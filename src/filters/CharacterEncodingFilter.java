package filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig;
	private String  default_charset="UTF-8";//web.xml中未配置字符集则使用默认的字符集
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig= filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		String charset=filterConfig.getInitParameter("charset");
		
		if(charset==null){
			charset=default_charset;
		}
		
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		
		chain.doFilter(new MyRequest(request), response);

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}

class MyRequest extends HttpServletRequestWrapper{

	public MyRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value= super.getParameter(name);
		
		if(value==null){
			return null;
		}
		
		if(super.getMethod().equalsIgnoreCase("post")){
			return value;
		}
		
		try {
			value=new String(value.getBytes("ISO8859-1"),super.getCharacterEncoding());
			return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
		
	}
	
	
	
}




















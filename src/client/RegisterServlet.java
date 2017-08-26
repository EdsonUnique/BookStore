package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;
import utils.MyBeanUtils;

public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceInter service=new BusinessService();
			User user=MyBeanUtils.Params2Bean(request.getParameterMap(), User.class);
			//通过手机号码判断用户是否已存在
			if(service.findUser(user.getUsername(), user.getPassword())!=null){
				request.setAttribute("message", "对不起，注册失败！用户已存在！");
			}else{
				service.add(user);
				
				//注册成功后给用户发送注册成功的邮件
				SendEmail send=new SendEmail();
				send.setUser(user);
				send.start();
				
				request.setAttribute("message", "注册成功！已向您的邮箱发送验证信息，请注意查收并激活帐户！");
			}
			
		}catch (Exception e) {
			request.setAttribute("message", "对不起，注册失败！");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

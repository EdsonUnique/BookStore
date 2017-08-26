package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Exception.PrivilegeException;

import domain.Book;
import domain.Category;
import domain.Page;
import domain.User;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;
import utils.MyBeanUtils;
import utils.ServiceFactory;


public class BookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String method=request.getParameter("method");		
			
			if(method.equalsIgnoreCase("addBook")){
				addBook(request,response);
			}else if(method.equalsIgnoreCase("updateBook")){
				updateBook(request,response);
			}else if(method.equalsIgnoreCase("deleteBook")){
				deleteBook(request,response);
			}else if(method.equalsIgnoreCase("listAllBook")){
				listAllBook(request,response);
			}else if(method.equalsIgnoreCase("addBookUI")){
				addBookUI(request,response);
				 //查找出图书所属的分类
			}else{
				request.setAttribute("message", "不支持此类操作！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
			
	}

	private void addBookUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		try{
			User user=(User) request.getSession().getAttribute("user");
			BusinessServiceInter service=ServiceFactory.getInstance().getBusinessService(BusinessService.class, user);
			List<Category>list=service.find();
			
			request.setAttribute("book_category", list);
			request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
		}catch (Exception e) {
			if(e.getCause() instanceof PrivilegeException){
				request.setAttribute("message",e.getCause().getMessage());
			}else{
				request.setAttribute("message", "操作失败!");
			}
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	private void listAllBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceInter service=new BusinessService();
			String pagenum=request.getParameter("pagenum");
			Page page=service.getBookPage(pagenum,10);
			page.setServlet("manager/"+this.getServletName()+"?method=listAllBook&");

			request.setAttribute("page", page);
			request.getRequestDispatcher("/manager/listBook.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateBook(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			User user=(User) request.getSession().getAttribute("user");
			BusinessServiceInter service=ServiceFactory.getInstance().getBusinessService(BusinessService.class, user);
			
			Book book=doFileupload(request);
			service.addBook(book);
			
			request.setAttribute("message", "添加成功！");
		}catch (Exception e) {
			if(e.getCause() instanceof PrivilegeException){
				request.setAttribute("message",e.getCause().getMessage());
			}else{
				request.setAttribute("message", "操作失败!");
			}
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private Book doFileupload(HttpServletRequest request) {
		try{
			Book book =new Book();
			DiskFileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			List<FileItem> list=upload.parseRequest(request);
			String savepath=this.getServletContext().getRealPath("/images");
			for(FileItem item:list){
				if(item.isFormField()){
					String name=item.getFieldName();
					String value=item.getString();
					value=new String(value.getBytes("ISO8859-1"),"UTF-8");//解决中文乱码
					BeanUtils.setProperty(book, name, value);
				}else{
					String filename=item.getName();
					filename=filename.substring(filename.lastIndexOf("\\")+1);
					if(filename==null || filename.trim().equals("")){
						return null;
					}
					
					
					String realpath=makeRealPath(savepath,filename);
					BeanUtils.setProperty(book, "image",realpath.substring(realpath.indexOf("images")));
					InputStream in=item.getInputStream();
					FileOutputStream out=new FileOutputStream(realpath);
					int len =0;
					byte[] buffer=new byte[1024];
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					
					in.close();
					out.close();
				}
			}
			return book;
			
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String makeRealPath(String savepath, String filename) {
		//避免文件重名覆盖
		filename=UUID.randomUUID()+"_"+filename;
		//生成多个文件夹储存文件
		int dir1=filename.hashCode()&0xf;
		int dir2=(filename.hashCode()&0xf)>>4;
		File file=new File(savepath+"/"+dir1+"/"+dir2);
		
		if(!file.exists()){
			file.mkdirs();
		}
		
		return file+"/"+filename;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

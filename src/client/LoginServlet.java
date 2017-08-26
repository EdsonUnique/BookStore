package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;

public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			
			BusinessServiceInter service=new BusinessService();
			User user=service.findUser(username, password);
			
			if(user!=null){
				request.getSession().setAttribute("user", user);//ÈÃÓÃ»§µÇÂ¼
			}
			request.getRequestDispatcher("/client/head.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("message", "²Ù×÷Ê§°Ü£¡");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

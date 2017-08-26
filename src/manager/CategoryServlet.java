package manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;

public class CategoryServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String method=request.getParameter("method");		
		
		if(method.equalsIgnoreCase("addCategory")){
			addCategory(request,response);
		}else if(method.equalsIgnoreCase("updateCategory")){
			updateCategory(request,response);
		}else if(method.equalsIgnoreCase("deleteCategory")){
			deleteCategory(request,response);
		}else if(method.equalsIgnoreCase("listAllCategory")){
			listAllCategory(request,response);
		}else{
			request.setAttribute("message", "��֧�ִ��������");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	
	private void listAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceInter service=new BusinessService();
			
			List<Category> list=service.find();
			if(list==null){
				request.setAttribute("message", "��δ���κη��࣡");
				request.getRequestDispatcher("/message.jap").forward(request, response);
				return;
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/manager/listCategory.jsp").forward(request, response);
			return;
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "����ʧ�ܣ�");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}


	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try{
			BusinessServiceInter service=new BusinessService();
			String name=request.getParameter("name");
			String description=request.getParameter("description");
			
			Category c=new Category();
			c.setName(name);
			c.setDescription(description);
			service.add(c);
			
			request.setAttribute("message", "������ӳɹ���");
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "�������ʧ�ܣ�");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

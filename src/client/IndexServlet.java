package client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import domain.Category;
import domain.Page;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;

public class IndexServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//找到所有分类
			BusinessServiceInter service=new BusinessService();
			List<Category>list=service.find();
			request.setAttribute("category", list);
			
			//找到指定类别的所有图书进行展示
			String category_id=request.getParameter("category_id");
			String pagenum=request.getParameter("pagenum");
			Page page=null;
			if(category_id==null){
				page=service.getBookPage(pagenum, 2);//获取所有图书的page对象
			}else{//获取指定类别图书的page对象
				page=service.getBookInCategoryPage(category_id, pagenum, 3);
			}
			page.setServlet("client/"+this.getServletName()+"?");
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("/client/body.jsp").forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

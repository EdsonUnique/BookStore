package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;

import domain.Book;
import domain.Cart;
import domain.CartItem;
import domain.User;

public class BuyServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//判断用户是否已登录
			User user=(User) request.getSession().getAttribute("user");
			if(user==null){
				//跳转到登录页面
				request.setAttribute("message", "请先登录！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			
			//获取购物车对象
			String bookId=request.getParameter("bookId");
			Cart cart= (Cart) request.getSession().getAttribute("cart");
			BusinessServiceInter service=new BusinessService();
			Book book=service.findBook(bookId);
			if(cart==null){
				
				cart=new Cart();
				Map<String,CartItem> map=new HashMap<String,CartItem>();
				CartItem item=new CartItem();
				item.setBook(book);
				item.setNum(1);
				item.setSum(item.getSum());
				map.put(bookId,item);
				cart.setMap(map);
				cart.setTotalprice(cart.getTotalprice());
				
				request.getSession().setAttribute("cart", cart);
			}else{
			
				Map<String,CartItem> map=cart.getMap();
				CartItem item=map.get(bookId);
				if(item!=null){//该书已购买过一次
					item.setNum(item.getNum()+1);
					item.setSum(item.getSum());
				}else{
				
					item=new CartItem();
					item.setBook(book);
					item.setNum(1);
					item.setSum(item.getSum());
					map.put(bookId,item);
				}
				
				cart.setMap(map);
				cart.setTotalprice(cart.getTotalprice());
				
				request.getSession().setAttribute("cart", cart);
			}
			
			request.setAttribute("message", "购买成功！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "购买失败！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

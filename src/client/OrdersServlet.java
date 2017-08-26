package client;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;

import domain.Order;
import domain.OrderItem;
import domain.User;

public class OrdersServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			User user=(User) request.getSession().getAttribute("user");
			
			if(user==null){
				request.setAttribute("message", "请先登录！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			String method=(String) request.getParameter("method");
			
			if(method.equalsIgnoreCase("listOrders")){
				listOrders(user,request,response);
				return;
			}else if(method.equalsIgnoreCase("listOrderItems")){
				String order_id=request.getParameter("order_id");
				listOrderItems(user,order_id,request,response);
				return;
			}else{
				request.setAttribute("message", "不支持此操作！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	/**
	 * 一个订单的所有订单项
	 * @param user
	 * @param order_id 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listOrderItems(User user, String order_id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceInter service =new BusinessService();
		Order order=service.findOrder(user.getId(), order_id);
		//给OrderItem设置书对象便于显示
		for(Entry<String, OrderItem> item :order.getMap().entrySet()){
			item.getValue().setBook(service.findBook(item.getKey()));
		}
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/client/listOrderItems.jsp").forward(request, response);
		
	}


	private void listOrders(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceInter service =new BusinessService();
		List<Order> orders=service.findOrders(user.getId());
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/client/listOrders.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

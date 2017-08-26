package manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Order;
import domain.OrderItem;
import domain.User;

import service.impl.BusinessService;
import service.impl.BusinessServiceInter;

public class OrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String method=request.getParameter("method");
			if(method.equalsIgnoreCase("inSend")){
				//查找已发货订单并显示
				inSend(request,response);
			}else if(method.equalsIgnoreCase("outSend")){
				//查找未发货订单并显示
				outSend(request,response);
			}else if(method.equalsIgnoreCase("updateState")){
				String order_id=request.getParameter("order_id");
				String state=request.getParameter("state");
				updateState(order_id,state,request,response);//修改订单状态
			}else if(method.equalsIgnoreCase("listOrderItems")){
				String order_id=request.getParameter("order_id");
				listOrderItems(order_id,request,response);
			}else{
				request.setAttribute("message", "不支持此类操作！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	
	private void listOrderItems(String order_id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceInter service =new BusinessService();
		Order order=service.findOrder(order_id);
		
		for(OrderItem item:order.getMap().values()){
			item.setBook(service.findBook(item.getBook_id()));
		}
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/manager/listOrderItems.jsp").forward(request, response);
		
		
	}


	private void updateState(String order_id, String state, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceInter service =new BusinessService();
		boolean set=true;
		
		if(state.equalsIgnoreCase("false")){
			set=false;
		}
		
		service.updateState(order_id, set);
		//修改订单状态后跳转到未发货订单管理页面
		outSend(request,response);
		
	}


	private void inSend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceInter service=new BusinessService();
		List<Order> orders=service.findStateOrder(true);
		
		//给每个Order对象设置User属性值便于显示
		for(Order order:orders){
			User user=service.findUser(order.getUser_id());
			order.setUser(user);
		}
		
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/manager/inSendOrder.jsp").forward(request, response);
		
	}


	private void outSend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceInter service=new BusinessService();
		List<Order> orders=service.findStateOrder(false);
		
		//给每个Order对象设置User属性值便于显示
		for(Order order:orders){
			User user=service.findUser(order.getUser_id());
			order.setUser(user);
		}
		
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/manager/outSendOrder.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

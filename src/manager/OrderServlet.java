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
				//�����ѷ�����������ʾ
				inSend(request,response);
			}else if(method.equalsIgnoreCase("outSend")){
				//����δ������������ʾ
				outSend(request,response);
			}else if(method.equalsIgnoreCase("updateState")){
				String order_id=request.getParameter("order_id");
				String state=request.getParameter("state");
				updateState(order_id,state,request,response);//�޸Ķ���״̬
			}else if(method.equalsIgnoreCase("listOrderItems")){
				String order_id=request.getParameter("order_id");
				listOrderItems(order_id,request,response);
			}else{
				request.setAttribute("message", "��֧�ִ��������");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "����ʧ�ܣ�");
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
		//�޸Ķ���״̬����ת��δ������������ҳ��
		outSend(request,response);
		
	}


	private void inSend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessServiceInter service=new BusinessService();
		List<Order> orders=service.findStateOrder(true);
		
		//��ÿ��Order��������User����ֵ������ʾ
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
		
		//��ÿ��Order��������User����ֵ������ʾ
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

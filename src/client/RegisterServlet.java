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
			//ͨ���ֻ������ж��û��Ƿ��Ѵ���
			if(service.findUser(user.getUsername(), user.getPassword())!=null){
				request.setAttribute("message", "�Բ���ע��ʧ�ܣ��û��Ѵ��ڣ�");
			}else{
				service.add(user);
				
				//ע��ɹ�����û�����ע��ɹ����ʼ�
				SendEmail send=new SendEmail();
				send.setUser(user);
				send.start();
				
				request.setAttribute("message", "ע��ɹ��������������䷢����֤��Ϣ����ע����ղ������ʻ���");
			}
			
		}catch (Exception e) {
			request.setAttribute("message", "�Բ���ע��ʧ�ܣ�");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

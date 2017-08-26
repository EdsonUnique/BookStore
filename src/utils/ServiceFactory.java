package utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import javax.management.RuntimeErrorException;

import Exception.PrivilegeException;

import service.impl.BusinessService;

import domain.Privilege;
import domain.User;

@SuppressWarnings("all")
public class ServiceFactory {

	private static ServiceFactory factory=new ServiceFactory();
	
	private ServiceFactory(){};
	
	public static ServiceFactory getInstance(){
		return factory;
	}
	
	public <T> T getBusinessService(Class<T> clazz,final User user) throws InstantiationException, IllegalAccessException{
		final T t=clazz.newInstance();
		
		return (T) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				//��ȡ��������
				String name=method.getName();
				System.out.println(name);
				//��ȡ��ʵ�����ϵķ���
				Method m=t.getClass().getMethod(name, method.getParameterTypes());
				//��ȡ���õķ����ϵ�ע��
				Permission p=m.getAnnotation(Permission.class);
				//��ע��Ϊ�գ�������Ȩ�ޣ�����
				if(p==null){
					return method.invoke(t, args);
				}
				//��ҪȨ�ޣ���õ�ע�����ݲ�new��Ȩ�޶���
				String value=p.value();
				Privilege pl=new Privilege();
				pl.setName(value);
				//�ж��û��Ƿ��¼����¼��õ��û�ӵ�е�Ȩ��
				if(user==null){
					throw new PrivilegeException("�Բ������ȵ�¼��");
				}
				
				BusinessService service=new BusinessService();
				List<Privilege> list=service.findPrivileges(user.getId());
				
				
				//�ж���Ҫ��Ȩ�޶����û��Ƿ�ӵ��
				if(list.contains(pl)){
					return method.invoke(t, args);
				}else{
					throw new PrivilegeException("�Բ�����û��Ȩ�޷��ʣ�");
				}
				
				//û�����׳�Ȩ���쳣��ӵ�������
				
			}
		}); 
	}
}

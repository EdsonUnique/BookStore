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
				//获取方法名称
				String name=method.getName();
				System.out.println(name);
				//获取真实对象上的方法
				Method m=t.getClass().getMethod(name, method.getParameterTypes());
				//获取调用的方法上的注解
				Permission p=m.getAnnotation(Permission.class);
				//若注解为空，则无需权限，放行
				if(p==null){
					return method.invoke(t, args);
				}
				//需要权限，则得到注解内容并new出权限对象
				String value=p.value();
				Privilege pl=new Privilege();
				pl.setName(value);
				//判断用户是否登录，登录则得到用户拥有的权限
				if(user==null){
					throw new PrivilegeException("对不起，请先登录！");
				}
				
				BusinessService service=new BusinessService();
				List<Privilege> list=service.findPrivileges(user.getId());
				
				
				//判断需要的权限对象用户是否拥有
				if(list.contains(pl)){
					return method.invoke(t, args);
				}else{
					throw new PrivilegeException("对不起！您没有权限访问！");
				}
				
				//没有则抛出权限异常，拥有则放行
				
			}
		}); 
	}
}

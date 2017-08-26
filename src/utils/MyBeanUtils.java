package utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MyBeanUtils {
	
	public static <T> T Params2Bean(Map params,Class<T> clazz){
		
		try{
			T bean=(T) clazz.newInstance();
			
			BeanUtils.populate(bean, params);
			return bean;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}

package utils;

public class DaoFactory {
	
	private static  DaoFactory factory=new DaoFactory();
	
	private DaoFactory(){
		
	}
	
	public static DaoFactory getInstance(){
		return factory;
	}
	

	public static <T> T createDao(String className) {
		try{
			T t=(T) Class.forName(className).newInstance();	
			return t;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}

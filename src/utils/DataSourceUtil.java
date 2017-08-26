package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtil {
	
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	
	public  static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConn(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

}

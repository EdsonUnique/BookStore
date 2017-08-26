package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.DataSourceUtil;

import domain.Category;

public class dao_Category { 

	public void add(Category c) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="insert into category(id,name,description) values(?,?,?)";
		Object params[]={c.getId(),c.getName(),c.getDescription()};

		qr.update(sql, params);
	}
	
	
	public Category find(String id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from category where id=?";
		return qr.query(sql, id, new BeanHandler(Category.class));
	}
	
	public List find() throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from category";
		return qr.query(sql, new BeanListHandler(Category.class));
	}
	
	
}

















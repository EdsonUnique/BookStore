package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.DataSourceUtil;
import utils.Permission;

import domain.Book;
import domain.Category;

public class dao_Book { 

	
	public void addBook(Book b) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="insert into book(id,name,author,price,image,description,category_id) values(?,?,?,?,?,?,?)";
		Object params[]={b.getId(),b.getName(),b.getAuthor(),b.getPrice(),b.getImage(),b.getDescription(),b.getCategory_id()};

		qr.update(sql, params);
	}
	
	
	public Book find(String id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from book where id=?";
		return qr.query(sql, id, new BeanHandler(Book.class));
	}
	
	
	public List find() throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from book";
		return qr.query(sql, new BeanListHandler(Book.class));
	}
	
	public int getTotalRecord() throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select count(*) from book";
		long l=(Long)qr.query(sql,new ScalarHandler());
		return (int)l;
	}
	
	/**
	 * 获取指定类别的图书数量
	 * @param category_id
	 * @return
	 * @throws SQLException
	 */
	public int getSelectedTotalRecord(String category_id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select count(*) from book where category_id=?";
		long l=(Long)qr.query(sql,category_id,new ScalarHandler());
		return (int)l;
	}
	
	public List<Book> getPageData(int pageindex,int pagesize) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from book limit ?,?";
		Object[]params={pageindex,pagesize};
		return qr.query(sql,params,new BeanListHandler(Book.class));
	}
	
	public List<Book> getBookInCategoryPageData(String category_id,int pageindex,int pagesize) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from book where category_id=? limit ?,?";
		Object[]params={category_id,pageindex,pagesize};
		return qr.query(sql,params,new BeanListHandler(Book.class));
	}
	
	/**
	 * 找指定类别的所有图书
	 * @param category_id
	 * @return
	 * @throws SQLException 
	 */
	public List findCategoryBook(String category_id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from book where category_id=?";
		return qr.query(sql,category_id, new BeanListHandler(Book.class));
	}
	
	
}

















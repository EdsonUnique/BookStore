package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.DataSourceUtil;

import domain.Privilege;
import domain.User;

public class dao_User {

	public void add(User user) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="insert into user(id,username,password,phone,address,email) values(?,?,?,?,?,?)";
		Object []params={user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getAddress(),user.getEmail()};
		qr.update(sql, params);
	}
	
	public void delete(String id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="delete from user where id=?";
		qr.update(sql, id);
	}
	
	public void update(User user) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="update user set username=?,password=?,phone=?,address=?,email=?) where id=?";
		Object []params={user.getUsername(),user.getPassword(),user.getPhone(),user.getAddress(),user.getEmail(),user.getId()};
		qr.update(sql, params);
	}
	
	/**
	 * 用户名和密码作为联合主键进行查询
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public User find(String username,String password) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user where username=? and password=?";
		Object params[]={username,password};
		return qr.query(sql,params ,new BeanHandler(User.class));
	}
	
	public User find(String id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user where id=?";
		return qr.query(sql,id ,new BeanHandler(User.class));
	}
	
	public List find() throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user";
		return qr.query(sql, new BeanListHandler(User.class));
	}
	
	/**
	 * 查找用户拥有的权限
	 * @throws SQLException 
	 */
	
	public List findPrivilege(String user_id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select privilege.* from user_privilege,privilege where id=privilege_id and user_id=?";
		return qr.query(sql, user_id,new BeanListHandler(Privilege.class));
	}
	
	
	
	
	
}

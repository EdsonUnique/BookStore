package service.impl;

import java.sql.SQLException;
import java.util.List;

import domain.Book;
import domain.Category;
import domain.Order;
import domain.Page;
import domain.User;

public interface BusinessServiceInter {

	public void add(Category c);

	public Category find(String id);

	public List<Category> find();

	//图书
	public void addBook(Book book);

	public Book findBook(String id);

	public List<Book> findBook();

	//获取所有图书分页对象
	public Page getBookPage(String pa, int simplePageSize);

	/**
	 * 获取指定类别的图书的分页对象
	 * @param pa
	 * @param simplePageSize
	 * @return
	 */
	public Page getBookInCategoryPage(String category_id, String pa,
			int simplePageSize);

	/**
	 * 找指定类别的所有图书
	 * @param category_id
	 * @return
	 * @throws SQLException 
	 */
	public List findCategoryBook(String category_id);

	//用户
	public void add(User user);

	public void delete(String id);

	public void update(User user);

	public User findUser(String username, String password);

	public User findUser(String id);

	public List findUsers();
	
	public List findPrivileges(String user_id);

	//Order订单
	public void addOrder(Order order);

	public List findOrders(String user_id);

	public Order findOrder(String user_id, String order_id);

	public Order findOrder(String order_id);

	public List findStateOrder(boolean state);

	public void updateState(String order_id, boolean state);

	
}
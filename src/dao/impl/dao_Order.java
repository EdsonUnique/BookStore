package dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.DataSourceUtil;

import domain.Order;
import domain.OrderItem;

public class dao_Order {

	public void add(Order order) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		
		//����Order��
		String sql="insert into orders(id,state,ordertime,totalprice,user_id) values(?,?,?,?,?)";
		Object []params={order.getId(),order.isState(),order.getOrderTime(),order.getTotalprice(),order.getUser().getId()};
		qr.update(sql, params);
		
		//����OrderItem��
		for(Entry<String, OrderItem> entry:order.getMap().entrySet()){
			sql="insert into orderItem(id,price,num,sum,orders_id,book_id) values(?,?,?,?,?,?)";
			OrderItem item=entry.getValue();
			params=new Object[]{item.getId(),item.getPrice(),item.getNum(),item.getSum(),item.getOrder_id(),item.getBook().getId()};
			qr.update(sql, params);
		}
		
	}
	
	/**
	 * ����ָ���û��Ķ��������ж�����ϸ
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public List find(String user_id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from orders where user_id=?";
		List<Order> orders=qr.query(sql,user_id, new BeanListHandler(Order.class));
		
		//�ҳ���Ӧ��OrderItem
		for(Order order:orders){
			sql="select * from orderItem where orders_id=?";
			List<OrderItem> items=qr.query(sql, order.getId(),new BeanListHandler(OrderItem.class));
			Map<String,OrderItem>map=new HashMap<String,OrderItem>();
			for(OrderItem item:items){
				map.put(item.getBook_id(), item);
			}
			order.setMap(map);
		}
		
		return orders;
	}
	/**
	 * ָ�������Ķ�����
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public Order find(String user_id,String order_id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from orders where user_id=? and id=?";
		Object[]params={user_id,order_id};
		Order order=qr.query(sql,params, new BeanHandler(Order.class));
		
		//�ҳ���Ӧ��OrderItem
		sql="select * from orderItem where orders_id=?";
		List<OrderItem> list=qr.query(sql, order_id, new BeanListHandler(OrderItem.class));
		
		Map<String,OrderItem>map=new HashMap<String,OrderItem>();
		for(OrderItem item:list){
			
			map.put(item.getBook_id(), item);
		}
		order.setMap(map);
		
		return order;
	}
	/**
	 * ͨ�������Ų��Ҷ�����
	 * @param order_id
	 * @return
	 * @throws SQLException
	 */
	public Order findOrderItems(String order_id) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from orders where id=?";
		Order order=qr.query(sql,order_id, new BeanHandler(Order.class));
		
		//�ҳ���Ӧ��OrderItem
		sql="select * from orderItem where orders_id=?";
		List<OrderItem> list=qr.query(sql, order_id, new BeanListHandler(OrderItem.class));
		
		Map<String,OrderItem>map=new HashMap<String,OrderItem>();
		for(OrderItem item:list){
			
			map.put(item.getBook_id(), item);
		}
		order.setMap(map);
		
		return order;
	}
	/**
	 * �ҳ�ĳһ״̬�Ķ������ں�̨����
	 * @param state
	 * @return
	 * @throws SQLException 
	 */
	public List findStateOrder(boolean state) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from orders where state=?";
		List<Order> orders=qr.query(sql,state, new BeanListHandler(Order.class));
		
		//�ҳ���Ӧ��OrderItem
		
		for(Order order :orders){
			sql="select * from orderItem where orders_id=?";
			List<OrderItem> list=qr.query(sql, order.getId(), new BeanListHandler(OrderItem.class));
			
			Map<String,OrderItem>map=new HashMap<String,OrderItem>();
			for(OrderItem item:list){
				map.put(item.getBook_id(), item);
			}
			order.setMap(map);
		}
		
		return orders;
	}
	
	/**
	 * �޸Ķ���״̬
	 * @param order_id
	 * @param state
	 * @throws SQLException
	 */
	public void updateState(String order_id,boolean state) throws SQLException{
		QueryRunner qr=new QueryRunner(DataSourceUtil.getDataSource());
		String sql="update orders set state=? where id=?";
		Object[]params={state,order_id};
		
		qr.update(sql, params);
		
		
		
		
	}
	
	
}

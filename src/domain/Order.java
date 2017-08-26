package domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private String id;
	private Date orderTime;//下单时间
	private boolean state;//订单状态
	
	/**
	 * 具体的订单项，以bookId为关键字存储
	 */
	private Map<String,OrderItem>map=new HashMap<String,OrderItem>();
	private double totalprice;
	private User user;
	private String user_id;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Map<String, OrderItem> getMap() {
		return map;
	}
	public void setMap(Map<String, OrderItem> map) {
		this.map = map;
	}
	public double getTotalprice() {
		totalprice=0;
		
		for(OrderItem item:map.values()){
			totalprice+=item.getSum();
		}
		
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}

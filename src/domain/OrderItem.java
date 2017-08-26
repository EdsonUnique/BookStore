package domain;

import java.util.HashSet;
import java.util.Set;

public class OrderItem {

	private String id;
	private int num;
	private double price;
	private double sum;
	private Book book;//哪件商品
	
	private String order_id;//记住该订单项属于哪个订单
	private String book_id;
	
	
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSum() {
		sum=this.price*num;
		return sum;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	
}

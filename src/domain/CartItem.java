package domain;

public class CartItem {

	private Book book;
	private int num;//购买该书的数量
	private double sum;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSum() {
		sum=book.getPrice()*num;
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
}

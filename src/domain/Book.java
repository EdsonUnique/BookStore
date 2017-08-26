package domain;

public class Book {

	private String id;
	private String name;
	private String author;
	private double price;
	private String image;//图片的存储位置
	private String description;
	private String category_id;//图书分类
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public Book(String id, String name, String author, double price,
			String image, String description, String category_id) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.image = image;
		this.description = description;
		this.category_id = category_id;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

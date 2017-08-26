package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车对象：使用map集合存储一本书的id，以及CartItem
 * 			CartItem:保存购买该书的数量，书信息，价钱
 * @author Acer
 *
 */
public class Cart {

	private Map<String,CartItem>map=new HashMap<String,CartItem>();
	private double totalprice;//购物车总价
	
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getTotalprice() {
		totalprice=0;
		
		for(CartItem item:map.values()){
			totalprice+=item.getSum();
		}
		
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
	
	
}

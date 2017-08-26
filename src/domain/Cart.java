package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * ���ﳵ����ʹ��map���ϴ洢һ�����id���Լ�CartItem
 * 			CartItem:���湺����������������Ϣ����Ǯ
 * @author Acer
 *
 */
public class Cart {

	private Map<String,CartItem>map=new HashMap<String,CartItem>();
	private double totalprice;//���ﳵ�ܼ�
	
	
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

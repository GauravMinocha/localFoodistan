package minocha.foodistan.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

	private static int orderId;
	private Map<String, Integer> orderItemQuantity = new HashMap<String, Integer>();
	private int orderDiscount;
    public enum status {RELEASED, HOLD, COMPLETE};
    private status orderStatus;  
    private int orderBill;
    
    
    
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Map<String, Integer> getOrderItemQuantity() {
		return orderItemQuantity;
	}
	public void setOrderItemQuantity(Map<String, Integer> orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}
	public int getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	public status getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(status orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getOrderBill() {
		return orderBill;
	}
	public void setOrderBill(int orderBill) {
		this.orderBill = orderBill;
	}
    
}

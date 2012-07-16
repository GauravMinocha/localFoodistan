package minocha.foodistan.order;

public class Order {

	private int orderId;
	private String orderItem;
	private int orderQuantity;
	private int orderStatus;	
    private int orderDiscount;
    //private enum statusType = {"RELEASED, HOLD, COMPLETE"};
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}

	public Order(String orderItem, int orderQuantity, int orderDiscount) {
		super();
		this.orderItem = orderItem;
		this.orderQuantity = orderQuantity;
		this.orderDiscount = orderDiscount;
		this.orderStatus = 0;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

    
}

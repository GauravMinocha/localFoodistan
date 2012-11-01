package minocha.foodistan.salesCounter;

import java.util.List;
import java.util.Map;


import minocha.foodistan.item.Item;
import minocha.foodistan.item.ItemType;
import minocha.foodistan.manager.Foodistan;
import minocha.foodistan.order.Order;
import minocha.foodistan.order.Order.orderStatus;

public class SalesCounter {
	
	private int sCounterNo;
    public enum counterStatus {BUSY, FREE}
    private counterStatus sCounterStatus;
   
	public SalesCounter(int sCounterNo) {
		super();
		this.sCounterNo = sCounterNo;
		this.sCounterStatus = counterStatus.FREE;
	}
    
    public Item recordOrder(ItemType itemType, int quantity, int orderDiscount){
    	this.setsCounterStatus(counterStatus.BUSY);
    	Order order = new Order(itemType, quantity, orderDiscount);
    	Foodistan.getfoodistan().setOrdersReceived(Foodistan.getfoodistan().getOrdersReceived()+1);
    	this.setsCounterStatus(counterStatus.FREE);
    	Item itm = Foodistan.getfoodistan().getMg().processOrder(order);
      	return itm;
		}
    
    public int getCounterNo() {
		return sCounterNo;
	}
	public void setCounterNo(int counterNo) {
		this.sCounterNo = counterNo;
	}
	public counterStatus getCounterStatus() {
		return sCounterStatus;
	}
	public void setsCounterStatus(counterStatus sCounterStatus) {
		this.sCounterStatus = sCounterStatus;
	}
   
	
}

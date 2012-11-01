package minocha.foodistan.salesCounter;

import minocha.foodistan.item.Item;
import minocha.foodistan.item.Item.ItemType;
import minocha.foodistan.manager.Foodistan;


import minocha.foodistan.order.Order;

public class SalesCounter {
	
	private int sCounterNo;
   
    private counterStatus sCounterStatus;
   
    public enum counterStatus {BUSY, FREE}
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
    	Item item = Foodistan.getfoodistan().getMg().processOrder(order);
      	return item;
		}
    
    //getter & setter 
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

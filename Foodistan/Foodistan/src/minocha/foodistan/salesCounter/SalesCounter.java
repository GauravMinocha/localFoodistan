package minocha.foodistan.salesCounter;

import java.util.List;
import java.util.Map;


import minocha.foodistan.item.Item;
import minocha.foodistan.item.ItemType;
import minocha.foodistan.manager.Foodistan;
import minocha.foodistan.order.Order;

public class SalesCounter {
	
	private int sCounterNo;
    public enum status {BUSY, FREE}
    private status sCounterStatus;
    
    
    public Item recordOrder(SalesCounter sc, ItemType itemType, int Quantity, int orderDiscount){
		
    	Order order = null;
		return Foodistan.getfoodistan().getMg().processOrder(order);
    
    	}
    
    public int getCounterNo() {
		return sCounterNo;
	}
	public void setCounterNo(int counterNo) {
		this.sCounterNo = counterNo;
	}

	public SalesCounter(int sCounterNo) {
		super();
		this.sCounterNo = sCounterNo;
		this.sCounterStatus = status.FREE;
	}
	public status getCounterStatus() {
		return sCounterStatus;
	}
	public void setsCounterStatus(status sCounterStatus) {
		this.sCounterStatus = sCounterStatus;
	}
    
    
	
}

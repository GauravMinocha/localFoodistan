package minocha.foodistan.salesCounter;


import minocha.foodistan.item.Item;
import minocha.foodistan.order.Order;

public interface SalesCounterService {
	
	    public int createOrder(String orderItem, int orderQuantity, int foodieDiscount);
	        
   	    public boolean isBusy();
   	    
   	    
}

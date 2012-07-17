package minocha.foodistan.manager;

import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.Item;
import minocha.foodistan.order.*;

public interface Manager {
	
	public Item executeOrder(Order order);
    
    public int calculateDiscount(Order order);
    
    public int refillInventory(Inventory inventory);
       
	
}

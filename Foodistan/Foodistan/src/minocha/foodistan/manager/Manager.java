package minocha.foodistan.manager;

import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.Item;
import minocha.foodistan.order.*;

public interface Manager {
	
	public Item processOrder(Order order);
    
    public int calculateDiscount();
    
    public int refillInventory(Item item);
       
	
}

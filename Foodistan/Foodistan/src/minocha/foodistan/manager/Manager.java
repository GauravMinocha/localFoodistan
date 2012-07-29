package minocha.foodistan.manager;

import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.Item;
import minocha.foodistan.order.*;

public interface Manager {
	
	public Item processOrder(Order order);
    
    public long calculateDiscount();
    
    public void refillInventory(Item item);
    
    public boolean hasInventory(Inventory invtry, int quantity);
    public Item getItem(Inventory invtry, int quantity);
       
	
}

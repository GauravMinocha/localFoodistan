package minocha.foodistan.manager;

import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.Item;
import minocha.foodistan.order.*;
/*
 * Interface to:
 * 1. Process an order : processOrder
 * 2. Calculate discount : calculateDiscount
 * 3. Add an item into inventory :  refillInventory
 * 4. Remove/Get an item from inventory : getItem
 * 5. Check inventory : hasInventory
 */

public interface Manager {

	public Item processOrder(Order order);

	public long calculateDiscount();

	public void refillInventory(Item item);

	public boolean hasInventory(Inventory invtry, int quantity);
	
	public Item getItem(Inventory invtry);

}

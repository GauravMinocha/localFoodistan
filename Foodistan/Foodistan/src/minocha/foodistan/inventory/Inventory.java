package minocha.foodistan.inventory;

import java.util.List;

import minocha.foodistan.item.*;

public class Inventory {

	private String inventoryItem;
	private int inventoryMin;
	private int inventoryMax;
	public List<Item> items;
		
   public List<Item> addItem(Item itm)
   {
	  this.items.add(itm);
	  return items;
	  
   }
   
   public Item removeItem()
   {
	return null;
	   //this.items.remove(arg0)
  }
      
	public String getInventoryItem() {
	return inventoryItem;
    }

public void setInventoryItem(String inventoryItem) {
	this.inventoryItem = inventoryItem;
}

public List<Item> getItems() {
	return items;
}

public void setItems(List<Item> items) {
	this.items = items;
}

	public int getInventoryMin() {
		return inventoryMin;
	}
	public void setInventoryMin(int inventoryMin) {
		this.inventoryMin = inventoryMin;
	}
	public int getInventoryMax() {
		return inventoryMax;
	}
	public void setInventoryMax(int inventoryMax) {
		this.inventoryMax = inventoryMax;
	}
	
	
	
}

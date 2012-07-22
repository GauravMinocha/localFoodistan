package minocha.foodistan.inventory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import minocha.foodistan.item.*;
import minocha.foodistan.item.Item.status;

public class Inventory {

	private ItemType itmType;
	// not used currently 
	private int inventoryMin;
	private int inventoryMax;
	// 
   public Queue<Item> items = new LinkedList<Item>();
	
		
   public int countItem()
   {
	   return this.items.size();
    }
   
	public void addItem(Item itm)
   {
	  this.items.add(itm);
	 
   }
   
   public Item removeItem()
   {
	  Item itm = this.items.poll();
	  if(!(itm.isUsable()))
	  {
		 itm.setItemStatus(status.STALE);
	  }
	  return itm;
	  //this.items.remove(arg0)
   }
      


	public ItemType getItmType() {
	return itmType;
}

public void setItmType(ItemType itmType) {
	this.itmType = itmType;
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

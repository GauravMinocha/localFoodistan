package minocha.foodistan.inventory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import minocha.foodistan.item.*;
import minocha.foodistan.item.Item.status;
import minocha.foodistan.manager.Foodistan;

public class Inventory {

	private ItemType itmType;
	// not used currently 
	private int inventoryMin;
	private int inventoryMax;
	// 
   public Queue<Item> items = new LinkedList<Item>();

   private boolean removeStale() {
	// TODO Auto-generated method stub
		 Foodistan fdistan = Foodistan.getfoodistan(); 
		 boolean b = false;
		
		 Item itm = this.items.peek();
		  while(!itm.isUsable()){
			  this.items.poll();
			  itm.setItemStatus(status.STALE);
			  fdistan.setBurgersWasted(fdistan.getBurgersWasted()+1);
			  itm = this.items.peek();
		      b = true;
		  }
			  return b;
   }
   public int countItem()
   {  
	   	removeStale();
	   	return this.items.size();
    }
   
	public void addItem(Item itm)
   {
		removeStale();	  
		this.items.add(itm);
   }
   
   public Item removeItem()
   {
	   removeStale();
	   return this.items.poll();
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

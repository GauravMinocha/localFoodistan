package minocha.foodistan.inventory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import minocha.foodistan.item.*;
import minocha.foodistan.item.Item.itemStatus;

import minocha.foodistan.manager.Foodistan;

public class Inventory {

   public Queue<Item> items = new LinkedList<Item>();

   private boolean removeStale() {
	// TODO Auto-generated method stub
		 Foodistan fdistan = Foodistan.getfoodistan(); 
		 boolean b = false;
         if(!this.items.isEmpty()){
        	 Item itm = this.items.peek();
        	 if(itm!=null){
		  while(!itm.isUsable()){
			  this.items.poll();
			  itm.setItmStatus(itemStatus.STALE);
			  fdistan.setBurgersWasted(fdistan.getBurgersWasted()+1);
			  itm = this.items.peek();
		      b = true;
		  }
        	 }
         }
			  return b;
   }
   public int countItem()
   {  
	   	//removeStale();
	   	return this.items.size();
    }
   
	public void addItem(Item itm)
   {
		//removeStale();	  
		this.items.add(itm);
   }
   
   public Item removeItem()
   {
	   //removeStale();
       if(!this.items.isEmpty()) 
	   return this.items.poll();
       else
    	   return null;
   }
 
}

package minocha.foodistan.inventory;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import minocha.foodistan.item.*;

public class Inventory {

   public Queue<Item> items = new LinkedList<Item>();

/*   private boolean removeStale() {
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
   }*/
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
      try{
	   if(!this.items.isEmpty()) {
	   Item itm = this.items.poll();
	   return itm;
	   
	   }
       else
    	   return null;
      }catch(NoSuchElementException e){
    	  
    	  return null;
      }
   }
 
}

package minocha.foodistan.manager;

import java.util.Iterator;

import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.Item;
import minocha.foodistan.item.ItemType;
import minocha.foodistan.order.Order;
import minocha.foodistan.order.Order.status;

public class ManagerImpl implements Manager {
    
	@Override
	public Item processOrder(Order order) {
		
		if (this.calculateDiscount() < order.getOrdrDiscount()||this.ifInventory(order.getOrdrItmTyp(), order.getOrdrQuantity())==false){
			order.setOrdrStatus(status.HOLD);
			Foodistan.getfoodistan().odrsOnHold.add(order);
            return null;
            }
        else
        	return this.getItem(order.getOrdrItmTyp(), order.getOrdrQuantity());
          }

	public boolean ifInventory(ItemType it, int quantity){
		return false;
	}
	
	public Item getItem(ItemType it, int quantity){
         return null;
	}
	
	public boolean addItem(Item item){
		item.getItemType();
		Iterator<Inventory> itr = Foodistan.getfoodistan().getInvList().iterator();
	
		while(!(itr.next().getItmType() == item.getItemType()))
		{   
			itr.next();
			
			}
		itr.next().addItem(item);
		//Foodistan.getfoodistan().invList.get(index)
		return true;
	}
	@Override
	public int calculateDiscount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int refillInventory(Item item) {
		// TODO Auto-generated method stub
		if(!Foodistan.getfoodistan().odrsOnHold.isEmpty())
		{  
			if(this.calculateDiscount() < Foodistan.getfoodistan().odrsOnHold.peek().getOrdrDiscount()){
				Foodistan.getfoodistan().odrsOnHold.add(Foodistan.getfoodistan().odrsOnHold.poll());
			    Foodistan.getfoodistan().foodiesOnHold.add(Foodistan.getfoodistan().foodiesOnHold.poll());
			}
			
			Foodistan.getfoodistan().odrsOnHold.poll().setOrdrStatus(status.COMPLETE);	
			Foodistan.getfoodistan().foodiesOnHold.poll().consumeItem(item);
						    				
		}
		else
		
		this.addItem(item);
		
		return 0;
	}
	
	
}
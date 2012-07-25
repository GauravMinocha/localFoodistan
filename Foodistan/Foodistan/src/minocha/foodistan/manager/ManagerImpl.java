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
		if (this.hasInventory(Foodistan.getfoodistan().getInvBurgr(), order.getOrdrQuantity())==false)
				{ Foodistan.getfoodistan().setBurgerNeeded(Foodistan.getfoodistan().getBurgerNeeded()+1);
							}
		if (this.calculateDiscount() < order.getOrdrDiscount()||this.hasInventory(Foodistan.getfoodistan().getInvBurgr(), order.getOrdrQuantity())==false){
			order.setOrdrStatus(status.HOLD);
			Foodistan.getfoodistan().odrsOnHold.add(order);
            return null;
            }
        else
        	Foodistan.getfoodistan().setOrdersDelivered(Foodistan.getfoodistan().getOrdersDelivered()+1);
        	return getItem(Foodistan.getfoodistan().getInvBurgr(), order.getOrdrQuantity());
       	    }

	public boolean hasInventory(Inventory invtry, int quantity){
		 if(invtry.countItem()>quantity)
		return true;
		 else
		return false;
		// review code below
		/*Iterator<Inventory> itr = Foodistan.getfoodistan().getInvList().iterator();
		while(!(itr.next().getItmType() == it))
		{   itr.next();
				}
		if (itr.next().countItem()> quantity){
		return true;
		}
		return false;
		*/
		}
	
	public Item getItem(Inventory invtry, int quantity){
         return invtry.removeItem();
	}
	
	public void addItem(Inventory invtry, Item item){
		invtry.addItem(item);
		
		/*item.getItemType();
		Iterator<Inventory> itr = Foodistan.getfoodistan().getInvList().iterator();
	
		while(!(itr.next().getItmType() == item.getItemType()))
		{   
			itr.next();
			
			}
		itr.next().addItem(item);
		//Foodistan.getfoodistan().invList.get(index)
		return true;
	  */
	}
	@Override
	public int calculateDiscount() {
		// TODO Auto-generated method stub
		/*	•	Total Discount = Net Demand / Net Supply
			•	Net Demand = Pending Sales Orders that are yet to be Delivered by the Counter
			•	Net Supply = (0.7) * Total Burgers in Counter's Inventory + (0.3) * Number of Burgers Ordered (for Procurement) by Counter but not yet Received
		*/
		long netDemand = Foodistan.getfoodistan().getOdrsOnHold().size();
		long netSupply = Foodistan.getfoodistan().getInvBurgr().countItem() + Foodistan.getfoodistan().getBurgerNeeded();
		int discount = (int) (netDemand/netSupply);
        return discount;
	}
	
	@Override
	public void refillInventory(Item item) {
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
		
		this.addItem(Foodistan.getfoodistan().getInvBurgr(), item);
		
		
	}
	
	
}
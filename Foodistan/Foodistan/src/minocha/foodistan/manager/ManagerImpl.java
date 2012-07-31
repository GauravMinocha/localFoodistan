package minocha.foodistan.manager;

import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.Item;
import minocha.foodistan.order.Order;
import minocha.foodistan.order.Order.orderStatus;


public class ManagerImpl implements Manager {
    
	@Override
	public Item processOrder(Order order) {
		if ((this.hasInventory(Foodistan.getfoodistan().getInv(), order.getOrdrQuantity())==false) || (this.calculateDiscount() < order.getOrdrDiscount())){ 
			Foodistan.getfoodistan().setBurgerNeeded(Foodistan.getfoodistan().getBurgerNeeded()+1);
			order.setOrdrStatus(orderStatus.HOLD);
			Foodistan.getfoodistan().odrsOnHold.add(order);
			return null;
		    }
        else{
        	Foodistan.getfoodistan().setOrdersDelivered(Foodistan.getfoodistan().getOrdersDelivered()+1);
		    order.setOrdrStatus(orderStatus.COMPLETE);
        	return getItem(Foodistan.getfoodistan().getInv(), order.getOrdrQuantity());
        }    
        }

	public boolean hasInventory(Inventory invtry, int quantity){
			if(invtry.countItem()>=quantity)
				return true;
			else
				return false;
		}
	
	public Item getItem(Inventory invtry, int quantity){
         	return invtry.removeItem();
	}
	
	public void addItem(Inventory invtry, Item item){
			invtry.addItem(item);
		

	}
	@Override
	public long calculateDiscount() {
		/*	•	Total Discount = Net Demand / Net Supply
			•	Net Demand = Pending Sales Orders that are yet to be Delivered by the Counter
			•	Net Supply = (0.7) * Total Burgers in Counter's Inventory + (0.3) * Number of Burgers Ordered (for Procurement) by Counter but not yet Received
		*/
		if(Foodistan.getfoodistan().getInv().countItem() == 0){
			return 90;
			
		}
		     int netDemand = Foodistan.getfoodistan().getOdrsOnHold().size();
			double netSupply = (0.7*Foodistan.getfoodistan().getInv().countItem()) + (0.3*Foodistan.getfoodistan().getBurgerNeeded());
			long discount; 
			try{
			 discount =  (long) ((long)(netDemand * 100)/netSupply);
	        }
			catch (ArithmeticException e){
	        	 return 25l;	        	
	        	
	        }
			return discount;
	}
	
	@Override
	public void refillInventory(Item item) {
			Foodistan.getfoodistan().getInv().addItem(item);
	}
	
}
package minocha.foodistan.manager;

import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.Item;
import minocha.foodistan.order.Order;
import minocha.foodistan.order.Order.orderStatus;


public class ManagerImpl implements Manager {

	@Override
	public Item processOrder(Order order) {
		/*
		 * Checks if order item quantity is not available in the inventory or foodistan discount is less that order discount. If yes, 
		 * burger needed is incremented, order status is set on hold and it is added to orders on hold list.
		 * else, orders delivered is incremented, order status is set to complete.    
		 */
		if ((this.hasInventory(Foodistan.getfoodistan().getInv(), order.getOrdrQuantity())==false) || (this.calculateDiscount() < order.getOrdrDiscount())){ 
			Foodistan.getfoodistan().setBurgerNeeded(Foodistan.getfoodistan().getBurgerNeeded()+1);
			order.setOrdrStatus(orderStatus.HOLD);
			Foodistan.getfoodistan().odrsOnHold.add(order);
			return null;
		}
		else{
			order.setOrdrStatus(orderStatus.COMPLETE);
			Foodistan.getfoodistan().setOrdersDelivered(Foodistan.getfoodistan().getOrdersDelivered()+1);
			// a while loop can be added here, to return a list of items if quantity is more than one 
			return getItem(Foodistan.getfoodistan().getInv());
		}    
	}

	public boolean hasInventory(Inventory invtry, int quantity){
		if(invtry.countItem()>=quantity)
			return true;
		else
			return false;
	}

	public Item getItem(Inventory invtry){
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
			return 100;
		}
		int netDemand = Foodistan.getfoodistan().getOdrsOnHold().size();
		if(netDemand == 0){
			netDemand = 0;
		}
		double netSupply = ((0.7)*Foodistan.getfoodistan().getInv().countItem()) + ((0.3)*Foodistan.getfoodistan().getBurgerNeeded());
		long discount; 
		try{
			discount =  (long) ((long)(netDemand * 100)/netSupply);
		}
		catch (ArithmeticException e){
			return 100;	        	
		}
		if(discount > 100) return 100;
		else return discount;
	}

	@Override
	public void refillInventory(Item item) {
		Foodistan.getfoodistan().getInv().addItem(item);
	}

}
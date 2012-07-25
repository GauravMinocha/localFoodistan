package minocha.foodistan.manager;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import minocha.foodistan.chef.Chef;
import minocha.foodistan.chef.Chef.chefStatus;
import minocha.foodistan.chef.ChefService;
import minocha.foodistan.chef.ChefServiceImpl;
import minocha.foodistan.foodie.Foodie;
import minocha.foodistan.foodie.Foodie.Status;
import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.*;
import minocha.foodistan.order.Order;
import minocha.foodistan.salesCounter.*;
import minocha.foodistan.salesCounter.SalesCounter.status;

//Assumptions 
//	1.  Foodie order a particular type of item.
//	2.  Chef can produce a particular type of item.
//	3.  
public class Foodistan {

	private static Foodistan ref;
	public int burgerNeeded;
	public long discount;
	public int ordersRecieved;
	public int ordersDelivered;
	public int burgersWasted;
	public long avgWaitingTime;
	public long maxWaitingTime;
	public long avgItemLifeTime;
	private Manager mg = new ManagerImpl();
    public  ArrayList<SalesCounter> salesCounters = new ArrayList<SalesCounter>(); 
    public Queue<Foodie> foodies = new LinkedList<Foodie>();
    public Queue<Chef> chefs = new LinkedList<Chef>();
	public Queue<Order> odrsOnHold = new LinkedList<Order>();
	public Queue<Foodie> foodiesOnHold = new LinkedList<Foodie>();
	public Queue<Order> completeOrders = new LinkedList<Order>();
	public Inventory InvBurgr = new Inventory();
	//public ArrayList<Inventory> invList = new ArrayList<Inventory>();
	
    public int getBurgersWasted() {
		return burgersWasted;
	}
	public Queue<Foodie> getFoodies() {
		return foodies;
	}
	public void setFoodies(Queue<Foodie> foodies) {
		this.foodies = foodies;
	}
	public Queue<Chef> getChefs() {
		return chefs;
	}
	public void setChefs(Queue<Chef> chefs) {
		this.chefs = chefs;
	}
	public int getBurgerNeeded() {
		return burgerNeeded;
	}
	public void setBurgerNeeded(int burgerNeeded) {
		this.burgerNeeded = burgerNeeded;
	}
	public Inventory getInvBurgr() {
		return InvBurgr;
	}
	public void setInvBurgr(Inventory invBurgr) {
		InvBurgr = invBurgr;
	}
	public void setBurgersWasted(int burgersWasted) {
		this.burgersWasted = burgersWasted;
	}
	public int getOrdersRecieved() {
		return ordersRecieved;
	}
	public void setOrdersRecieved(int ordersRecieved) {
		this.ordersRecieved = ordersRecieved;
	}
	public int getOrdersDelivered() {
		return ordersDelivered;
	}
	public void setOrdersDelivered(int ordersDelivered) {
		this.ordersDelivered = ordersDelivered;
	}
	public Queue<Order> getOdrsOnHold() {
		return odrsOnHold;
	}
	public void setOdrsOnHold(Queue<Order> odrsOnHold) {
		this.odrsOnHold = odrsOnHold;
	}

	private ChefService cfServ = new ChefServiceImpl(); 
    
	public SalesCounter getSalesCounter() {
		int i = 0;
		while(this.salesCounters.get(i).getCounterStatus() == status.BUSY){
		i++;
		}
		return this.salesCounters.get(i);
	}
	public void setSalesCounters(ArrayList<SalesCounter> salesCounters) {
		this.salesCounters = salesCounters;
	}

	public Manager getMg() {
		return mg;
	}
	public void setMg(Manager mg) {
		this.mg = mg;
	}
	public static synchronized Foodistan getfoodistan(){
		
		if(ref == null)
			ref = new Foodistan();
		return ref;
	}
	
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
		} 
	
	public static void main(String args[])
	{	   	
		int foodies = 0;
		Foodistan fdistan = getfoodistan();
		//java fs {F1, F2, F3 ...} {C1, C2, C3 .....} n
		//;
		int i;
		for (i=0; i< args.length;)
		{
			if(args[i] == "{"){
				foodies++;
				if(foodies==1){
					while(args[i] != "}"){
				    i++;
				    Foodie fd = new Foodie(Integer.parseInt(args[i]), ItemType.BURGER, 300000);
					fdistan.foodies.add(fd);
								}
							}
				else
					while(args[i]!="}"){
						i++;
						Chef cf = new Chef(ItemType.BURGER, 300000);
						fdistan.chefs.add(cf);
					}
			}
			
		}
		
		long endTime = Long.parseLong(args[i+2])*60000 + System.currentTimeMillis();
		
		
		SalesCounter s1 = new SalesCounter(1);
		fdistan.salesCounters.add(s1);
		
		while(System.currentTimeMillis()<= endTime){
			// Thread 1 - to order 
		//ThreadA ta = new ThreadA ();
		//ThreadB tb = new ThreadB ();
			//ta.start();
			//tb.start();
     	Foodie fx = fdistan.getFoodies().poll();
		if( fx.getFoodieStatus()== Status.HUNGRY)
		{			
		Item item = fx.requestOrder(fdistan.getSalesCounter(), fx.getItmTyp(), 1, 0);
		if (item ==null){
			fx.setWaitStartTime(System.currentTimeMillis());
			fx.setFoodieStatus(Status.WAITING);
			Foodistan.getfoodistan().foodiesOnHold.add(fx);
  		}
		else 
		fx.consumeItem(item);
		}
		else 
		{
			fdistan.getFoodies().add(fx);
		}
				
	    //Thread 2 - to produce 
		Chef cx = fdistan.getChefs().poll();
		if( cx.getcStatus()== chefStatus.FREE)
	   	    fdistan.getMg().refillInventory(cx.cookItem(ItemType.BURGER));
		else 
			fdistan.getChefs().add(cx);
	    //Thread 3 - to fullfill the order if inventory is available 
	    
	    
	    //Thread 4 to remove the stale items 

		}
	}
}

class ThreadA extends Thread {
 
    public void run() {
    	
    	Foodistan fdistan= Foodistan.getfoodistan();
		Foodie fx = fdistan.getFoodies().poll();
		if( fx.getFoodieStatus()== Status.HUNGRY)
		{			
		Item item = fx.requestOrder(fdistan.getSalesCounter(), fx.getItmTyp(), 1, 0);
		if (item ==null){
			fx.setWaitStartTime(System.currentTimeMillis());
			fx.setFoodieStatus(Status.WAITING);
			Foodistan.getfoodistan().foodiesOnHold.add(fx);
			fdistan.getFoodies().add(fx);
  		}
		else 
		fx.consumeItem(item);
		fdistan.getFoodies().add(fx);
		}
		else 
		{
			fdistan.getFoodies().add(fx);
		}
    	
        //Code
    }
}

class ThreadB extends Thread {

    public void run() {
    	Foodistan fdistan= Foodistan.getfoodistan();
    	Chef cx = fdistan.getChefs().poll();
		if( cx.getcStatus()== chefStatus.FREE)
	   	    fdistan.getMg().refillInventory(cx.cookItem(ItemType.BURGER));
		else 
			fdistan.getChefs().add(cx);
        //Code
    }
}
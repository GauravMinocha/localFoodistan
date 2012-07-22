package minocha.foodistan.manager;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import minocha.foodistan.chef.Chef;
import minocha.foodistan.chef.ChefService;
import minocha.foodistan.chef.ChefServiceImpl;
import minocha.foodistan.foodie.Foodie;
import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.*;
import minocha.foodistan.order.Order;
import minocha.foodistan.salesCounter.*;
import minocha.foodistan.salesCounter.SalesCounter.status;

public class Foodistan {

	private static Foodistan ref; 
	private Manager mg = new ManagerImpl();
    public  ArrayList<SalesCounter> salesCounters = new ArrayList<SalesCounter>(); 
	public Queue<Order> odrsOnHold = new LinkedList<Order>();
	public Queue<Foodie> foodiesOnHold = new LinkedList<Foodie>();
	public Queue<Order> completeOrders = new LinkedList<Order>();
    public ArrayList<Inventory> getInvList() {
		return invList;
	}
	public void setInvList(ArrayList<Inventory> invList) {
		this.invList = invList;
	}

	public ArrayList<Inventory> invList = new ArrayList<Inventory>();
    
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
	
	public static void main(String arg [])
	{
	   
		Foodie f= new Foodie();			
		Foodistan fdistan = getfoodistan();
		SalesCounter s1 = new SalesCounter(1);
		fdistan.salesCounters.add(s1);
		Item item = f.requestOrder(fdistan.getSalesCounter(), null, 0);
		if (item ==null)
			Foodistan.getfoodistan().foodiesOnHold.add(f);
		else 
			f.consumeItem(item);
		Chef cf = new Chef();
	    fdistan.mg.refillInventory(fdistan.cfServ.cookItem(cf, ItemType.BURGER));
				
		//Thread for foodie order
		
		//Thread for chef production
		
		//
		
	}
}

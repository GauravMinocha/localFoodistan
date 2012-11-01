package minocha.foodistan.manager;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import minocha.foodistan.chef.Chef;
import minocha.foodistan.chef.Chef.chefStatus;

import minocha.foodistan.foodie.Foodie;

import minocha.foodistan.foodie.Foodie.foodieStatus;
import minocha.foodistan.inventory.Inventory;
import minocha.foodistan.item.*;
import minocha.foodistan.order.Order;
import minocha.foodistan.order.Order.orderStatus;
import minocha.foodistan.salesCounter.*;

//Assumptions 
//	1.  Foodie order a particular type of item.
//	2.  Chef can produce a particular type of item.
//	3.  
public class Foodistan {

	private static final Foodistan ref = new Foodistan();
	private int speedUp;
	int speedFactor;
	int speedDown;
	long foodistanEndTime;
	public int burgerNeeded;
	public long discount;
	public int ordersReceived;
	public int ordersDelivered;
	public int burgersWasted;
	public long avgWaitingTime;
	public long maxWaitingTime;
	public long avgItemLifeTime;
	public int deathPenalty = 0;
	public int avgDiscount = 0;
	private Manager mg = new ManagerImpl();
	public List<SalesCounter> salesCounters = new Vector<SalesCounter>(); 
	public List<Foodie> foodies = new Vector<Foodie>();
	public List<Chef> chefs = new Vector<Chef>();
	public List<Order> odrsOnHold = new Vector<Order>();
	public List<Foodie> foodiesOnHold = new Vector<Foodie>();
	public List<Order> completeOrders = new Vector<Order>();
	public Inventory Inv = new Inventory();

	public static synchronized Foodistan getfoodistan(){
		return ref;
	}

	private Foodistan(){
		System.out.println(Thread.currentThread().getName()+ " Created new Foodistan Object");
		speedUp=1;
		speedFactor = 50;
	}

	public SalesCounter getSalesCounter() {
		return this.getSalesCounters().get(0);
		// to be added when sales counter are increased 
		/*	boolean found = false;
		int i = 0;
	for(; i< this.getSalesCounters().size(); i++){
		if(this.getSalesCounters().get(i).getCounterStatus() == counterStatus.BUSY){
			found = true;
			return this.salesCounters.get(i);
		}
	}
	 if(found==false) 
	   return null;*/
	}

	public List<SalesCounter> getSalesCounters() {
		return salesCounters;
	}

	public void setSalesCounters(List<SalesCounter> salesCounters) {
		this.salesCounters = salesCounters;
	}

	public List<Foodie> getFoodies() {
		return foodies;
	}

	public void setFoodies(List<Foodie> foodies) {
		this.foodies = foodies;
	}

	public List<Chef> getChefs() {
		return chefs;
	}

	public void setChefs(List<Chef> chefs) {
		this.chefs = chefs;
	}

	public List<Order> getOdrsOnHold() {
		return odrsOnHold;
	}

	public void setOdrsOnHold(List<Order> odrsOnHold) {
		this.odrsOnHold = odrsOnHold;
	}

	public List<Foodie> getFoodiesOnHold() {
		return foodiesOnHold;
	}

	public void setFoodiesOnHold(List<Foodie> foodiesOnHold) {
		this.foodiesOnHold = foodiesOnHold;
	}

	public List<Order> getCompleteOrders() {
		return completeOrders;
	}

	public void setCompleteOrders(List<Order> completeOrders) {
		this.completeOrders = completeOrders;
	}

	public synchronized int getSpeedUp() {
		return speedUp;
	}

	public synchronized void setSpeedUp(int speedUp) {
		this.speedUp = speedUp;
	}

	public int getSpeedFactor() {
		return speedFactor;
	}

	public void setSpeedFactor(int speedFactor) {
		this.speedFactor = speedFactor;
	}

	public int getAvgDiscount() {
		return avgDiscount;
	}

	public void setAvgDiscount(int avgDiscount) {
		this.avgDiscount = avgDiscount;
	}

	public int getSpeedDown() {
		return speedDown;
	}

	public void setSpeedDown(int speedDown) {
		this.speedDown = speedDown;
	}

	public int getBurgersWasted() {
		return burgersWasted;
	}

	public long getDiscount() {
		return discount;
	}

	public void setDiscount(long discount) {
		this.discount = discount;
	}

	public long getAvgWaitingTime() {
		return avgWaitingTime;
	}

	public void setAvgWaitingTime(long avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}

	public long getMaxWaitingTime() {
		return maxWaitingTime;
	}

	public void setMaxWaitingTime(long maxWaitingTime) {
		this.maxWaitingTime = maxWaitingTime;
	}

	public long getAvgItemLifeTime() {
		return avgItemLifeTime;
	}

	public void setAvgItemLifeTime(long avgItemLifeTime) {
		this.avgItemLifeTime = avgItemLifeTime;
	}



	public void setOdrsOnHold(ArrayList<Order> odrsOnHold) {
		this.odrsOnHold = odrsOnHold;
	}



	public void setFoodiesOnHold(ArrayList<Foodie> foodiesOnHold) {
		this.foodiesOnHold = foodiesOnHold;
	}


	public void setCompleteOrders(ArrayList<Order> completeOrders) {
		this.completeOrders = completeOrders;
	}



	public long getFoodistanEndTime() {
		return foodistanEndTime;
	}
	public void setFoodistanEndTime(long foodistanEndTime) {
		this.foodistanEndTime = foodistanEndTime;
	}
	public int getDeathPenalty() {
		return deathPenalty;
	}
	public void setDeathPenalty(int deathPenalty) {
		this.deathPenalty = deathPenalty;
	}

	public void setFoodies(ArrayList<Foodie> foodies) {
		this.foodies = foodies;
	}

	public void setChefs(ArrayList<Chef> chefs) {
		this.chefs = chefs;
	}
	public int getBurgerNeeded() {
		return burgerNeeded;
	}
	public void setBurgerNeeded(int burgerNeeded) {
		this.burgerNeeded = burgerNeeded;
	}

	public int getOrdersReceived() {
		return ordersReceived;
	}
	public void setOrdersReceived(int ordersReceived) {
		this.ordersReceived = ordersReceived;
	}
	public Inventory getInv() {
		return Inv;
	}
	public void setInv(Inventory inv) {
		Inv = inv;
	}
	public void setBurgersWasted(int burgersWasted) {
		this.burgersWasted = burgersWasted;
	}

	public int getOrdersDelivered() {
		return ordersDelivered;
	}
	public void setOrdersDelivered(int ordersDelivered) {
		this.ordersDelivered = ordersDelivered;
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


	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	} 


}



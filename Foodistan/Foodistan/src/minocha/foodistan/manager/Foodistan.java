package minocha.foodistan.manager;

import java.util.ArrayList;
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
	private Manager mg = new ManagerImpl();
	public ArrayList<SalesCounter> salesCounters = new ArrayList<SalesCounter>(); 
	public ArrayList<Foodie> foodies = new ArrayList<Foodie>();
	public ArrayList<Chef> chefs = new ArrayList<Chef>();
	public ArrayList<Order> odrsOnHold = new ArrayList<Order>();
	public ArrayList<Foodie> foodiesOnHold = new ArrayList<Foodie>();
	public ArrayList<Order> completeOrders = new ArrayList<Order>();
	public Inventory Inv = new Inventory();

	public static synchronized Foodistan getfoodistan(){
		return ref;
	}

	private Foodistan(){
		System.out.println(Thread.currentThread().getName()+ " Created new Foodistan Object");
		speedUp=1;
	}

	public SalesCounter getSalesCounter() {
		return this.getSalesCounters().get(0);
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

	public synchronized int getSpeedUp() {
		return speedUp;
	}

	public synchronized void setSpeedUp(int speedUp) {
		this.speedUp = speedUp;
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

	public ArrayList<Order> getOdrsOnHold() {
		return odrsOnHold;
	}

	public void setOdrsOnHold(ArrayList<Order> odrsOnHold) {
		this.odrsOnHold = odrsOnHold;
	}

	public ArrayList<Foodie> getFoodiesOnHold() {
		return foodiesOnHold;
	}

	public void setFoodiesOnHold(ArrayList<Foodie> foodiesOnHold) {
		this.foodiesOnHold = foodiesOnHold;
	}

	public ArrayList<Order> getCompleteOrders() {
		return completeOrders;
	}

	public void setCompleteOrders(ArrayList<Order> completeOrders) {
		this.completeOrders = completeOrders;
	}

	public ArrayList<SalesCounter> getSalesCounters() {
		return salesCounters;
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
	public ArrayList<Foodie> getFoodies() {
		return foodies;
	}
	public void setFoodies(ArrayList<Foodie> foodies) {
		this.foodies = foodies;
	}
	public ArrayList<Chef> getChefs() {
		return chefs;
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


	public static void main(String[] args)
	{	   	
		int foodies = 0;
		Foodistan fdistan = getfoodistan();
		//java fs {F1, F2, F3 ...} {C1, C2, C3 .....} n
		//;
		int i=0;
		int curl=0;
		int curlC=0;

		for(String s: args){
			if(s.equalsIgnoreCase("{")){
				curl++;
			}
			if(s.equalsIgnoreCase("}")){
				curlC++;
			}
			if(((!s.equalsIgnoreCase("{"))) && curl == 1 && curlC !=1){
				//System.out.println(s);
				if(Integer.parseInt(s) < 0 || Integer.parseInt(s) > 100){
					System.out.println("Invalid Foodie Discount" + s);
					System.exit(1);
				}
				Foodie fd1 = new Foodie(Integer.parseInt(s), ItemType.BURGER, 300000l);
				fdistan.foodies.add(fd1);
			}
			if(((!s.equalsIgnoreCase("}"))&&(!s.equalsIgnoreCase("{"))) && curl == 2 && curlC != 2){
				//System.out.println(s+"hello");
				
				if(Long.parseLong(s) == 0){
					System.out.println("Invalid chef cooking time" + s);
					System.exit(1);
				}
				
				long cookTime = 3600000l/Long.parseLong(s);
				Chef cf = new Chef(ItemType.BURGER, cookTime);
				fdistan.chefs.add(cf);
			} 
			if(curlC==2 && !s.equalsIgnoreCase("}")){
				Foodistan.getfoodistan().setFoodistanEndTime(Long.parseLong(s)*60000l + System.currentTimeMillis());
				System.out.println(s+"heelo");

			}  
		}

		SalesCounter s1 = new SalesCounter(1);
		fdistan.salesCounters.add(s1);
		int j;
		for (j=0;j<fdistan.getChefs().size();j++) {
			fdistan.chefs.get(j).cookItem(ItemType.BURGER);
		}

		//fdistan.setSpeedUp(1);

		ThreadA ta = new ThreadA ();
		ThreadB tb = new ThreadB ();
		ThreadC tc = new ThreadC ();
		ThreadD td = new ThreadD ();
		ThreadE te = new ThreadE ();
		ThreadF tf = new ThreadF ();
		ThreadG tg = new ThreadG ();
		ThreadM tm = new ThreadM ();
		ThreadN tn = new ThreadN ();
		// Thread 1 - to order 

		ta.start();
		tb.start();
		tc.start();
		td.start();
		te.start();
		tf.start();
		tg.start();
		//tm.start();
		tn.start();

		if(System.currentTimeMillis() > Foodistan.getfoodistan().getFoodistanEndTime()){
			System.out.println(Foodistan.getfoodistan().getOrdersReceived()+ " Final Orders Received");
			System.out.println(Foodistan.getfoodistan().getOrdersDelivered()+ " Final Orders Delivered");
			System.out.println(Foodistan.getfoodistan().getBurgersWasted()+ " Final Burgers Wasted");
			System.out.println(Foodistan.getfoodistan().getDeathPenalty()+ " Final Foodie died");
			System.out.println(Foodistan.getfoodistan().getOdrsOnHold().size() + " Final Orders on hold");
			System.out.println(Foodistan.getfoodistan().getFoodiesOnHold().size() + " Final Foodies on hold");
			System.out.println(Foodistan.getfoodistan().getInv().countItem()+ " Final Inventory");
			System.out.println(Foodistan.getfoodistan().getBurgerNeeded() + " Final Burgers needed");
			System.out.println(Foodistan.getfoodistan().getMg().calculateDiscount() + " Final discount");
			System.out.println(Foodistan.getfoodistan().getAvgWaitingTime() + " Final avg waiting time in minutes");

		}
	}
}
//Thread A : To check if the chef has cooked the food, if yes set it's status to FREE
class ThreadA extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){
			for (int k=0;k<fdistan.getChefs().size();k++) {
				Chef c = fdistan.chefs.get(k);
				if (((c.getcStatus() == chefStatus.BUSY) && (c.getCookStartTime() + (c.getCurrentCookTime())) < System.currentTimeMillis())){
					Item itm = new Item(ItemType.BURGER);
					fdistan.getMg().refillInventory(itm);
					fdistan.chefs.get(k).setcStatus(chefStatus.SPEEDUPDATE);
					fdistan.chefs.get(k).setCurrentCookTime(fdistan.chefs.get(k).getCurrentCookTime()*fdistan.getSpeedUp());
				}
			}
		}
	}
}
//Thread G : to check if a chef is FREE, if yes order him to cook again
class ThreadG extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){
			for (int l=0;l<fdistan.getChefs().size();l++) {
				Chef c = fdistan.chefs.get(l);
				if (c.getcStatus() == chefStatus.FREE){
					fdistan.getChefs().get(l).cookItem(ItemType.BURGER);

				}
			}
		}
	}
}
// Thread C: To check if any foodie is HUNGRY. If yes, place an order 
class ThreadC extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int m=0; m<fdistan.getFoodies().size(); m++ ){
				Foodie f = fdistan.foodies.get(m);
				if(f.getfStatus()==foodieStatus.HUNGRY){
					Item item = f.requestOrder(fdistan.getSalesCounter(), f.getItmTyp(), 1, f.getFoodieDiscount());	
					if (item ==null){
						fdistan.foodies.get(m).setWaitStartTime(System.currentTimeMillis());
						fdistan.foodies.get(m).setfStatus(foodieStatus.WAITING);
						Foodistan.getfoodistan().foodiesOnHold.add(f);
					}
					else 
						f.consumeItem(item);
				}
			}   	
		}			
	}
}

// Thread B: To check if a foodie has finished eating. If yes, change it's status to HUNGRY  
class ThreadB extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int n=0;n<fdistan.getFoodies().size();n++) {
				Foodie f = fdistan.foodies.get(n);
				if((f.getfStatus()==foodieStatus.EATING) && (f.getEatStartTime()+f.getEatTime() < System.currentTimeMillis())){
					f.setfStatus(foodieStatus.HUNGRY);
				}

			}
		}
	}
}

//Thread F: To check if a foodie is WAITING. If yes, check if inventory is available and the current discount matches his discount, 
//if yes fullfil his demand and change his status to eating

class ThreadF extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int x=0;x<fdistan.getFoodies().size();x++) {
				Foodie f = fdistan.getFoodies().get(x);
				if(f.getfStatus() == foodieStatus.WAITING){

					if((fdistan.getMg().hasInventory(Foodistan.getfoodistan().getInv(), 1)==false) || (fdistan.getMg().calculateDiscount() < f.getFoodieDiscount())){
					}
					else {
						fdistan.setAvgWaitingTime(fdistan.getAvgWaitingTime()+(System.currentTimeMillis()-f.getWaitStartTime()));  
						fdistan.getFoodies().get(x).consumeItem(fdistan.getMg().getItem(fdistan.getInv(), 1));
						fdistan.getOdrsOnHold().remove(0).setOrdrStatus(orderStatus.COMPLETE);
						fdistan.getFoodiesOnHold().remove(0);
						fdistan.setBurgerNeeded(fdistan.getBurgerNeeded()-1);
						fdistan.setOrdersDelivered(Foodistan.getfoodistan().getOrdersDelivered()+1);
					}
				}
			} 
		}        
	}
}

//Thread D: To check if a foodie is dead, if yes change it's status to DEAD   
class ThreadD extends Thread {
	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int z=0;z<fdistan.getFoodies().size();z++) {
				Foodie f = fdistan.foodies.get(z);
				if (f.getfStatus() == foodieStatus.WAITING){
				/*	if((f.getMaxWaitTime() + f.getWaitStartTime()) - System.currentTimeMillis() < 10000l)
					{
						if(fdistan.getMg().hasInventory(fdistan.getInv(), 1)){
							fdistan.setAvgWaitingTime(fdistan.getAvgWaitingTime()+(System.currentTimeMillis()-f.getWaitStartTime()));  
							fdistan.getFoodies().get(z).consumeItem(fdistan.getMg().getItem(fdistan.getInv(), 1));
							fdistan.getOdrsOnHold().remove(0).setOrdrStatus(orderStatus.COMPLETE);
							fdistan.getFoodiesOnHold().remove(0);
							fdistan.setBurgerNeeded(fdistan.getBurgerNeeded()-1);
							fdistan.setOrdersDelivered(Foodistan.getfoodistan().getOrdersDelivered()+1);	
										
						}
						
					}*/
					if ((f.getMaxWaitTime() + f.getWaitStartTime()) < System.currentTimeMillis()){
						fdistan.foodies.get(z).setfStatus(foodieStatus.DEAD);
						fdistan.setAvgWaitingTime(fdistan.getAvgWaitingTime()+(f.getMaxWaitTime()));
						fdistan.setDeathPenalty(fdistan.getDeathPenalty()+1);
					}
				}
			}
		}
	}
}


// To display output
class ThreadE extends Thread {

	public void run() {
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 

			//System.out.println("Thread E");
			// Thread 5: To display the output
			/*  Output Specifications
      	•	For the time being, you need to use Logging Messages as your output
      	•	Following stats must be clearly indicated at every step of your algorithm
      	•	Total Number of Orders Received (Maximize) 
      	•	Total Number of Orders Delivered (Maximize)
      	•	Total Numbers of Burgers Wasted (Minimize)
      	•	Average Waiting Time / Order Delivered (Minimize)
      	•	Maximum Waiting Time (Minimize)
      	•	Average life of Burgers / Order Delivered (Minimize)
      	•	I will assign weights to above parameters and share a definitive formula to decide the best solution*/

			System.out.println(Foodistan.getfoodistan().getOrdersReceived()+ " Orders Received");
			System.out.println(Foodistan.getfoodistan().getOrdersDelivered()+ " Orders Delivered");
			System.out.println(Foodistan.getfoodistan().getBurgersWasted()+ " Burgers Wasted");
			System.out.println(Foodistan.getfoodistan().getDeathPenalty()+ " Foodie died");
			System.out.println(Foodistan.getfoodistan().getOdrsOnHold().size() + " Orders on hold");
			System.out.println(Foodistan.getfoodistan().getFoodiesOnHold().size() + " Foodies on hold");
			System.out.println(Foodistan.getfoodistan().getInv().countItem()+ " Inventory");
			System.out.println(Foodistan.getfoodistan().getBurgerNeeded() + " Burgers needed");
			System.out.println(Foodistan.getfoodistan().getMg().calculateDiscount() + " Discount");
			//System.out.println(Foodistan.getfoodistan().getSpeedUp() + " speedup");
			try{
				System.out.println((Foodistan.getfoodistan().getAvgWaitingTime()/(Foodistan.getfoodistan().getOrdersReceived())) + " Avg. waiting time in milli seconds");
			}
			catch(ArithmeticException a){
				System.out.println( 0 +" Avg. waiting time in milli-seconds");

			}
			//System.out.println(Foodistan.getfoodistan().getMg().calculateDiscount() + " Discount");
		}

	}

}

// to manipulate chef speed
class ThreadM extends Thread {
	public void run() {

		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			if((fdistan.getInv().countItem() > (fdistan.getBurgerNeeded()+fdistan.getFoodies().size()))){ 
				  int speedHold=fdistan.getSpeedUp()*2;
	                 System.out.println(speedHold + "from 2nd loop");
	                 if(speedHold<4){
					fdistan.setSpeedUp(speedHold);
	                 }
		
			}
			if((!fdistan.getInv().items.isEmpty() && (fdistan.getMg().calculateDiscount() > 40)) && fdistan.getSpeedUp()>2 ){
				// increase speed 
				int speed=fdistan.getSpeedUp()/2;
				System.out.println(speed + " from 1st loop");
				fdistan.setSpeedUp(speed!= 0? speed : 1);
				// decrease speed
			}
		}
	}
}


//
class ThreadN extends Thread {
	public void run() {

		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int k=0;k<fdistan.getChefs().size();k++) {
				Chef c = fdistan.chefs.get(k);
				if (c.getcStatus() == chefStatus.SPEEDUPDATE){
					if((fdistan.getInv().countItem() > (fdistan.getBurgerNeeded()+fdistan.getFoodies().size()))){
					    long changeUpTime = c.getCurrentCookTime()*2;
					    if(changeUpTime <= 3600000){
					    	fdistan.chefs.get(k).setCurrentCookTime(changeUpTime);
					    }
					    else {
					    	fdistan.chefs.get(k).setCurrentCookTime(fdistan.chefs.get(k).getDefaultCookTime());	
					 	    }
					}
					if((!fdistan.getInv().items.isEmpty() && (fdistan.getMg().calculateDiscount() > 40))){
					    long changeDownTime = c.getCurrentCookTime()/2;
						if(	changeDownTime > 1){
							
							fdistan.chefs.get(k).setCurrentCookTime(changeDownTime);	
						}
						  else {
						    	fdistan.chefs.get(k).setCurrentCookTime(fdistan.chefs.get(k).getDefaultCookTime());	
						 	    }
					}
					fdistan.chefs.get(k).setcStatus(chefStatus.FREE);
				   }
				}
			}
			
		}
}
	

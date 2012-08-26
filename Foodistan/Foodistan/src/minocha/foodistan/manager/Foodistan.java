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


	/**
	 * @param args
	 */
	public static void main(String[] args)
	{	
		// to parse the input with validation checks
		Foodistan fdistan = getfoodistan();
		//java fs {F1, F2, F3 ...} {C1, C2, C3 .....} n
		//;
		int curl=0;
		int curlC=0;
		String delimiter=",";

		try{for(String s: args){
			String[] temp = s.split(delimiter);


			if(temp[0].equalsIgnoreCase("{")){
				curl++;
			}
			if(temp[0].equalsIgnoreCase("}")){
				curlC++;
			}
			if(((!temp[0].equalsIgnoreCase("{"))) && curl == 1 && curlC !=1){
				//System.out.println(s);
				if(Integer.parseInt(temp[0]) < 0 || Integer.parseInt(temp[0]) > 100){
					System.out.println("Invalid Foodie Discount" + temp[0]);
					System.exit(1);
				}
				Foodie fd1 = new Foodie(Integer.parseInt(temp[0]), ItemType.BURGER, 300000l);
				fdistan.foodies.add(fd1);
			}
			if(((!temp[0].equalsIgnoreCase("}"))&&(!s.equalsIgnoreCase("{"))) && curl == 2 && curlC != 2){
				//System.out.println(s+"hello");

				if(Long.parseLong(temp[0]) <= 0){
					System.out.println("Invalid chef cooking speed " + temp[0]);
					System.exit(1);
				}

				long cookTime = 3600000l/Long.parseLong(temp[0]);
				Chef cf = new Chef(ItemType.BURGER, cookTime);
				fdistan.chefs.add(cf);
			} 
			if(curlC==2 && !temp[0].equalsIgnoreCase("}")){
				if(temp[0] == "" || Long.parseLong(temp[0]) <=0){
					System.out.println("Please enter correct time");
					System.exit(1);

				}
				Foodistan.getfoodistan().setFoodistanEndTime(Long.parseLong(temp[0])*60000l + System.currentTimeMillis());
				//System.out.println(s+"heelo");

			}  
		}
		}catch(NumberFormatException e){
			System.out.println(" please check the imput format");
			System.exit(1);
		}

		if( fdistan.getFoodies().size() == 0 || fdistan.getChefs().size() == 0){
			System.out.println(" Either chef or foodies not present");
			System.exit(1);
		}

		SalesCounter s1 = new SalesCounter(1);
		fdistan.salesCounters.add(s1);
		int j;
		for (j=0;j<fdistan.getChefs().size();j++) {
			fdistan.chefs.get(j).cookItem(ItemType.BURGER);
		}
		int f=0;

		for(f=0; f<fdistan.getFoodies().size(); f++){
			fdistan.setAvgDiscount(fdistan.getAvgDiscount()+fdistan.getFoodies().get(f).getFoodieDiscount());

		}
		fdistan.setAvgDiscount((int)(fdistan.getAvgDiscount()/fdistan.getFoodies().size()));

		if(fdistan.getAvgDiscount() > 50 && fdistan.getAvgDiscount() < 70)
		{
			fdistan.setSpeedFactor(75);

		}
		else if(fdistan.getAvgDiscount() > 70) 
		{

			fdistan.setSpeedFactor(95);
		}

		ThreadA ta = new ThreadA ();
		ThreadB tb = new ThreadB ();
		ThreadC tc = new ThreadC ();
		ThreadD td = new ThreadD ();
		ThreadE te = new ThreadE ();
		ThreadF tf = new ThreadF ();
		ThreadG tg = new ThreadG ();
		ThreadN tn = new ThreadN ();
		ThreadO to = new ThreadO ();
		ThreadK tk = new ThreadK ();
		// Thread 1 - to order 
		to.start();
		ta.start();
		tb.start();
		tc.start();
		td.start();
		te.start();
		tf.start();
		tg.start();
		tn.start();
		tk.start();


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
		long waitTime =0l;
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int x=0;x<fdistan.getFoodies().size();x++) {
				Foodie f = fdistan.getFoodies().get(x);
				if(f.getfStatus() == foodieStatus.WAITING){

					if((fdistan.getMg().hasInventory(Foodistan.getfoodistan().getInv(), 1)==false) || (fdistan.getMg().calculateDiscount() < f.getFoodieDiscount())){
					}
					else {
						waitTime = System.currentTimeMillis()-f.getWaitStartTime();
						if (waitTime > fdistan.getMaxWaitingTime()) fdistan.setMaxWaitingTime(waitTime);
						fdistan.setAvgWaitingTime(fdistan.getAvgWaitingTime()+waitTime);
						Item itm = fdistan.getMg().getItem(fdistan.getInv());
						fdistan.getFoodies().get(x).consumeItem(itm);
						fdistan.setAvgItemLifeTime(fdistan.getAvgItemLifeTime() + (System.currentTimeMillis() - itm.getItemStartTime()));
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
						fdistan.getFoodiesOnHold().remove(0);
						fdistan.getOdrsOnHold().remove(0);
						fdistan.setBurgerNeeded(fdistan.getBurgerNeeded()-1);
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


			System.out.println("Orders Received -------> " + Foodistan.getfoodistan().getOrdersReceived());
			System.out.println("Orders Delivered ------> " + Foodistan.getfoodistan().getOrdersDelivered());
			System.out.println("Burgers Wasted --------> " + Foodistan.getfoodistan().getBurgersWasted());
			System.out.println("Foodies Died ----------> " + Foodistan.getfoodistan().getDeathPenalty());
			System.out.println("Speed Factor ----------> " + Foodistan.getfoodistan().getSpeedFactor());
			System.out.println("Orders on Hold -------->" + Foodistan.getfoodistan().getOdrsOnHold().size());
			System.out.println("Foodies on Hold ------->" + Foodistan.getfoodistan().getFoodiesOnHold().size());
			System.out.println("Current Inventory ----->" + Foodistan.getfoodistan().getInv().countItem());
			//System.out.println("Burgers Needed -------->" + Foodistan.getfoodistan().getBurgerNeeded());
			System.out.println("Current Discount ------>" + Foodistan.getfoodistan().getMg().calculateDiscount());
			if(Foodistan.getfoodistan().getDeathPenalty()>1){
				System.out.println("Max. Wait Time(ms) ----> 600000");
			}
			else{
				System.out.println("Max. Wait Time(ms) ----> " + Foodistan.getfoodistan().getMaxWaitingTime());
			}
			try{
				System.out.println("Avg. Waiting Time(ms) -> " + (Foodistan.getfoodistan().getAvgWaitingTime()/(Foodistan.getfoodistan().getOrdersReceived())));
			}
			catch(ArithmeticException a){
				System.out.println("Avg. Waiting Time(ms) -> 0");
			}
			try{
				System.out.println("Avg. Burger Life(ms) --> " + (Foodistan.getfoodistan().getAvgItemLifeTime()/(Foodistan.getfoodistan().getOrdersDelivered() + Foodistan.getfoodistan().getBurgersWasted())));
			}catch (ArithmeticException a){
				System.out.println("Avg. Burger Life(ms) --> 0");

			}
			System.out.println("                          ");
			//System.out.println(Foodistan.getfoodistan().getMg().calculateDiscount() + " Discount");
		}

	}

}

// to manipulate chef speed
/*class ThreadM extends Thread {
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
 */
class ThreadN extends Thread {
	public void run() {

		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int k=0;k<fdistan.getChefs().size();k++) {
				Chef c = fdistan.chefs.get(k);

				// decreasing speed
				if(fdistan.getInv().items.size() > fdistan.getBurgerNeeded()){

					long changeUpTime = c.getBackUpCookTime()*2;
					if(changeUpTime <= 3600000){
						fdistan.chefs.get(k).setBackUpCookTime(changeUpTime);
					}
					else {
						
							fdistan.chefs.get(k).setBackUpCookTime(fdistan.chefs.get(k).getDefaultCookTime());
						}
				    }

				// increasing speed
				if(fdistan.getInv().items.size() < fdistan.getBurgerNeeded()){
					long changeDownTime = c.getBackUpCookTime()/2;

					if(	changeDownTime > 1){

						fdistan.chefs.get(k).setBackUpCookTime(changeDownTime);	
					}


				}

			}
		}
	}
}



class ThreadK extends Thread {
	public void run() {

		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 

			for (int k=0;k<fdistan.getChefs().size();k++) {
				Chef c = fdistan.chefs.get(k);
				if (c.getcStatus() == chefStatus.SPEEDUPDATE){

					fdistan.chefs.get(k).setCurrentCookTime(fdistan.chefs.get(k).getBackUpCookTime()); 
					fdistan.chefs.get(k).setcStatus(chefStatus.FREE);	

				}	


			}
		}

	}
}




//
/*class ThreadN extends Thread {
	public void run() {

		Foodistan fdistan = Foodistan.getfoodistan();
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int k=0;k<fdistan.getChefs().size();k++) {
				Chef c = fdistan.chefs.get(k);
				if (c.getcStatus() == chefStatus.SPEEDUPDATE){
					// decreasing speed
					if(fdistan.getInv().items.size() > fdistan.getBurgerNeeded()){
						long changeUpTime = c.getCurrentCookTime()*2;
						if(changeUpTime <= 3600000){
							fdistan.chefs.get(k).setCurrentCookTime(changeUpTime);
						}
						else {
							if(fdistan.getAvgDiscount()<40){
							fdistan.chefs.get(k).setCurrentCookTime(fdistan.chefs.get(k).getDefaultCookTime());	
							}

						}
					}
					// increasing speed
					if(fdistan.getInv().items.size() < fdistan.getBurgerNeeded()){
						long changeDownTime = c.getCurrentCookTime()/2;

						if(	changeDownTime > 1){

							fdistan.chefs.get(k).setCurrentCookTime(changeDownTime);	
						}


					}
					fdistan.chefs.get(k).setcStatus(chefStatus.FREE);
				}
			}
		}

	}
}*/

//Thread to remove stale burger
class ThreadO extends Thread{

	public void run(){
		Foodistan fdistan = Foodistan.getfoodistan();

		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){
			//System.out.println(fdistan.getInv().items.isEmpty());

			long waitTime = 180000L;
			if(!fdistan.getInv().items.isEmpty()){

				if (System.currentTimeMillis() > (180000L + fdistan.getInv().items.peek().getItemStartTime())){
					fdistan.getInv().items.poll();
					fdistan.setBurgersWasted(fdistan.getBurgersWasted()+1);
					fdistan.setAvgItemLifeTime(fdistan.getAvgItemLifeTime()+ItemType.BURGER.getLifeTime());
				}
				waitTime = (fdistan.getInv().items.peek().getItemStartTime()+180000L)- System.currentTimeMillis();

			}
			System.out.println("Stale burgers it ");	
			if(waitTime<0)
				continue;
			try{

				Thread.currentThread().sleep(waitTime);
			}catch (Exception e) {
				e.printStackTrace();
			}

			/*
			while(!(fdistan.getInv().items.isEmpty())){
			    System.out.println(fdistan.getInv().items.isEmpty());
				System.out.println("System time update " + System.currentTimeMillis());
				System.out.println("System time update " + fdistan.getInv().items.peek().getItemStartTime());
				System.out.println("System time update " + (System.currentTimeMillis() - fdistan.getInv().items.peek().getItemStartTime()));

				//System.out.println("Outside it ");
				if (System.currentTimeMillis() > (180000L + fdistan.getInv().items.peek().getItemStartTime())){
					fdistan.getInv().items.poll();
					System.out.println("inside it ");
					//jjSystem.exit(1);

					fdistan.setBurgersWasted(fdistan.getBurgersWasted()+1);
					fdistan.setAvgItemLifeTime(fdistan.getAvgItemLifeTime()+ItemType.BURGER.getLifeTime());
				}else
					System.out.println("outside it ");	
			}  */
		}

	}

}



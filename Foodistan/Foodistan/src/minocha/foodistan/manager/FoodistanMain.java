package minocha.foodistan.manager;

import minocha.foodistan.chef.Chef;
import minocha.foodistan.chef.Chef.chefStatus;
import minocha.foodistan.foodie.Foodie;
import minocha.foodistan.foodie.Foodie.foodieStatus;
import minocha.foodistan.item.Item;
import minocha.foodistan.item.Item.ItemType;
import minocha.foodistan.order.Order.orderStatus;
import minocha.foodistan.salesCounter.SalesCounter;

public class FoodistanMain {
	
	public static void main(String[] args)
	{	
		Foodistan fdistan = Foodistan.getfoodistan();
		//To parse the input with validation checks

		//INPUT: java fs {F1, F2, F3 ...} {C1, C2, C3 .....} n
		
		//Parsing the input 
		int oCurl=0;
		int cCurl=0;
		String delimiter=",";

		try{
			for(String s: args){
			String[] temp = s.split(delimiter);

			if(temp[0].equalsIgnoreCase("{")){
				oCurl++; // Opening braces flagged
			}
			if(temp[0].equalsIgnoreCase("}")){
				cCurl++; // Closing braces flagged
			}
			// Parsing Foodies 
			if(((!temp[0].equalsIgnoreCase("{"))) && oCurl == 1 && cCurl!=1){
				// Validation for Foodie discount 
			  if(Integer.parseInt(temp[0]) < 0 || Integer.parseInt(temp[0]) > 100){
					System.out.println("Invalid Foodie Discount" + temp[0]);
					System.exit(1);
			    }
				// Create and add new Foodie
				Foodie fd1 = new Foodie(Integer.parseInt(temp[0]), ItemType.BURGER, 300000l);
				fdistan.getFoodies().add(fd1);
			}
			//Parsing Chefs 
			//(!temp[0].equalsIgnoreCase("}"))
			if(((!s.equalsIgnoreCase("{"))) && oCurl == 2 && cCurl!= 2){
				    if(Long.parseLong(temp[0]) <= 0){
					System.out.println("Invalid chef cooking speed " + temp[0]);
					System.exit(1);
				}

				long cookTime = 3600000l/Long.parseLong(temp[0]);
				//Create and add new chef
				Chef cf = new Chef(ItemType.BURGER, cookTime);
				fdistan.getChefs().add(cf);
			} 
			if(cCurl==2 && !temp[0].equalsIgnoreCase("}")){
				if(temp[0] == "" || Long.parseLong(temp[0]) <=0){
					System.out.println("Please enter correct time");
					System.exit(1);
				}
				// Set Foodistan Run Time
				Foodistan.getfoodistan().setFoodistanEndTime(Long.parseLong(temp[0])*60000l + System.currentTimeMillis());
			}  
		}
		}catch(NumberFormatException e){
			System.out.println(" Please check the imput format");
			System.exit(1);
		}

		
		if( fdistan.getFoodies().size() == 0 || fdistan.getChefs().size() == 0){
			System.out.println(" Either chef or foodies not present");
			System.exit(1);
		}
		//Initialize Sales Counter
		SalesCounter s1 = new SalesCounter(1);
		fdistan.getSalesCounters().add(s1);
		
		//Send command to all the cooks, to start cooking
		for (int j=0;j<fdistan.getChefs().size();j++) {
			fdistan.getChefs().get(j).cookItem(ItemType.BURGER);
		}

		// Start the Threads
		ChefReadyForUpdate ta = new ChefReadyForUpdate ();
		InitiateChefProduction tb = new InitiateChefProduction ();
		CalculateChefProductivity tc = new CalculateChefProductivity ();
		UpdateChefProductivity td = new UpdateChefProductivity ();
		InitiateFoodieDemand tf = new InitiateFoodieDemand ();
		UpdateFoodieDemand tg = new UpdateFoodieDemand ();
		FulfillFoodieDemand tn = new FulfillFoodieDemand ();
		RemoveDeadFoodie to = new RemoveDeadFoodie ();
		RemoveStaleItem tk = new RemoveStaleItem ();
		DisplayOutput te = new DisplayOutput ();
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

//Thread A : To check if the chef has cooked the food, if yes set it's status to SPEEDUPDATE
class ChefReadyForUpdate extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){
			for (int k=0;k<fdistan.getChefs().size();k++) {
				
				Chef c = fdistan.getChefs().get(k);
				// Check if Chef has cooked the item
				if (((c.getcStatus() == chefStatus.BUSY) && (c.getCookStartTime() + (c.getCurrentCookTime())) < System.currentTimeMillis())){
					Item itm = new Item(ItemType.BURGER);
					fdistan.getMg().refillInventory(itm);
					//c.setcStatus(chefStatus.SPEEDUPDATE); // Why not this?
					c.setcStatus(chefStatus.SPEEDUPDATE);
					//fdistan.getChefs().get(k).setCurrentCookTime(fdistan.getChefs().get(k).getCurrentCookTime()*fdistan.getSpeedUp());
				}
			}
		}
	}
}

//Thread G : To check if a chef is FREE, if yes order him to cook
class InitiateChefProduction extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){
			for (int l=0;l<fdistan.getChefs().size();l++) {
				
				Chef c = fdistan.getChefs().get(l);
				if (c.getcStatus() == chefStatus.FREE){
				    //c.cookItem(ItemType.BURGER);  // Why not this?
					c.cookItem(ItemType.BURGER);
				}
			}
		}
	}
}

//Thread N: To calculate and store new speed of chefs
class CalculateChefProductivity extends Thread {
	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int k=0;k<fdistan.getChefs().size();k++) {
				
				Chef c = fdistan.getChefs().get(k);
				// decreasing speed
				if(fdistan.getInv().getItems().size() > fdistan.getBurgerNeeded()){
					long changeUpTime = c.getBackUpCookTime()*2;
					// if speed is less than an hour update else reset to default
					if(changeUpTime <= 3600000){
						c.setBackUpCookTime(changeUpTime);
					}
					else {
						c.setBackUpCookTime(fdistan.getChefs().get(k).getDefaultCookTime());
					}
				// increasing speed
				if(fdistan.getInv().getItems().size() < fdistan.getBurgerNeeded()){
					long changeDownTime = c.getBackUpCookTime()/2;
                    // if the speed is more than a second else don't change
					if(	changeDownTime > 1){
						c.setBackUpCookTime(changeDownTime);	
					}
				}
			}
		}
	}
  }
}

//Thread K : To check if a chef status is SPEEDUPDATE, if yes update speed and change chef status to FREE
class UpdateChefProductivity extends Thread {
	public void run() {
        Foodistan fdistan = Foodistan.getfoodistan();
		
        while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int k=0;k<fdistan.getChefs().size();k++) {	
				Chef c = fdistan.getChefs().get(k);
				
				if (c.getcStatus() == chefStatus.SPEEDUPDATE){
					//update speed 
					c.setCurrentCookTime(fdistan.getChefs().get(k).getBackUpCookTime()); 
					//set status to free 
					c.setcStatus(chefStatus.FREE);	
				}	
			}
		}
	}
}

// Thread C: To check if any foodie is HUNGRY. If yes, place an order 
class InitiateFoodieDemand extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int m=0; m<fdistan.getFoodies().size(); m++ ){
				Foodie f = fdistan.getFoodies().get(m);
				
				if(f.getfStatus()==foodieStatus.HUNGRY){
					Item item = f.requestOrder(fdistan.getSalesCounter(), f.getItmTyp(), 1, f.getFoodieDiscount());	
					if (item==null){
						f.setWaitStartTime(System.currentTimeMillis());
						f.setfStatus(foodieStatus.WAITING);
						fdistan.getFoodiesOnHold().add(f);
					}
					else 
						f.consumeItem(item);
				}
			}   	
		}			
	}
}

// Thread B: To check if a foodie has finished eating. If yes, change it's status to HUNGRY  
class UpdateFoodieDemand extends Thread {

	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int n=0;n<fdistan.getFoodies().size();n++) {
				Foodie f = fdistan.getFoodies().get(n);
				
				if((f.getfStatus()==foodieStatus.EATING) && (f.getEatStartTime()+f.getEatTime() < System.currentTimeMillis())){
					f.setfStatus(foodieStatus.HUNGRY);
				}

			}
		}
	}
}

//Thread F: To check if a foodie is WAITING. If yes, check if inventory is available and the current discount matches his discount, 
//if yes fullfil his demand and change his status to eating

class FulfillFoodieDemand extends Thread {

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
						//if Foodie is dead skip
						//if(waitTime<f.getMaxWaitTime()){  ??
						fdistan.setAvgWaitingTime(fdistan.getAvgWaitingTime()+waitTime);
						Item itm = fdistan.getMg().getItem(fdistan.getInv());
						f.consumeItem(itm);
						fdistan.setAvgItemLifeTime(fdistan.getAvgItemLifeTime() + (System.currentTimeMillis() - itm.getItemStartTime()));
						// Remove foodie, orders on hold; reduce the number of burgers needed by 1; increment the orders delivered 
						fdistan.getOdrsOnHold().remove(0).setOrdrStatus(orderStatus.COMPLETE); // Why is status set to Complete? 
						fdistan.getFoodiesOnHold().remove(0);
						fdistan.setBurgerNeeded(fdistan.getBurgerNeeded()-1);
						fdistan.setOrdersDelivered(Foodistan.getfoodistan().getOrdersDelivered()+1);
						//}
					}
				}
			} 
		}        
	}
}

//Thread D: To check if Foodie is dead, if yes change it's status to DEAD   
class RemoveDeadFoodie extends Thread {
	public void run() {
		Foodistan fdistan = Foodistan.getfoodistan();
		
		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){ 
			for (int z=0;z<fdistan.getFoodies().size();z++) {
				Foodie f = fdistan.getFoodies().get(z);
				
				if (f.getfStatus() == foodieStatus.WAITING){
						if ((f.getMaxWaitTime() + f.getWaitStartTime()) < System.currentTimeMillis()){
					    //Remove foodie, orders on hold; reduce the number of burgers needed by 1; increment the orders delivered ??
						f.setfStatus(foodieStatus.DEAD);
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

//Thread O: To remove stale burger
class RemoveStaleItem extends Thread{

	public void run(){
		Foodistan fdistan = Foodistan.getfoodistan();

		while(System.currentTimeMillis() <= Foodistan.getfoodistan().getFoodistanEndTime()){
		    long waitTime = 180000L;
			
		    if(!fdistan.getInv().getItems().isEmpty()){

				if (System.currentTimeMillis() > (180000L + fdistan.getInv().getItems().peek().getItemStartTime())){
					fdistan.getInv().getItems().poll();
					fdistan.setBurgersWasted(fdistan.getBurgersWasted()+1);
					fdistan.setAvgItemLifeTime(fdistan.getAvgItemLifeTime()+ItemType.BURGER.getLifeTime());
				}
				waitTime = (fdistan.getInv().getItems().peek().getItemStartTime()+180000L)- System.currentTimeMillis();

			}
			if(waitTime<0)
				continue;
			try{
				//Thread.currentThread().sleep(waitTime);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

// To display output
class DisplayOutput extends Thread {

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
			//System.out.println("Orders on Hold -------->" + Foodistan.getfoodistan().getOdrsOnHold().size());
			//System.out.println("Foodies on Hold ------->" + Foodistan.getfoodistan().getFoodiesOnHold().size());
			//System.out.println("Current Inventory ----->" + Foodistan.getfoodistan().getInv().countItem());
			//System.out.println("Current Discount ------>" + Foodistan.getfoodistan().getMg().calculateDiscount());
			if(Foodistan.getfoodistan().getDeathPenalty()>1){
				System.out.println("Max. Wait Time(ms) ----> 600000");
			}
			else{
				System.out.println("Max. Wait Time(ms) ----> " + Foodistan.getfoodistan().getMaxWaitingTime());
			}
			try{
				System.out.println("Avg. Waiting Time(ms) -> " + (Foodistan.getfoodistan().getAvgWaitingTime()/(Foodistan.getfoodistan().getOrdersReceived())));
				System.out.println("Avg. Waiting Time(ms) -> 0");
				System.out.println("Avg. Burger Life(ms) --> " + (Foodistan.getfoodistan().getAvgItemLifeTime()/(Foodistan.getfoodistan().getOrdersDelivered() + Foodistan.getfoodistan().getBurgersWasted())));
			}catch (ArithmeticException a){
				System.out.println("Avg. Burger Life(ms) --> 0");
			}
			System.out.println("                          ");
			
		}

	}

}







package minocha.foodistan.foodie;
import minocha.foodistan.item.Item;
import minocha.foodistan.item.Item.ItemType;

import minocha.foodistan.salesCounter.SalesCounter;


public class Foodie {

	private int foodieDiscount;
	private foodieStatus fStatus;
	private ItemType itmTyp;
	private long eatTime;
	private long eatStartTime;
	private long waitStartTime;
	private long maxWaitTime;

	public enum foodieStatus {HUNGRY, EATING, WAITING, DEAD} 
	public Foodie(int foodieDiscount, ItemType itmTyp, long eatTime) {
		super();
		this.setFoodieDiscount(foodieDiscount);
		this.setfStatus(foodieStatus.HUNGRY);
		this.setItmTyp(itmTyp);
		this.setEatTime(eatTime);
		this.setEatStartTime(0);
		this.setWaitStartTime(0);
		this.setMaxWaitTime(600000);
	}
   
	public Item requestOrder(SalesCounter sc, ItemType itemType, int quantity, int foodieDiscount){
		    return sc.recordOrder(itemType, quantity, foodieDiscount);
		 	}
	
	public void consumeItem(Item item) {
		this.setfStatus(foodieStatus.EATING);
		this.setEatStartTime(System.currentTimeMillis());
	}

	//getter & setters
	public long getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(long maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public long getEatStartTime() {
		return eatStartTime;
	}

	public void setEatStartTime(long eatStartTime) {
		this.eatStartTime = eatStartTime;
	}

	public long getEatTime() {
		return eatTime;
	}

	public void setEatTime(long eatTime) {
		this.eatTime = eatTime;
	}

	public int getFoodieDiscount() {
		return foodieDiscount;
	}

	public void setFoodieDiscount(int foodieDiscount) {
		this.foodieDiscount = foodieDiscount;
	}

	public foodieStatus getfStatus() {
		return fStatus;
	}

	public void setfStatus(foodieStatus fStatus) {
		this.fStatus = fStatus;
	}

	public ItemType getItmTyp() {
		return itmTyp;
	}

	public void setItmTyp(ItemType itmType) {
		this.itmTyp = itmType;
	}

	public long getWaitStartTime() {
		return waitStartTime;
	}

	public void setWaitStartTime(long waitStartTime) {
		this.waitStartTime = waitStartTime;
	}

	
	
}

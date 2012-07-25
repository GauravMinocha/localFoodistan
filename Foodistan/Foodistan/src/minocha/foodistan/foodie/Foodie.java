package minocha.foodistan.foodie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minocha.foodistan.item.Item;
import minocha.foodistan.item.ItemType;
import minocha.foodistan.manager.Foodistan;
import minocha.foodistan.salesCounter.SalesCounter;


public class Foodie {

	private int foodieDiscount;
	public enum Status {HUNGRY, EATING, WAITING} 
	private Status foodieStatus;
	private ItemType itmTyp;
	private long waitStartTime;
	private long eatTime;
	//private Map<ItemType, Long> foodieItemEatTime = new HashMap<ItemType, Long>();
	
	//public Item requestOrder(SalesCounter sc, Map<String, Integer> itemTypeQuantity, int foodieDiscount){
	//	sc.recordOrder(sc, itemTypeQuantity, foodieDiscount);
	//    return null;
	//}
	
	public Item requestOrder(SalesCounter sc, ItemType itemType, int quantity, int foodieDiscount){
		    return sc.recordOrder(itemType, quantity, foodieDiscount);
		 	}
	
	public void consumeItem(Item item) {
		
	try {
		Thread.sleep(this.eatTime);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	public Foodie(int foodieDiscount, ItemType itmTyp, long eatTime) {
		super();
		this.foodieDiscount = foodieDiscount;
		this.itmTyp = itmTyp;
		this.eatTime = eatTime;
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

	public Status getFoodieStatus() {
		return foodieStatus;
	}

	public void setFoodieStatus(Status foodieStatus) {
		this.foodieStatus = foodieStatus;
	}

	public ItemType getItmTyp() {
		return itmTyp;
	}

	public void setItmTyp(ItemType itmTyp) {
		this.itmTyp = itmTyp;
	}

	public long getWaitStartTime() {
		return waitStartTime;
	}

	public void setWaitStartTime(long waitStartTime) {
		this.waitStartTime = waitStartTime;
	}

	
	
}

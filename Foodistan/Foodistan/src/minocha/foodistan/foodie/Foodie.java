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
	public enum Status {FREE, EATING, WAITING} 
	private Status foodieStatus;
	private Map<ItemType, Long> foodieItemEatTime = new HashMap<ItemType, Long>();
	
	public Item requestOrder(SalesCounter sc, Map<String, Integer> itemTypeQuantity, int foodieDiscount){
		sc.recordOrder(sc, itemTypeQuantity, foodieDiscount);
	    return null;
	}
	
	public int getFoodieDiscount() {
		foodieItemEatTime.put(ItemType.BURGER,1234L);
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

	public void consumeItem(Item item) {
		
	}


	
	
}

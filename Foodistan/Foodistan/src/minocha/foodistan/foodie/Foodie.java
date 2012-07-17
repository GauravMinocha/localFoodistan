package minocha.foodistan.foodie;

import java.util.List;

public class Foodie {

	private int foodieEatTime;

	private List<String> foodieItem;
	private int foodieDiscount;
	public enum Status {FREE, BUSY, HOLD} 
	private Status foodieStatus;
	
	public int getFoodieEatTime() {
		return foodieEatTime;
	}
	public void setFoodieEatTime(int foodieEatTime) {
		this.foodieEatTime = foodieEatTime;
	}
	public List<String> getFoodieItem() {
		return foodieItem;
	}
	public void setFoodieItem(List<String> foodieItem) {
		this.foodieItem = foodieItem;
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
	
	
}

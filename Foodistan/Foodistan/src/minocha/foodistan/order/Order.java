package minocha.foodistan.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minocha.foodistan.item.ItemType;

public class Order {

	//private static int ordrId;
	//private Map<ItemType, Integer> orderItemTypeQuantity = new HashMap<ItemType, Integer>();
	private ItemType ordrItmTyp;
	private int ordrQuantity;
	private int ordrDiscount;
    public enum status {RELEASED, HOLD, COMPLETE};
    private status ordrStatus;  
    
	public Order(ItemType ordrItmTyp, int ordrQuantity, int ordrDiscount) {
		super();
		this.ordrItmTyp = ordrItmTyp;
		this.ordrQuantity = ordrQuantity;
		this.ordrDiscount = ordrDiscount;
		this.setOrdrStatus(status.RELEASED);
	}
	public ItemType getOrdrItmTyp() {
		return ordrItmTyp;
	}
	public void setOrdrItmTyp(ItemType ordrItmTyp) {
		this.ordrItmTyp = ordrItmTyp;
	}
	public int getOrdrQuantity() {
		return ordrQuantity;
	}
	public void setOrdrQuantity(int ordrQuantity) {
		this.ordrQuantity = ordrQuantity;
	}
	public int getOrdrDiscount() {
		return ordrDiscount;
	}
	public void setOrdrDiscount(int ordrDiscount) {
		this.ordrDiscount = ordrDiscount;
	}
	public status getOrdrStatus() {
		return ordrStatus;
	}
	public void setOrdrStatus(status ordrStatus) {
		this.ordrStatus = ordrStatus;
	}
    
 
}

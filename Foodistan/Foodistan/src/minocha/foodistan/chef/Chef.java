package minocha.foodistan.chef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minocha.foodistan.item.Item;
import minocha.foodistan.item.ItemType;

public class Chef {
		
	//private Map<ItemType, Long> chefItemTime = new HashMap<ItemType, Long>();
	//private Map<ItemType, Long> chefItemDefaultTime = new HashMap<ItemType, Long>();
	
	public enum chefStatus {FREE, BUSY} 
	private chefStatus cStatus;
	private ItemType chefItemType;
	private long currentCookTime;
	private long defaultCookTime;
	private long cookStartTime;

	public Chef(ItemType chefItemType, long defaultCookTime) {
		super();
		this.chefItemType = chefItemType;
		this.defaultCookTime = defaultCookTime;
		this.currentCookTime = defaultCookTime;
		this.setcStatus(chefStatus.FREE);
	}
	
	public void cookItem(ItemType itmType) {
	
	if(this.getChefItemType() == itmType){
	this.cStatus = chefStatus.BUSY;
    this.cookStartTime = System.currentTimeMillis();
   	}

    }
	
	public chefStatus getcStatus() {
		return cStatus;
	}

	public void setcStatus(chefStatus cStatus) {
		this.cStatus = cStatus;
	}

	public long getCookStartTime() {
		return cookStartTime;
	}

	public void setCookStartTime(long cookStartTime) {
		this.cookStartTime = cookStartTime;
	}

	public ItemType getChefItemType() {
		return chefItemType;
	}
	public void setChefItemType(ItemType chefItemType) {
		this.chefItemType = chefItemType;
	}
	public long getCurrentCookTime() {
		return currentCookTime;
	}
	public void setCurrentCookTime(long currentCookTime) {
		this.currentCookTime = currentCookTime;
	}
	public long getDefaultCookTime() {
		return defaultCookTime;
	}
	public void setDefaultCookTime(long defaultCookTime) {
		this.defaultCookTime = defaultCookTime;
	}

		
}

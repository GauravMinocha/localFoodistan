package minocha.foodistan.chef;

import minocha.foodistan.item.ItemType;

/* Chef entity:
 * chefItemType: A chef is skilled to prepare a particular Item
 * defautCookTime: Default time to cook a single item.
 * currentCookTime: Updated time to cook a single item.
 * cookStartTime: Time in milliSeconds when chef started cooking item
 * cStatus: 
 * FREE - ready to cook item
 * BUSY - cooking item
 * SPEEDUPDATE - ready to update its speed 
 */

public class Chef {

	//private Map<ItemType, Long> chefItemTime = new HashMap<ItemType, Long>();
	//private Map<ItemType, Long> chefItemDefaultTime = new HashMap<ItemType, Long>();

	public enum chefStatus {FREE, BUSY, SPEEDUPDATE} 
	private chefStatus cStatus;
	private ItemType chefItemType;
	private long currentCookTime;
	private long defaultCookTime;
	private long cookStartTime;
	private long backUpCookTime;

	public Chef(ItemType chefItemType, long defaultCookTime) {
		super();
		this.chefItemType = chefItemType;
		this.defaultCookTime = defaultCookTime;
		this.currentCookTime = defaultCookTime;
		this.setcStatus(chefStatus.FREE);
		this.backUpCookTime = defaultCookTime;
	}

	/*
	 * If the chef item matches input item, it does the following 
	 * 1. Changes chef status to BUSY
	 * 2. resets its cookStartTime to System current time  
	 */

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

	public long getBackUpCookTime() {
		return backUpCookTime;
	}

	public void setBackUpCookTime(long backUpCookTime) {
		this.backUpCookTime = backUpCookTime;
	}


}

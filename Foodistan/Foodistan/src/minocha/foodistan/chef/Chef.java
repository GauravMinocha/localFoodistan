package minocha.foodistan.chef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minocha.foodistan.item.Item;
import minocha.foodistan.item.ItemType;
import minocha.foodistan.item.Item.status;

public class Chef {
	
	private boolean isBusy;
	//private Map<ItemType, Long> chefItemTime = new HashMap<ItemType, Long>();
	//private Map<ItemType, Long> chefItemDefaultTime = new HashMap<ItemType, Long>();
	
	private ItemType chefItemType;
	private long currentCookTime;
	private long defaultCookTime;
	
	public Item cookItem(ItemType itmType) {
		
  	    try {
		Thread.sleep(this.getCurrentCookTime());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// TOD Auto-generated method stub
	Item itm = new Item(itmType);
    itm.setItemStatus(status.FRESH);
    return itm;
   }
	
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
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

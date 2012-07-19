package minocha.foodistan.item;

import java.util.HashMap;
import java.util.Map;

import minocha.foodistan.salesCounter.SalesCounter.status;

public class Item {
	
	  private long itemLifeTime;
	  private long itemStartTime = 0;
	  public  enum status {FRESH, STALE};
	  private status itemStatus;  
	  private ItemType itemType;
	  public static Map<String, Long> itemLifeTimeMap = new HashMap<String, Long>();
	
	  
	  public boolean isUsable(){
		   if ((System.currentTimeMillis()-this.getItemStartTime())>this.getItemLifeTime())
		    	return true;
		    		return false;
		   }


	public Item(ItemType itemType) {
		super();
		this.itemLifeTime = itemType.getLifeTime();
		this.itemStartTime = System.currentTimeMillis();
		this.itemStatus = status.FRESH;
		this.itemType = itemType;
	}


	public long getItemLifeTime() {
		return itemLifeTime;
	}


	public void setItemLifeTime(long itemLifeTime) {
		this.itemLifeTime = itemLifeTime;
	}


	public long getItemStartTime() {
		return itemStartTime;
	}

	public void setItemStartTime(long itemStartTime) {
		this.itemStartTime = itemStartTime;
	}

	public status getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(status itemStatus) {
		this.itemStatus = itemStatus;
	}


	public ItemType getItemType() {
		return itemType;
	}


	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}


	public static Map<String, Long> getItemLifeTimeMap() {
		return itemLifeTimeMap;
	}


	public static void setItemLifeTimeMap(Map<String, Long> itemLifeTimeMap) {
		Item.itemLifeTimeMap = itemLifeTimeMap;
	}


}

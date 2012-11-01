package minocha.foodistan.item;

public class Item {
	
	  public  enum itemStatus {FRESH, STALE};
	  private itemStatus itmStatus;  
	  private ItemType itemType;
	  private long itemLifeTime;
	  private long itemStartTime;
	 
	 public Item(ItemType itemType) {
			super();
		this.setItmStatus(itemStatus.FRESH);
		this.setItemType(itemType);
		this.setItemLifeTime(itemType.getLifeTime());
		this.setItemStartTime(System.currentTimeMillis());	
		}  
	  
	 public boolean isUsable(){
		   if ((System.currentTimeMillis()-this.getItemStartTime())<this.getItemLifeTime())
		    	return true;
		   else 
			   	return false;
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

	public itemStatus getItmStatus() {
		return itmStatus;
	}

	public void setItmStatus(itemStatus itmStatus) {
		this.itmStatus = itmStatus;
	}

	public ItemType getItemType() {
		return itemType;
	}


	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

}

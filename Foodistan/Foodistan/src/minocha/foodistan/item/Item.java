package minocha.foodistan.item;

public class Item {
	
	  private itemStatus itmStatus;  
	  private ItemType itemType;
	  private long itemLifeTime;
	  private long itemStartTime;
	 
	  public enum ItemType {BURGER(18); int lifeTime;
		ItemType(int lifeTime){
			this.lifeTime=lifeTime;	
		}
		public int getLifeTime(){
			return lifeTime;
		}
		}
	  public enum itemStatus {FRESH, STALE} 
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
   
	 //getter & setters
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

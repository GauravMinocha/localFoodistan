package minocha.foodistan.item;

public class Item {
	
	  private long itemLife;
	  private long itemTime = 0;
	  private String itemType;

	  public boolean isUsable(){
		   if (this.itemTime < itemLife)
		    	return true;
		    		return false;
		   }

	public long getItemLife() {
		return itemLife;
	}

	public void setItemLife(long itemLife) {
		this.itemLife = itemLife;
	}

	public long getItemTime() {
		return itemTime;
	}

	public void setItemTime(long itemTime) {
		this.itemTime = itemTime;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}

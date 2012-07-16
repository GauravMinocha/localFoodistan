package minocha.foodistan.chef;

import minocha.foodistan.item.Item;

public interface Chef {
	
	public Item chefCookItem();
	
	public void chefSpeedUp();
	
	public boolean chefBusy();
	
	public void chefSpeedDown();
	
}

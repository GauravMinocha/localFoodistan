package minocha.foodistan.chef;

import minocha.foodistan.item.*;

public interface ChefService
{

	public Item cookItem(Chef cf, ItemType itmType, int quantity);

	
}

package minocha.foodistan.chef;

import minocha.foodistan.item.Item;
import minocha.foodistan.item.Item.status;
import minocha.foodistan.item.ItemType;

public class ChefServiceImpl implements ChefService {

	@Override
	public Item cookItem(Chef cf, ItemType itmType, int quantity) {
		
		// TODO Auto-generated method stub
		Item itm = new Item(itmType);
	    itm.setItemStatus(status.FRESH);
	    			
		return null;
	}
}

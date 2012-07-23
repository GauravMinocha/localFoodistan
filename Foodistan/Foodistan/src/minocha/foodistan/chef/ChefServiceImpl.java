package minocha.foodistan.chef;

import minocha.foodistan.item.Item;
import minocha.foodistan.item.Item.status;
import minocha.foodistan.item.ItemType;
// useless for now 
public class ChefServiceImpl implements ChefService {

	@Override
	public Item cookItem(Chef cf, ItemType itmType) {
	
	  	    try {
			Thread.sleep(cf.getCurrentCookTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TOD Auto-generated method stub
		Item itm = new Item(itmType);
	    itm.setItemStatus(status.FRESH);
	    return itm;
	}
}

package minocha.foodistan.inventory;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import minocha.foodistan.item.*;

public class Inventory {

	private Queue<Item> items = new LinkedList<Item>();

	public int countItem()
	{  
		return this.items.size();
	}

	public void addItem(Item item)
	{
		this.items.add(item);
	}

	public Item removeItem()
	{
		try{
			if(!this.items.isEmpty()) {
				Item item = this.items.poll();
				return item;
			}
			else
				return null;
		}catch(NoSuchElementException e){ 
			return null;
		}
	}

	//getter & setters 
	public Queue<Item> getItems() {
		return items;
	}

	public void setItems(Queue<Item> items) {
		this.items = items;
	}
}

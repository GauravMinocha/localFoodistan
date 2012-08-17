package minocha.foodistan.item;
/*
 * Enum to define itemType with its lifeTime 
 */

public enum ItemType {

	BURGER(18);

	int lifeTime;

	ItemType(int lifeTime){
		this.lifeTime=lifeTime;	
	}
	public int getLifeTime(){
		return lifeTime;
	}

}

package minocha.foodistan.item;

public enum ItemType {
	
	BURGER(1800000);
	
	int lifeTime;
	
	ItemType(int lifeTime){
	this.lifeTime=lifeTime;	
	}
	public int getLifeTime(){
		return lifeTime;
	}

}

package minocha.foodistan.salesCounter;

import java.util.List;

public class SalesCounter {
	
	private int counterNo;
	private List<String> counterItems;
    public enum status {BUSY, FREE}
    private status counterStatus;
	
    public int getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(int counterNo) {
		this.counterNo = counterNo;
	}
	public List<String> getCounterItems() {
		return counterItems;
	}
	public void setCounterItems(List<String> counterItems) {
		this.counterItems = counterItems;
	}
	public status getCounterStatus() {
		return counterStatus;
	}
	public void setCounterStatus(status counterStatus) {
		this.counterStatus = counterStatus;
	}
    
    
	
}

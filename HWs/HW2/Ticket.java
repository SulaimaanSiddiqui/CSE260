
public class Ticket {

	private int num;
	private double cost;
	private String description;
	
	public Ticket(int tickNum, double tickCost, String tickDescription) {
		
		num = tickNum;
		cost = tickCost;
		description = tickDescription;
	}

	public int getNum(){
		return num;
	}
	
	public double getCost(){
		return cost;
	}
	
	public void setCost(double newCost){
		cost = newCost;
	}
	
	public String tickDescription(){
		return description;
	}
}


public class RepairCar {

	public String vin;
	public String make;
	public int year;
	public Ticket[] ticks;
	
	public RepairCar(String carVIN, String carMake, int carYear) {
		vin = carVIN;
		make = carMake;
		year = carYear;
		ticks = new Ticket[0];
	}
	
	public String getVIN(){
		return vin;
	}
	
	public String getMake(){
		return make;
	}
	
	
	public void addTick(Ticket tick){
		Ticket[] newDatabase = new Ticket[ticks.length + 1];
		for(int i = 0; i < ticks.length; i++){
			newDatabase[i] = ticks[i];
		}
		newDatabase[ticks.length] = tick;
		ticks = newDatabase;
	}
	
	public boolean removeTick(int tickNum){
		for(int i = 0; i < ticks.length; i++){
			if(ticks[i].getNum() == tickNum){
				ticks[i] = null;
				int j = 0;
				int k = 0;
				Ticket[] newDatabase = new Ticket[ticks.length - 1];
				while(j<ticks.length-1 && k < ticks.length){
					if(ticks[k] == null){
						k++;
					}
					newDatabase[j] = ticks[k];
					k++;
					j++;
				}
				ticks = newDatabase;
				return true;
			}
		}
		return false;
	}
	
	public double countRepairCosts(){
		double d = 0.0;
		for(Ticket t:ticks){
			d += t.getCost();
		}
		return d;
	}
	
	public boolean hasTick(int tickNum){
		for(Ticket t:ticks){
			if(t.getNum() == tickNum)
				return true;
		}
		return false;
	}
	
	public Ticket getTick(int tickNum){
		for(Ticket t:ticks){
			if(t.getNum() == tickNum)
				return t;
		}
		return null;
	}
	
	public void removeAllRepairs(){
		Ticket[] blankTicks = new Ticket[0];
		ticks = blankTicks;
	}
	
}


public class CarDatabase {

	private RepairCar[] myDatabase;
	private String[] makeList;
	private int[] makeCounter;
	private int currTicket;
	
	public CarDatabase() {
		myDatabase = new RepairCar[0];
		makeList = new String[0];
		makeCounter = new int[0];
		currTicket = 1;
	}
	
	public int getNumberOfCars(){
		return myDatabase.length;
	}
	
	public int getNumberOfMakes(){
		return makeList.length;
	}
	
	public int getCurrTicket(){
		return currTicket;
	}
	
	public void addCar(String vin, String make, int year) {
		RepairCar newCar = new RepairCar(vin, make, year);
		RepairCar[] updatedList = new RepairCar[myDatabase.length+1];
		for(int i = 0; i < myDatabase.length; i++){
			updatedList[i] = myDatabase[i];
		}
		updatedList[myDatabase.length] = newCar;
		myDatabase = updatedList;
		if(!hasMake(make)){
			String[] updatedMakeList = new String[makeList.length+1];
			int[] updatedMakeCounter = new int[makeCounter.length+1];
			for(int i = 0; i < makeList.length; i++){
				updatedMakeList[i] = makeList[i];
				updatedMakeCounter[i] = makeCounter[i];
			}
			updatedMakeList[makeList.length] = make;
			updatedMakeCounter[makeCounter.length] = 0;
			makeList = updatedMakeList;
			makeCounter = updatedMakeCounter;
		}

	}

	public void addTicket(String vin, double cost, String description) {
		RepairCar carToBeRepaired = getCarFromVIN(vin);
		Ticket newTick = new Ticket(currTicket, cost, description);
		carToBeRepaired.addTick(newTick);
		currTicket++;
		String m = carToBeRepaired.getMake();
		makeCounter[getMakePos(m)]++;
	}

	public void updateRepairCost(int ticketNum, double newCost) {
		Ticket toBeUpdated = getTickFromNum(ticketNum);
		toBeUpdated.setCost(newCost);
	}
	
	public void deleteRepair(int ticketNum) {
		RepairCar carToUpdate = getCarFromNum(ticketNum);
		carToUpdate.removeTick(ticketNum);
	}
	
	public void deleteAllRepairsForCar(String VIN) {
		RepairCar carToUpdate = getCarFromVIN(VIN);
		carToUpdate.removeAllRepairs();
	}
	
	public void deleteCarAndRepairs(String VIN) {
		for(int i = 0; i < myDatabase.length; i++){
			if(myDatabase[i].getVIN().equals(VIN)){
				myDatabase[i] = null;
				int j = 0;
				int k = 0;
				RepairCar[] newDatabase = new RepairCar[myDatabase.length - 1];
				while(j < myDatabase.length - 1 && k < myDatabase.length){
					if(myDatabase[k] == null){
						k++;
					}
					newDatabase[j] = myDatabase[k];
					k++;
					j++;
				}
				myDatabase = newDatabase;
			}
		}
	}
	
	public double calcTotalRepairCost(String vin) {
		RepairCar myCar = getCarFromVIN(vin);
		return myCar.countRepairCosts();
	}
	
	public double getTickCost(int ticketNum) {
		Ticket desiredTick = getTickFromNum(ticketNum);
		return desiredTick.getCost();
	}

	public boolean containsCar(String vin){
		for(RepairCar c: myDatabase){
			if(c.getVIN().equals(vin))
				return true;
		}
		return false;
	}
	
	public boolean containsTicket(int tickNum){
		for(RepairCar c: myDatabase){
			if(c.hasTick(tickNum))
				return true;
		}
		return false;
	}
	
	public RepairCar getCarFromVIN(String vin){
		for(RepairCar c: myDatabase){
			if(c.getVIN().equals(vin)){
				return c;
			}
		}
		return null;
	}
	
	public Ticket getTickFromNum(int tickNum){
		for(RepairCar c: myDatabase){
			if(c.hasTick(tickNum))
				return c.getTick(tickNum);
		}
		return null;
	}
	
	public RepairCar getCarFromNum(int tickNum){
		for(RepairCar c: myDatabase){
			if(c.hasTick(tickNum))
				return c;
		}
		return null;
	}
	
	public boolean hasMake(String make){
		for(String m: makeList){
			if(m.equals(make))
				return true;
		}
		return false;
	}
	
	public int getMakePos(String make){
		for(int i = 0; i < makeList.length; i++){
			if(makeList[i].equals(make))
				return i;
		}
		return -1;
	}
	
	public String getWorstCar(){
		int max = 0;
		int maxPos = 0;
		for(int i = 0; i < makeCounter.length; i++){
			if(makeCounter[i] > max){
				max = makeCounter[i];
				maxPos = i;
			}
		}
		return makeList[maxPos];
	}
}

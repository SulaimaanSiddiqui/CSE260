
public class CarRepairShopv2 {
	private RepairCar[] carDatabase = new RepairCar[0];
	private int currTicket = 1;

		public int addNewCar(String vin, String make, int year) {
			if(containsCar(vin)){
				return -1;
			}
			RepairCar newCar = new RepairCar(vin, make, year);
			RepairCar[] updatedList = new RepairCar[carDatabase.length+1];
			for(int i = 0; i < carDatabase.length; i++){
				updatedList[i] = carDatabase[i];
			}
			updatedList[carDatabase.length] = newCar;
			carDatabase = updatedList;
			return carDatabase.length;
		}
		
		public int addRepairTicket(String vin, double cost, String description) {
			if(!containsCar(vin)){
				return -1;
			}
			RepairCar carToBeRepaired = getCarFromVIN(vin);
			Ticket newTick = new Ticket(currTicket, cost, description);
			carToBeRepaired.addTick(newTick);
			currTicket++;
			return currTicket;
		}
		
		public double getRepairCost(int ticketNum) {
			if(!containsTicket(ticketNum)){
				return -1.0;
			}
			Ticket desiredTick = getTickFromNum(ticketNum);
			return desiredTick.getCost();
		}
		
		public double getTotalRepairCosts(String vin) {
			if(!containsCar(vin)){
				return -1.0;
			}
			RepairCar myCar = getCarFromVIN(vin);
			return myCar.countRepairCosts();
		}

		public String getWorstCarMake() {
			return "Go-Kart";
		}
		
		public boolean updateRepairCost(int ticketNum, double newCost) {
			if(!containsTicket(ticketNum)){
				return false;
			}
			Ticket toBeUpdated = getTickFromNum(ticketNum);
			toBeUpdated.setCost(newCost);
			return true;
		}
		
		public boolean deleteRepair(int ticketNum) {
			if(!containsTicket(ticketNum)){
				return false;
			}
			RepairCar carToUpdate = getCarFromNum(ticketNum);
			carToUpdate.removeTick(ticketNum);
			return true;
		}
		
		public boolean deleteAllRepairsForCar(String VIN) {
			if(!containsCar(VIN)){
				return false;
			}
			RepairCar carToUpdate = getCarFromVIN(VIN);
			carToUpdate.removeAllRepairs();
			return true;
		}
		
		public boolean deleteCarAndRepairs(String VIN) {
			if(!containsCar(VIN)){
				return false;
			}
			for(int i = 0; i < carDatabase.length; i++){
				if(carDatabase[i].getVIN().equals(VIN)){
					carDatabase[i] = null;
					int j = 0;
					int k = 0;
					RepairCar[] newDatabase = new RepairCar[carDatabase.length - 1];
					while(j<carDatabase.length-1 && k < carDatabase.length){
						if(carDatabase[k] == null){
							k++;
						}
						newDatabase[j] = carDatabase[k];
						k++;
						j++;
					}
					carDatabase = newDatabase;
					return true;
				}
			}
			return true;
		}
	
		public boolean containsCar(String vin){
			for(RepairCar c: carDatabase){
				if(c.getVIN().equals(vin))
					return true;
			}
			return false;
		}
		
		public boolean containsTicket(int tickNum){
			for(RepairCar c: carDatabase){
				if(c.hasTick(tickNum))
					return true;
			}
			return false;
		}
		
		public RepairCar getCarFromVIN(String vin){
			for(RepairCar c: carDatabase){
				if(c.getVIN().equals(vin)){
					return c;
				}
			}
			return null;
		}
		
		public Ticket getTickFromNum(int tickNum){
			for(RepairCar c: carDatabase){
				if(c.hasTick(tickNum))
					return c.getTick(tickNum);
			}
			return null;
		}
		
		public RepairCar getCarFromNum(int tickNum){
			for(RepairCar c: carDatabase){
				if(c.hasTick(tickNum))
					return c;
			}
			return null;
		}

}

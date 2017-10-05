
public class CarRepairShop {
	private CarDatabase myDatabase = new CarDatabase();

		public int addNewCar(String vin, String make, int year) {
			if(myDatabase.containsCar(vin)){
				return -1;
			}
			myDatabase.addCar(vin, make, year);
			return myDatabase.getNumberOfCars();
		}
		
		public int addRepairTicket(String vin, double cost, String description) {
			if(!myDatabase.containsCar(vin)){
				return -1;
			}
			myDatabase.addTicket(vin, cost, description);
			return myDatabase.getCurrTicket();
		}
		
		public double getRepairCost(int ticketNum) {
			if(!myDatabase.containsTicket(ticketNum)){
				return -1.0;
			}
			return myDatabase.getTickCost(ticketNum);
		}
		
		public double getTotalRepairCosts(String vin) {
			if(!myDatabase.containsCar(vin)){
				return -1.0;
			}
			return myDatabase.calcTotalRepairCost(vin);
		}

		public String getWorstCarMake() {
			if(myDatabase.getNumberOfMakes() == 0){
				return null;
			}
			return myDatabase.getWorstCar();
		}
		
		public boolean updateRepairCost(int ticketNum, double newCost) {
			if(!myDatabase.containsTicket(ticketNum)){
				return false;
			}
			myDatabase.updateRepairCost(ticketNum, newCost);
			return true;
		}
		
		public boolean deleteRepair(int ticketNum) {
			if(!myDatabase.containsTicket(ticketNum)){
				return false;
			}
			myDatabase.deleteRepair(ticketNum);
			return true;
		}
		
		public boolean deleteAllRepairsForCar(String VIN) {
			if(!myDatabase.containsCar(VIN)){
				return false;
			}
			myDatabase.deleteAllRepairsForCar(VIN);
			return true;
		}
		
		public boolean deleteCarAndRepairs(String VIN) {
			if(!myDatabase.containsCar(VIN)){
				return false;
			}
			myDatabase.deleteCarAndRepairs(VIN);
			return true;
		}
	
		
		

}

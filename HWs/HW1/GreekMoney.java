
public class GreekMoney {

	public static int howManyOboloi(int talents, int minae, int drachmae,int oboloi){
		return talToOb(talents) + minaToOb(minae) 
			+ drachToOb(drachmae) + oboloi;
	}
	
	public static int drachToOb(int drach){
		return drach * 6;
	}
	
	public static int minaToOb(int mina){
		return drachToOb(mina * 70);
	}
	
	public static int talToOb(int tal){
		return minaToOb(tal * 60);
	}
	
	public static void main(String[] args){
		System.out.println(howManyOboloi(2, 32, 59, 3));
	}
	
}

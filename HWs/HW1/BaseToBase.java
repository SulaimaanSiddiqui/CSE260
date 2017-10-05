//import java.util.Arrays;

/**
 * CSE 261 HW Set 1 Problem 2
 * Base2Base
 * Convert between two bases
 * @author Sulaimaan Siddiqui
 * 9/12/17
 *
 */
public class BaseToBase {
	
	private static String[] keys;
	
	public static void setKeys(){
		keys = new String[36];
		for(int i = 0; i < 10; i++){
			keys[i] = "" + i;
		}
		for(int j = 0; j < 26; j++){
			char ref = 65;
			keys[j+10] = "" + (char)(ref + j);
		}
	}
	
	
	public static String base2base(String n, int b1, int b2){
		String decVal = toDec(n, b1);
		return toBase(decVal, b2);
	}

	public static String toDec(String n, int b)
	{
		int sum = 0;
		for(int i = 0; i < n.length(); i++){
			int dig = fromLetter(n.substring(n.length()-1-i,n.length()-i));
			sum += dig * Math.pow(b, i);
		}
		//System.out.println("\n" + sum);
		return(""+sum);
	}
	
	public static int fromLetter(String s){
		//return Arrays.asList(keys).indexOf(s);
		for(int i = 0;i < keys.length;i++){
			if(keys[i].equals(s))
				return i;
		}
		return -1;
	}
	
	public static String fromInt(int b){
		return keys[b];
	}
	
	public static String toBase(String d, int b)
	{
		String newBase = "";
		int dec = Integer.parseInt(d);
		while(dec > 0)
		{
			newBase = keys[dec%b] + newBase;
			dec /= b;
		}
		return newBase;
	}
	
	public static void main(String[] args){
		setKeys();
		//for(String s: keys)
			//System.out.print(s);
		System.out.println(base2base("12345", 6, 8));
		System.out.println(base2base("STONY", 36, 19));
		System.out.println(base2base("832K1", 26, 15));
	}
	
}

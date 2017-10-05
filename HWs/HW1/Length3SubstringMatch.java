/**
 * CSE 261 HW Set 1 Problem 1
 * Length3SubstringMatch
 * Given 2 strings, s1 and s2, return the number of the positions where they contain the same length 3 substring.
 * @author Sulaimaan Siddiqui
 * 9/12/17
 *
 */
public class Length3SubstringMatch {
	
	/**
	 * stringMatch3 method
	 * Counts the number of times two strings have the same three letter sequence 
	 * in the same three positions
	 * 
	 * @param a First string to match
	 * @param b Second string to compare
	 * @return the number of matches
	 */
	public static int stringMatch3(String a, String b){
		int count = 0;
		int minLength;
		if(a.length() > b.length() ){
			minLength = b.length();
		}
		else minLength = a.length();
		for(int i = 0; i < minLength - 2; i++){
			String substringA = a.substring(i, i + 3);
			String substringB = b.substring(i, i + 3);
			if(substringA.equals(substringB))
				count++;
		}
		return count;
	}
	
	/**
	 * Main method for this problem. Tests problem against the given examples.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(stringMatch3("sdgndsgyudsaiufb","sadufygyudsafbnsaicgaf"));
		System.out.println(stringMatch3("banana","bananas"));
	}
	
}

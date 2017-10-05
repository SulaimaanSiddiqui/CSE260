
public class RunLengthEncoding {
	
	public static String encode(String message, char delimiter){
		int n = 0;
		String encoded = "";
		
		while(n < message.length()){
			String newString = message.substring(n);
			if(getLength(newString) > 3){
				encoded += update(newString, delimiter, getLength(newString));
				n+=getLength(newString);
			}
			else{
				encoded += newString.substring(0,1);
				n++;
			}
		}
		return encoded;
	}

	
	private static int getLength(String newString) {
		int count = 1;
		String ref = newString.substring(0,1);
		int i = 1;
		//System.out.println(ref);
		while(i < newString.length() && ref.equals(newString.substring(i,i+1))){
			count++;
			i++;
		}
		return count;
	}
	
	public static String update(String message, char delimiter, int length){
		return("" + delimiter + message.substring(0, 1) + length);
	}
	
	public static void main(String[] args){
		System.out.println(encode("XYZAAAAAAGGTCCCCCCTTTAAAAAAAAAAAAAAKK", '*'));
		System.out.println(encode("KKKKKKKKKKKKBCCDDDDDDDDDDDDDDDKKKKKMNUUUGGGGG", '$'));
	}
}

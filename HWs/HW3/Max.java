
public class Max {

	public static <E extends Comparable<E>> E max(E[] list){
		E max = list[0]; 
		for(int i = 0; i < list.length; i++){
			if(list[i].compareTo(max) > 0)
			{
				max = list[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args){
		Integer[] list = {1 , 2 , 2 , 3 , 4 , 5, 8989 , 0};
		System.out.println(max(list));
	}
	
}

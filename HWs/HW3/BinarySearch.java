
public class BinarySearch {

	public static<E extends Comparable<E>> int binarySearch(E[] list, E key){
		int min = 0;
		int max = list.length-1;
		while(min <= max){
			int mid = (max + min) / 2;
			if(list[mid].compareTo(key) == 0)
				return mid;
			else if(list[mid].compareTo(key) > 0)
				max = mid - 1;
			else
				min = mid + 1;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Integer[] list = { 1, 1, 2, 3, 4, 5, 6, 12, 34, 53, 66, 67, 666, 999};
		System.out.println( binarySearch( list, new Integer(66) ) );

	}

}

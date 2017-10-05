import java.util.ArrayList;

public class Shuffle {
	
	public static <E> void shuffle(ArrayList<E> list) {
		for (int i = 0; i < list.size(); i++) {
			int randPos = (int)(Math.random() * list.size());
			E temp = list.get(i);
			
			list.set(i, list.get(randPos));
			list.set(randPos, temp);
		}
	}
}

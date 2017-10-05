
public class MaxMatrix {
	public static <E extends Comparable<E>> E max(E[][] list) {
		E maximum = list[0][0];
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				if (list[i][j].compareTo(maximum) > 0)
					maximum = list[i][j];
			}
		}
		return maximum;
	}
}

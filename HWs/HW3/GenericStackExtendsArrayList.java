
import java.util.ArrayList;
import java.util.Scanner;

	public class GenericStackExtendsArrayList<E> extends ArrayList<E> {

		
		public E peek() {
			return get(size() - 1);
			}

		
		public void push(E o) {
			add(o);
			}

		public E pop() {
			E o = get(size() - 1);
			remove(size() - 1);
			return o;
		}

		public String toString() {
			return "stack: " + super.toString();
		}
		
		public static void main(String[] args) {
			
			Scanner scanner = new Scanner(System.in);
			GenericStackExtendsArrayList<String> stack = new GenericStackExtendsArrayList<>(); 

			System.out.print("Add 5 strings: ");
			for (int i = 0; i < 5; i++){ 
				stack.push(scanner.nextLine());
			} 
			
			while (stack.size() > 0)
				System.out.print(stack.pop() + " ");
			
			System.out.println();
		}
		
	}


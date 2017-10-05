
public class GenericStackWithArray<E> {

	private E[] myStack = (E[])(new Object[666]);
	private int size  = 0;
	
	public int getSize(){
		return size;
	}
	
	public void push(E o){
		//size++;
		if(isFull()){
			E[] newStack = (E[])(new Object[myStack.length * 2]);
			for(int i = 0; i < myStack.length; i++){
				newStack[i] = myStack[i];
			}
			myStack = newStack;
		}
		myStack[size++] = o;
	}
	
	public E pop(){
		E place = myStack[size-1];
		myStack[size-1] = null;
		size--;
		return place;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public String toString(){
		return ("stack " + myStack.toString());
	}
	
	public E peek(){
		return myStack[size - 1];
	}
	
	public boolean isFull(){
		return size == myStack.length - 1;
	}
	
}

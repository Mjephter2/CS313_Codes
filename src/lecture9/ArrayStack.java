package lecture9;

public class ArrayStack<E> implements Stack<E> {

	private Object[] data;
	private int top;
	
	public ArrayStack(int capacity) {
		this.data = new Object[capacity];
		this.top = -1;
	}
	
	@Override
	public void push(E e) {
		if (this.size() == this.data.length) throw new IllegalStateException();
		this.data[++this.top] = e; 
	}

	@Override
	public E pop() {
		if (this.isEmpty()) return null;
		E val = (E) this.data[this.top];
		this.data[this.top] = null;
		this.top--;
		return val;
	}

	@Override
	public E top() {
		return (E) this.data[this.top];
	}

	@Override
	public int size() {
		return this.top + 1;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

}

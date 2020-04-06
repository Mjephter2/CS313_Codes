package lecture11;

public class ArrayDeque<E> implements Deque<E> {

	private Object[] data;
	private int first, size;
	
	public ArrayDeque(int capacity) {
		this.data = new Object[capacity];
		this.first = 0;
		this.size = 0;
	}
	
	@Override
	public void addFirst(E e) {
		if (this.size == this.data.length)
			this.resize(this.data.length * 2);
		this.first = (this.first + this.data.length - 1) % this.data.length;
		this.data[this.first] = e;
		this.size++;
	}

	@Override
	public void addLast(E e) {
		if (this.size == this.data.length) 
			this.resize(this.data.length * 2);
		int nextAvailable = (this.first + this.size) % this.data.length;
		this.data[nextAvailable] = e;
		this.size++;
	}

	@Override
	public E removeFirst() {
		if (this.isEmpty()) 
			throw new IllegalStateException("Deque is empty");
		E val = (E) this.data[this.first];
		this.data[this.first] = null;
		this.first = (this.first + 1) % this.data.length;
		this.size--;
		return val;
	}

	@Override
	public E removeLast() {
		if (this.isEmpty())
			throw new IllegalStateException("Deque is empty");
		int last = (this.first + this.size - 1) % this.data.length;
		E val = (E) this.data[last];
		this.data[last] = null;
		this.size--;
		return val;
	}

	@Override
	public E first() {
		return (E) this.data[this.first];
	}

	@Override
	public E last() {
		int last = (this.first + this.size - 1) % this.data.length;
		return (E) this.data[last];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	private void resize(int newSize) {
		Object[] larger = new Object[newSize];
		for (int i = 0; i < this.size; i++) {
			int j = (this.first + i) % this.data.length;
			larger[i] = this.data[j];
		}
		this.data = larger;
		this.first = 0;
	}

}

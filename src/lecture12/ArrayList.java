package lecture12;

public class ArrayList<E> extends AbstractList<E> {
	
	private Object[] data;
	private int size;
	
	public ArrayList() {
		this.data = new Object[10];
		this.size = 0;
	}
	
	public ArrayList(int capacity) {
		this.data = new Object[capacity];
		this.size = 0;
	}

	@Override
	public void set(int i, E e) {
		this.data[i] = e;
	}

	@Override
	public void add(int i, E e) {
		//check if we need a larger array
		if (this.size == this.data.length) {
			this.resize(this.data.length * 2);			
		}
		
		this.size++;
		
		//shift all elements from i to last element one space to the right
		for (int j = this.size - 1; j > i; j--) {
			this.data[j] = this.data[j-1];
		}
		
		this.data[i] = e;
	}
	
	private void resize(int newSize) {
		Object[] newData = new Object[newSize];
		for (int i = 0; i < this.size; i++) {
			newData[i] = this.data[i];
		}
		this.data = newData;
	}

	@Override
	public E get(int i) {
		return (E) this.data[i];
	}

	@Override
	public E remove(int i) {
		E val = (E) this.data[i];
		for (int j = i+1; j < this.size; j++) {
			this.data[j-1] = this.data[j];
		}
		this.size--;
		return val;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	public void add(E e) {
		this.add(this.size, e);
	}
	
	public void clear() {
		this.size = 0; 
	}
	
	public boolean equals(ArrayList<E> other) {
		//quick size comparison
		if (this.size != other.size) return false;
		
		//exhaustive contents check
		for (int i = 0; i < this.size; i++) {
			if (!this.data[i].equals(other.data[i])) return false;
		}
		return true;
	}
	
	public int indexOf(E e) {
		for (int i = 0; i < this.size; i++) {
			if (this.data[i].equals(e)) return i;
		}
		return -1;
	}
	
	public boolean contains(E e) {
		return this.indexOf(e) != -1;
	}
	
	public boolean remove(E e) {
		int idx = this.indexOf(e);
		if (idx == -1) return false;
		else {
			this.remove(idx);
			return true;
		}
	}
	
	//shallow copy
	public ArrayList<E> clone() {
		ArrayList<E> c = new ArrayList<E>(this.data.length);
		for (int i = 0; i < this.size; i++) {
			c.add((E) this.data[i]);
		}
		return c;
	}
	
}

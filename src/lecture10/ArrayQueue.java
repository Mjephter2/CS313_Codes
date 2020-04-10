package lecture10;

public class ArrayQueue<E> implements Queue<E> {

	public static void main(String[] args) {
		ArrayQueue q = new ArrayQueue<>(10);

		q.enqueue(1);
		q.dequeue();
		q.enqueue(2);
		q.enqueue(3);
		q.dequeue();
		System.out.println(q.first);
	}

	private Object[] data;
	private int first, size;

	public ArrayQueue(int cap) {
		this.data = new Object[cap];
		this.first = 0;
		this.size = 0;
	}

	@Override
	public void enqueue(E e) {
		if (this.size == this.data.length) return;
		int next = (this.first + this.size) % this.data.length;
		this.data[next] = e;
		this.size++;
	}

	@Override
	public E dequeue() {
		if (this.isEmpty()) return null;
		E val = (E) this.data[this.first];
		this.data[this.first] = null;
		this.first = (this.first + 1) % this.data.length;
		this.size--;
		return val;
	}

	@Override
	public E first() {
		return (E) this.data[this.first];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

}

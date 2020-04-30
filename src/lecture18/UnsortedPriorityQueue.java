package lecture18;

import java.util.Comparator;

import lecture7.*;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

	private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

	public UnsortedPriorityQueue() {
		super();
	}
	
	public UnsortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}
	
	//O(1)
	@Override
	public Entry<K, V> insert(K key, V value) {
		this.checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		this.list.addLast(newest);
		return newest;
	}
	
	//O(n)
	private Position<Entry<K,V>> findMin() {
		Position<Entry<K,V>> current = this.list.first();
		
		Position<Entry<K,V>> min = current;
		while(current != null) {
			
			if (this.compare(current.getData(), min.getData()) < 0)
				min = current;
			
			current = this.list.after(current);	
		}
		
		return min;
	}

	//O(n)
	@Override
	public Entry<K, V> removeMin() {
		if (this.isEmpty()) return null;
		return this.list.remove(this.findMin());
	}

	//O(n)
	@Override
	public Entry<K, V> min() {
		if (this.isEmpty()) return null;
		return this.findMin().getData();
	}

	@Override
	public int size() {
		return this.list.size();
	}

}

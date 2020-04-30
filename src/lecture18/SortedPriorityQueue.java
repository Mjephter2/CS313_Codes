package lecture18;

import java.util.Comparator;

import lecture7.LinkedPositionalList;
import lecture7.PositionalList;
import lecture7.Position;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

	private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
	
	public SortedPriorityQueue() {
		super();
	}
	
	public SortedPriorityQueue(Comparator<K> comp) {
		super(comp);
	}
	
	//O(n)
	@Override
	public Entry<K, V> insert(K key, V value) {
		this.checkKey(key);
		
		Entry<K,V> newest = new PQEntry<>(key,value);

		Position<Entry<K,V>> current = this.list.first();
		while(current != null && this.compare(current.getData(), newest) < 0)
			current = this.list.after(current);
		
		if (current == null)
			list.addFirst(newest);
		else 
			this.list.addBefore(current, newest);
		
		return newest;
	}

	//O(1)
	@Override
	public Entry<K, V> removeMin() {
		if (this.isEmpty()) return null;
		return this.list.remove(this.list.first());
	}

	//O(1)
	@Override
	public Entry<K, V> min() {
		if (this.isEmpty()) return null;
		return this.list.first().getData();
	}

	@Override
	public int size() {
		return this.list.size();
	}

}

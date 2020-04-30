package lecture19;

import java.util.ArrayList;
import java.util.Comparator;

import lecture18.AbstractPriorityQueue;
import lecture18.Entry;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

	private ArrayList<Entry<K,V>> heap = new ArrayList<>();
	
	public HeapPriorityQueue() {
		super();
	}
	
	public HeapPriorityQueue(Comparator<K> comp) {
		super(comp);
	}
	
	private int parent(int i) {
		return (i - 1) / 2;
	}
	
	private int left(int i) {
		return 2*i + 1;
	}
	
	private int right(int i) {
		return 2*i + 2;
	}
	
	private boolean hasLeft(int i) {
		//check if the index of the (potential) left child is in bounds
		return this.left(i) < this.heap.size();
	}
	
	private boolean hasRight(int i) {
		//check if the index of the (potential) right child is in bounds
		return this.right(i) < this.heap.size();
	}
	
	private void swap(int i, int j) {
		Entry<K,V> temp = this.heap.get(i);
		this.heap.set(i, this.heap.get(j));
		this.heap.set(j, temp);
	}
	
	private void upheap(int i) {
		//may need to continue bubbling until root is reached
		while(i > 0) {
			
			//compare with parent
			int p = this.parent(i);
			Entry<K,V> current = this.heap.get(i);
			Entry<K,V> parent = this.heap.get(p);
			
			//if current key >= parent key, stop bubbling
			if (this.compare(current, parent) >= 0) break;
			
			//otherwise swap and keep bubbling
			this.swap(i, p);
			i = p;
		}
	}

	private void downheap(int i) {
		//may need to keep bubbling until a leaf node is reached
		while(this.hasLeft(i)) {
			
			//find smallest child key
			int leftIdx = this.left(i);
			int smallestIdx = leftIdx;
			
			
			if (this.hasRight(i)) {
				int rightIdx = this.right(i);
				
				Entry<K,V> left = this.heap.get(leftIdx);
				Entry<K,V> right = this.heap.get(rightIdx);
				if (this.compare(right, left) < 0) 
					smallestIdx = rightIdx;
			}
			
			//compare with smallest child
			Entry<K,V> smallestChild = this.heap.get(smallestIdx);
			Entry<K,V> current = this.heap.get(i);
			
			//if smallest key >= current key, stop bubbling
			if (this.compare(smallestChild, current) >= 0) 
				break;
			
			//otherwise swap and keep bubbling
			this.swap(i, smallestIdx);
			i = smallestIdx;
		}
	}
	
	//O(log n)
	@Override
	public Entry<K, V> insert(K key, V value) {
		this.checkKey(key);
		
		//step one: create a new node with the given data
		Entry<K,V> newest = new PQEntry<>(key, value);
		this.heap.add(this.heap.size(), newest);
		
		//step two: perform upheap bubbling to move the data to a valid position
		this.upheap(this.heap.size() - 1);
		
		return newest;
	}
	
	//O(log n)
	@Override
	public Entry<K, V> removeMin() {
		if (this.isEmpty()) return null;
		
		//step one: return the element with minimum key
		Entry<K,V> min = this.min();
		
		//step two: swap root and last node
		this.swap(0, this.heap.size() - 1);
		//remove last node
		this.heap.remove(this.heap.size() - 1);
		
		//step three: perform downheap bubbling to move the data in the root to a valid position
		this.downheap(0);
		
		return min;
	}

	@Override
	public Entry<K, V> min() {
		if (this.isEmpty()) return null;
		return this.heap.get(0);
	}

	@Override
	public int size() {
		return this.heap.size();
	}



}

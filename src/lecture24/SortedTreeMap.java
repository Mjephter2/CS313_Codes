package lecture24;

import java.util.ArrayList;
import java.util.Comparator;

import lecture15.Position;
import lecture16.LinkedBinaryTree;
import lecture18.DefaultComparator;
import lecture18.Entry;
import lecture20.AbstractMap;

public class SortedTreeMap<K,V> extends AbstractMap<K,V> implements SortedMap<K,V> {

	private LinkedBinaryTree<Entry<K,V>> tree = new LinkedBinaryTree<>();
	private Comparator<K> comp;

	public SortedTreeMap(Comparator<K> comp) {
		this.comp = comp;
		this.tree.addRoot(null);
	}
	
	public SortedTreeMap() {
		this(new DefaultComparator<>());
	}
	
	
	private Position<Entry<K,V>> search (Position<Entry<K,V>> p, K key) {
		//entry not found
		if (this.tree.isExternal(p)) return p;
		
		int comparison = this.comp.compare(key, p.getElement().getKey());
		
		//found matching entry
		if (comparison == 0) return p;
		
		//recursively search left or right
		if (comparison < 0)
			return search(this.tree.left(p), key);
		else
			return search(this.tree.right(p), key);
	}
	
	@Override
	public V put(K key, V val) {
		Position<Entry<K,V>> p = this.search(this.tree.root(), key);
		
		Entry<K,V> newEntry = new MapEntry<>(key, val);
		
		if (this.tree.isExternal(p)) {
			//no matching entry found, create new entry
			this.tree.addLeft(p, null);
			this.tree.addRight(p, null);
			this.tree.set(p, newEntry);
			return null;
		} else {
			//update existing entry
			V oldValue = p.getElement().getValue();
			this.tree.set(p, newEntry);
			return oldValue;
		}
	}

	@Override
	public V get(K key) {
		Position<Entry<K,V>> p = this.search(this.tree.root(), key);
		if (this.tree.isExternal(p)) return null;
		return p.getElement().getValue();
	}

	@Override
	public V remove(K key) {
		Position<Entry<K,V>> p = this.search(this.tree.root(), key);
		if (this.tree.isExternal(p)) return null;
		
		//check if both children are internal
		if ( this.tree.isInternal(this.tree.left(p)) && this.tree.isInternal(this.tree.right(p))) {
			//find replacement
			Position<Entry<K,V>> r = this.treeMax(this.tree.left(p));
			
			//swap replacement with p
			Entry<K,V> temp = p.getElement();
			this.tree.set(p, r.getElement());
			this.tree.set(r, temp);
			
			//delete replacement
			p = r;
		}
		
		//remove a sentinel node so p can be removed
		Position<Entry<K,V>> leaf;
		if (this.tree.isExternal(this.tree.left(p)))
			leaf = this.tree.left(p);
		else 
			leaf = this.tree.right(p);
		this.tree.remove(leaf);
		
		V oldVal = p.getElement().getValue();
		this.tree.remove(p);
		return oldVal;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> iterable = new ArrayList<>(this.size());
		for ( Position<Entry<K,V>> p : this.tree.inorder() )
			if ( this.tree.isInternal(p))
				iterable.add(iterable.size(), p.getElement());
		return iterable;
	}

	@Override
	public int size() {
		return (this.tree.size() - 1) / 2;
	}
	
	private Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p) {
		Position<Entry<K,V>> cursor = p;
		while(this.tree.isInternal(cursor))
			cursor = this.tree.left(cursor);
		return this.tree.parent(cursor);
	}
	
	private Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) {
		Position<Entry<K,V>> cursor = p;
		while(this.tree.isInternal(cursor))
			cursor = this.tree.right(cursor);
		return this.tree.parent(cursor);
	}
	
	private boolean isLeft(Position<Entry<K,V>> p) {
		return p == this.tree.left(this.tree.parent(p));
	}
	
	private boolean isRight(Position<Entry<K,V>> p) {
		return p == this.tree.right(this.tree.parent(p));
	}

	//O(h)
	@Override
	public Entry<K, V> firstEntry() {
		if (this.isEmpty()) return null;
		return this.treeMin(this.tree.root()).getElement();
	}

	//O(h)
	@Override
	public Entry<K, V> lastEntry() {
		if (this.isEmpty()) return null;
		return this.treeMax(this.tree.root()).getElement();
	}

	//O(h)
	@Override
	public Entry<K, V> floorEntry(K key) {
		Position<Entry<K,V>> p = this.search(this.tree.root(), key);
		if (this.tree.isInternal(p)) return p.getElement();
		while(p != this.tree.root()) {
			if (this.isRight(p)) return this.tree.parent(p).getElement();
			p = this.tree.parent(p);
		}
		return null;
	}

	//O(h)
	@Override
	public Entry<K, V> ceilingEntry(K key) {
		Position<Entry<K,V>> p = this.search(this.tree.root(), key);
		if (this.tree.isInternal(p)) return p.getElement();
		while(p != this.tree.root()) {
			if (this.isLeft(p)) return this.tree.parent(p).getElement();
			p = this.tree.parent(p);
		}
		return null;
	}

	//O(h)
	@Override
	public Entry<K, V> lowerEntry(K key) {
		Position<Entry<K,V>> p = this.search(this.tree.root(), key);
		if (this.tree.isInternal(p) && this.tree.isInternal(this.tree.left(p)))
			return this.treeMax(this.tree.left(p)).getElement();
		while(p != this.tree.root()) {
			if (this.isRight(p)) return this.tree.parent(p).getElement();
			p = this.tree.parent(p);
		}
		return null;
	}

	//O(h)
	@Override
	public Entry<K, V> higherEntry(K key) {
		Position<Entry<K,V>> p = this.search(this.tree.root(), key);
		if (this.tree.isInternal(p) && this.tree.isInternal(this.tree.right(p)))
			return this.treeMin(this.tree.right(p)).getElement();
		while(p != this.tree.root()) {
			if (this.isLeft(p)) return this.tree.parent(p).getElement();
			p = this.tree.parent(p);
		}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
		ArrayList<Entry<K,V>> iterable = new ArrayList<>(this.size());
		if (this.comp.compare(fromKey, toKey) < 0)
			subMapRecurse(fromKey, toKey, this.tree.root(), iterable);
		return iterable;
	}
	
	private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>> p, ArrayList<Entry<K,V>> iterable) {
		if (this.tree.isExternal(p)) return; //base case
		
		if (this.comp.compare(p.getElement().getKey(), fromKey) < 0) {
			this.subMapRecurse(fromKey,toKey,this.tree.right(p),iterable);
		} else {
			this.subMapRecurse(fromKey, toKey, this.tree.left(p), iterable);
			if (this.comp.compare(p.getElement().getKey(), toKey) >= 0) return;
			iterable.add(iterable.size(), p.getElement());
			this.subMapRecurse(fromKey, toKey, this.tree.right(p), iterable);			
		}
		
		
	}
	
	public static void main(String[] args) {
		SortedMap<Integer, String> m = new SortedTreeMap<>();
		
		m.put(0, "apples");
		m.put(2, "bananas");
		m.put(4, "carrots");
		
		System.out.println(m.size());
		System.out.println(m.get(0));
		System.out.println(m.get(2));
		
		
		System.out.println();
		System.out.println(m.firstEntry().getValue());
		System.out.println(m.floorEntry(2).getValue());
		System.out.println(m.floorEntry(3).getValue());
		System.out.println(m.lowerEntry(2).getValue());
		
		System.out.println();
		System.out.println(m.lastEntry().getValue());
		System.out.println(m.ceilingEntry(2).getValue());
		System.out.println(m.ceilingEntry(3).getValue());
		System.out.println(m.higherEntry(2).getValue());
		
		m.put(-2, "zebras");
		System.out.println();
		System.out.println(m.firstEntry().getValue());
		
		System.out.println();
		for (Entry<Integer,String> e : m.subMap(-2, 2))
			System.out.println(e.getValue());
	}
	
}


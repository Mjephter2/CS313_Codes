package lecture23;

import lecture15.Position;
import lecture16.LinkedBinaryTree;
import lecture18.DefaultComparator;
import lecture18.Entry;
import lecture20.AbstractMap;

import java.util.ArrayList;
import java.util.Comparator;

public class TreeMapHW<K,V> extends AbstractMap<K,V> {

    private LinkedBinaryTree<Entry<K,V>> tree = new LinkedBinaryTree<>();
    private Comparator<K> comp;

    public TreeMapHW(Comparator<K> comp) {
        this.comp = comp;
        this.tree.addRoot(null);
    }

    public TreeMapHW() {
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
        this.checkKey(key);
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
        this.checkKey(key);
        Position<Entry<K,V>> p = this.search(this.tree.root(), key);
        if (this.tree.isExternal(p)) return null;
        return p.getElement().getValue();
    }

    @Override
    public V remove(K key) {
        this.checkKey(key);
        Position<Entry<K,V>> p = this.search(this.tree.root(), key);
        if (this.tree.isExternal(p)) return null;

        //check if both children are internal
        if ( this.tree.isInternal(this.tree.left(p)) && this.tree.isInternal(this.tree.right(p))) {
            //find replacement
            Position<Entry<K,V>> r = this.tree.left(p);
            while (this.tree.isInternal(r)) {
                r = this.tree.right(r);
            }
            r = this.tree.parent(r);

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

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return this.comp.compare(key, key) == 0;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid key");
        }
    }

}


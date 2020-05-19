package lecture24;

import lecture18.Entry;
import lecture20.AbstractMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedTableMap<K,V> extends AbstractMap<K,V> implements SortedMap<K,V> {

    private class EntryIterable implements Iterable<Entry<K,V>> {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    private class EntryIterator implements Iterator<Entry<K,V>> {

        private int idx = 0;

        @Override
        public boolean hasNext() {
            return this.idx < SortedTableMap.this.table.size();
        }

        @Override
        public Entry<K, V> next() {
            if (this.idx == SortedTableMap.this.table.size()) throw new NoSuchElementException();
            return SortedTableMap.this.table.get(this.idx++);
        }

    }

    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    private Comparator<K> comp;

    public SortedTableMap(Comparator<K> comp){
        this.comp = comp;
    }

    @Override
    public Entry<K, V> firstEntry() {
        if(this.table.isEmpty()) return null;
        return this.table.get(0);
    }

    @Override
    public Entry<K, V> lastEntry() {
        if(this.table.isEmpty()) return null;
        return this.table.get(this.table.size() - 1);
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        int index = this.findIndex(key);
        if(index > -1){
            // found key
            return this.table.get(index);
        }else{
            // key not found
            for(int i = 0; i < this.size(); i++){
                Entry<K,V> currentEntry = this.table.get(i);
                if(this.comp.compare(currentEntry.getKey(), key) < 0){
                    return currentEntry;
                }
            }
        }
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        int index = this.findIndex(key);
        if(index > -1){
            // found key
            return this.table.get(index);
        }else{
            // key not found
            for(int i = 0; i < this.size(); i++){
                Entry<K,V> currentEntry = this.table.get(i);
                if(this.comp.compare(currentEntry.getKey(), key) > 0){
                    return currentEntry;
                }
            }
        }
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        if(this.floorEntry(key) == this.firstEntry()) return null;
        int index = this.findIndex(key);
        if(index > -1) return this.table.get(index - 1);

        for(int i = 0; i < this.size(); i++){
            Entry<K,V> currentEntry = this.table.get(i);
            if(this.comp.compare(currentEntry.getKey(), key) < 0){
                return currentEntry;
            }
        }
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        if(this.ceilingEntry(key) == this.lastEntry()) return null;

        int index = this.findIndex(key);
        if(index > -1) return this.table.get(index + 1);

        for(int i = 0; i < this.size(); i++){
            Entry<K,V> currentEntry = this.table.get(i);
            if(this.comp.compare(currentEntry.getKey(), key) > 0){
                return currentEntry;
            }
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
        ArrayList<Entry<K,V>> iter = new ArrayList<>();
        int from = this.findIndex(fromKey);
        int to = this.findIndex(toKey);
        if(from < 0 || to < 0){
            return null;
        }
        for(int i = from; i < to; i++){
            iter.add(this.table.get(i));
        }
        return iter;
    }

    //O(n)
    public int findIndex(K key) {
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    //O(n)
    @Override
    public V put(K key, V val) {
        int index = this.findIndex(key);
        if (index > -1) {
            //update the matching entry
            V oldVal = this.table.get(index).getValue();
            this.table.get(index).setValue(val);
            return oldVal;
        } else {
            MapEntry<K,V> newEntry = new MapEntry<>(key, val); //O(1)
            Entry<K,V> floor = this.floorEntry(key);
            if(floor != null){
                this.table.add(this.findIndex(floor.getKey()),newEntry);
            }else{
                this.table.add(0,newEntry);
            }
            return null;
        }
    }

    //O(n)
    @Override
    public V get(K key) {
        int idx = this.findIndex(key);
        if (idx < 0) return null;
        return this.table.get(idx).getValue();
    }

    //O(n)
    @Override
    public V remove(K key) {
        int idx = this.findIndex(key);
        if (idx < 0) return null;
        V val = this.table.get(idx).getValue();

        this.table.set(idx, this.table.get(this.table.size() - 1));
        this.table.remove(this.table.size() - 1);

        return val;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    @Override
    public int size() {
        return this.table.size();
    }

    public static void main(String[] args) {

        SortedTableMap<String, Integer> m = new SortedTableMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //add new entries
        m.put("TP", 30);
        m.put("Toothbrush", 2);
        m.put("Beans", 20);

        for (Entry<String, Integer> entry : m.entrySet() ) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("***************************");

        for (Entry<String, Integer> entry : m.subMap("Beans", "TP") ) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("*********************************");


        //update
        m.put("TP", 42);

        //remove
        m.remove("Toothbrush");

        for (Entry<String, Integer> entry : m.entrySet() ) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

}


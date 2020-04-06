package lecture8;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {

    private int idx = 0;
    private int lastReturned = -1;
    private ArrayList<E> list;

    public ArrayIterator(ArrayList<E> list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return this.idx < list.size();
    }

    @Override
    public E next() {
        this.lastReturned = this.idx;
        this.idx++;
        return list.get(this.lastReturned);
    }

    @Override
    public void remove() {
        if (this.lastReturned < 0) throw new IllegalStateException();
        list.remove(this.lastReturned);
        this.lastReturned = -1;
    }
}
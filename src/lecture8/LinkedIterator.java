package lecture8;

import lecture7.Position;

import java.util.Iterator;

public class LinkedIterator<E> implements Iterator<E> {

    private Position<E> cursor;
    private Position<E> lastReturned = null;
    LinkedPositionalList<E> list;

    public LinkedIterator(LinkedPositionalList<E> list){
        this.list = list;
        cursor = this.list.first();
    }
    @Override
    public boolean hasNext() {
        return this.cursor != null;
    }

    @Override
    public E next() {
        this.lastReturned = this.cursor;
        this.cursor = list.after(this.cursor);
        return this.lastReturned.getData();
    }

    @Override
    public void remove() {
        if (this.lastReturned == null) throw new IllegalStateException();
        list.remove(this.lastReturned);
        this.lastReturned = null;
    }
}

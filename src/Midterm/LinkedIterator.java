package Midterm;

import java.util.Iterator;

public class LinkedIterator<E> implements Iterator<E> {

    private DoublyLinkedList<E> data;

    public LinkedIterator(DoublyLinkedList<E> list){
        data = list;
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public E next() {
        return data.removeFirst();
    }
}

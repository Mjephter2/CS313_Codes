package lecture4;
/*
 * array-based implementation of an array list
 */

public class ArrayList<E> extends lecture4.AbstractList<E> {

    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[10];
        this.size = 0;
    }

    public ArrayList(int capacity) {
        this.data = new Object[capacity];
        this.size = 0;
    }

    @Override
    public void set(int i, E e) {
        this.data[i] = e;
    }

    @Override
    public void add(int i, E e) {
        //check if we need a larger array
        if (this.size == this.data.length) {
            this.resize(this.data.length * 2);
        }

        this.size++;

        //shift all elements from i to last element one space to the right
        for (int j = this.size - 1; j > i; j--) {
            this.data[j] = this.data[j-1];
        }

        this.data[i] = e;
    }

    private void resize(int newSize) {
        Object[] newData = new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }

    @Override
    public E get(int i) {
        return (E) this.data[i];
    }

    @Override
    public E remove(int i) {
        E val = (E) this.data[i];
        for (int j = i; j < this.size - 1; j++) {
            this.data[j] = this.data[j+1];
        }
        this.size--;
        return val;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void add(E e) {
        this.add(this.size, e);
    }

    public void clear() {
        this.size = 0;
    }

    public boolean equals(ArrayList<E> other) {
        //quick size comparison
        if (this.size != other.size) return false;

        //exhaustive contents check
        for (int i = 0; i < this.size; i++) {
            if (!this.data[i].equals(other.data[i])) return false;
        }
        return true;
    }

    public int indexOf(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(e)) return i;
        }
        return -1;
    }

    public boolean contains(E e) {
        return this.indexOf(e) != -1;
    }

    /*
     * O(n)
     */
    public boolean remove(E e) {
        int idx = this.indexOf(e);
        if (idx == -1) return false;
        else {
            this.remove(idx);
            return true;
        }
    }

    //shallow copy
    public ArrayList<E> clone() {
        ArrayList<E> c = new ArrayList<E>(this.data.length);
        for (int i = 0; i < this.size; i++) {
            c.add((E) this.data[i]);
        }
        return c;
    }
    /*
     *  Homework
     *  3 Imagine a method removeRange(int, int) that takes two indices as arguments.
     *  The method removes all the elements starting at the first index and ending at the last index.
     *  Implement the removeRange(...) method in code so that your method calls the normal remove(...) method
     *  to remove each element in the range - what is the runtime estimate (big O) for this implementation?
     *  Then try implementing removeRange(...) without use of the remove(...) method - what is the best runtime
     *  you can achieve?
     *  Runtime: O(n^2)
     *  Justification: worst case scenario, we have to run remove(E e) n times. Each times the amount of shifting
     *  reduces by 1. Since our data array has n element, if we have to remove all the items then we shift n-1 items the
     *  first time, n-2 the second time and so on.
     */
    public boolean removeRange(int start, int end){
        if(start > end) {
            return false;
        }else if (start < 0 || end > data.length-1){
            throw new IndexOutOfBoundsException();
        }else if(start == end) return remove((E)data[start]);
        else{
            for(int i = start; i <= end; i++){
                remove((E)data[start]);
            }
            return true;
        }
    }
    // implementation of removeRange without calling remove(E e)
    // O(n)
    public boolean removeRange2(int start, int end){
        if(start > end) {
            return false;
        }else if (start < 0 || end > size-1){
            throw new IndexOutOfBoundsException();
        } else{
            int remaining = (size - 1) - (end + 1) + 1;
            int gap = end - start + 1 ;
            for(int i = start;i < start + remaining; i++){
                data[i] = data[i + gap];
            }
            for(int i = 0; i < gap; i++){
                size--;
            }
            return true;
        }
    }

}

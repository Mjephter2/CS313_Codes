package lecture6;
/*
 * Homework
 * # 2
 */

public class CircularList<E> {

    public static void main(String[] args) {
        CircularList<Integer> list1 = new CircularList<>();
        for(int i = 0; i <= 10; i++){
            list1.addFirst(i);
        }
        list1.addLast(1000);
        System.out.println("size = " + list1.size + ": " + list1);
        list1.rotate();
        System.out.println("After rotate size = " + list1.size + ": " + list1);
        list1.rotate();
        System.out.println("After rotate size = " + list1.size + ": " + list1);
        list1.rotate();
        System.out.println("After rotate size = " + list1.size + ": " + list1);
        list1.rotate();
        System.out.println("After rotate size = " + list1.size + ": " + list1);
        list1.removeFirst();
        System.out.println("After removeFirst() size = " + list1.size() + ": " + list1);
        list1.removeFirst();
        System.out.println("After removeFirst() size = " + list1.size() + ": " + list1);
        list1.removeFirst();
        System.out.println("After removeFirst() size = " + list1.size() + ": " + list1);
        list1.removeFirst();
        System.out.println("After removeFirst() size = " + list1.size() + ": " + list1);
    }

    private static class Node<E> {

        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Object[] data;
    private int size = 0;

    public CircularList(){
        data = new Object[10];
    }
    // O(n)
    private void resize(int newSize) {
        Object[] newData = new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }
    // O(n)
    public void rotate() {
      if(isEmpty()) return;
      addFirst((E)data[size - 1]);
      size--;
    }
    // O(1)
    public E first() {
        if (isEmpty()) return null;
        return (E)data[0];
    }
    // O(1)
    public E last() {
        if (isEmpty()) return null;
        return (E)data[size - 1];
    }
    // O(1)
    public int size() {
        return this.size;
    }
    // O(1)
    public boolean isEmpty() {
        return size == 0;
    }
    // O(n) because we may have to resize
    public void addFirst(E e) {
        if (size() == data.length) resize(2 * data.length);
        for(int i = size; i > 0; i--){
            data[i] = data[i - 1];
        }
        data[0] = e;
        this.size++;
    }
    // O(n) because we may have to resize
    public void addLast(E e) {
        if(size == data.length) resize(2 * data.length);
        data[size] = e;
        size++;
    }
    // O(n) since we need to shift the content of data to the left except for the first cell
    public E removeFirst() {
        if(isEmpty()) return null;
        E savedData = first();
        for(int i = 0; i <= size() - 1; i++){
            data[i] = data[i + 1];
        }
        size--;
        return savedData;
    }
    // O(n)
    public String toString(){
        if (isEmpty()) return "[]";
        StringBuilder s = new StringBuilder();
        s.append("[");
        for(int i = 0; i < size - 1; i++){
            s.append(data[i] + ", ");
        }
        s.append(data[size - 1] + "]" );
        return s.toString();
    }

}

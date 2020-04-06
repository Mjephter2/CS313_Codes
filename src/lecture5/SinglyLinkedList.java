package lecture5;

public class SinglyLinkedList<E> {

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

    private Node<E> header, tail;
    private int size;

    public SinglyLinkedList() {
        this.header = new Node<>(null);
        this.tail = header;
        this.size = 0;
    }

    //O(1)
    public E first() {
        if (this.isEmpty()) return null;
        return this.header.next.data;
    }

    //O(1)
    public E last() {
        if (this.isEmpty()) return null;
        return this.tail.data;
    }

    //O(1)
    public int size() {
        return this.size;
    }

    //O(1)
    public boolean isEmpty() {
        return this.header.next == null;
    }

    //O(1)
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, this.header.next);
        this.header.next = newNode;
        this.size++;
    }

    //O(1)
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        this.tail.next = newNode;
        this.tail = newNode;
        this.size++;
    }

    //O(1)
    public E removeFirst() {
        if (this.isEmpty()) return null;
        E val = this.header.next.data;
        this.header.next = this.header.next.next;
        this.size--;
        return val;
    }

    /*
     *Homework
     * 3 - Implement the following method: E removeLast().  This method should return the last element of the list
     * and remove it from the list.
     * O(n)
     */
    public E removeLast(){
        E val = this.tail.data;
        if(this.isEmpty()) return null;
        else if(this.size() == 1){
            header.next = null;
            size--;
        }else {
            Node<E> current = this.header.next;
            Node<E> prevNode = null;
            while (current != null) {
                if (current == this.tail) {
                    prevNode = current;
                }
                current = current.next;
            }
            prevNode.next = null;
            this.tail = prevNode;
            size--;
        }
        return val;
    }

    //O(n)
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> current = this.header;

        while(current.next != null) {
            current = current.next;
            sb.append(String.format("%s --> ", current.data));
        }
        sb.append("null]");

        return sb.toString();
    }

}
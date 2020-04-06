package lecture8;

import lecture7.Position;
import lecture7.PositionalList;

public class LinkedPositionalList_hw2<E> implements PositionalList<E>{

    public static void main(String[] args) {
        LinkedPositionalList_hw2<Integer> list = new LinkedPositionalList_hw2<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        System.out.println(list.hasNext());
        System.out.println(list.hasPrevious());

        System.out.println(list.nextIndex());
        System.out.println(list.next());
        System.out.println(list.nextIndex());
        System.out.println(list.next());
        System.out.println(list.nextIndex());
        System.out.println(list.next());
        System.out.println(list.nextIndex());

    }

    private static class Node<E> implements Position<E> {

        private E data;
        private Node<E> prev, next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public E getData() throws IllegalStateException {
            if (this.next == null) throw new IllegalStateException("Invalid Position");
            return this.data;
        }

    }

    private Node<E> header, trailer;
    // node to keep track of the current position in the list
    private Node<E> currentNode;
    private int currentIndex = -1;
    private int size;

    public LinkedPositionalList_hw2() {
        this.size = 0;
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, null, null);
        this.header.next = this.trailer;
        this.trailer.prev = this.header;
        currentNode = header;
    }


    //O(1)
    private Position<E> position(Node<E> n) {
        if (n.data == null) return null;
        return n;
    }

    //O(1)
    private Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p;
        if (node.next == null) throw new IllegalArgumentException("Invalid position");
        return node;
    }

    //O(1)
    private Position<E> addBetween(E data, Node<E> prev, Node<E> next) {
        Node<E> newNode = new Node<>(data, prev, next);
        prev.next = newNode;
        next.prev = newNode;
        this.size++;
        return this.position(newNode);
    }

    //O(1)
    @Override
    public int size() {
        return this.size;
    }

    //O(1)
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //O(1)
    @Override
    public Position<E> first() {
        return this.position(this.header.next);
    }

    //O(1)
    @Override
    public Position<E> last() {
        return this.position(this.trailer.prev);
    }

    //O(1)
    @Override
    public Position<E> addFirst(E data) {
        return this.addBetween(data, this.header, this.header.next);
    }

    //O(1)
    @Override
    public Position<E> addLast(E data) {
        return this.addBetween(data, this.trailer.prev, this.trailer);
    }

    //O(1)
    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> n = this.validate(p);
        return this.position(n.prev);
    }

    //O(1)
    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> n = this.validate(p);
        return this.position(n.next);
    }

    //O(1)
    @Override
    public Position<E> addBefore(Position<E> p, E data) throws IllegalArgumentException {
        Node<E> n = this.validate(p);
        return this.addBetween(data, n.prev, n);
    }

    //O(1)
    @Override
    public Position<E> addAfter(Position<E> p, E data) throws IllegalArgumentException {
        Node<E> n = this.validate(p);
        return this.addBetween(data, n, n.next);
    }

    //O(1)
    @Override
    public E set(Position<E> p, E data) throws IllegalArgumentException {
        Node<E> n = this.validate(p);
        E old = n.data;
        n.data = data;
        return old;
    }

    //O(1)
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> n = this.validate(p);
        Node<E> prev = n.prev;
        Node<E> next = n.next;
        prev.next = next;
        next.prev = prev;
        E val = n.data;

        //set all node data to null so the original position p is no longer valid
        n.next = null;
        n.prev = null;
        n.data = null;

        this.size--;
        return val;
    }

    public boolean hasNext(){
        if(currentNode.next == trailer){
            return false;
        }else{
            return true;
        }
    }

    public boolean hasPrevious(){
        if(this.size() == 0) return false;
        if(currentNode == header || currentNode == header.next){
            return false;
        }else{
            return true;
        }
    }

    public E next(){
        if(currentIndex < size - 1){
            E n = currentNode.next.data;
            currentNode = currentNode.next;
            if(currentNode != trailer){
                currentIndex = nextIndex();
            }
            return n;
        }else{
            return null;
        }
    }

    public int nextIndex(){
        if (currentIndex < size - 1) {
            return currentIndex + 1;
        }else{
            throw new IllegalStateException("Already reached end of list!");
        }
    }

    public int previousIndex(){
        if (currentNode !=  header && currentNode.prev != header) {
            return currentIndex - 1;
        }else{
            throw new IllegalStateException("Already reached beginning of list!");
        }
    }

    public String toString(){
        if(size == 0) return "[]";
        else {
            StringBuilder s = new StringBuilder();
            s.append("[");
            Position<E> curr = header.next;
            for(int i = 0; i < size - 1; i++){
                s.append(curr.getData() + ",");
                curr = after(curr);
            }
            s.append(curr.getData() + "]");
            return s.toString();
        }
    }

}

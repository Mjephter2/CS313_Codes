package lecture10;

public class ArrayQueue_hw1<E> implements CircularQueue {

    public static void main(String[] args) {

        ArrayQueue_hw1 list1 = new ArrayQueue_hw1(10);

        for(int i = 0; i <= 9; i++){
            list1.enqueue(i);
        }
        System.out.print("Original List: ");
        System.out.println(list1);

        for(int i = 0 ; i < 15; i++){
            System.out.println("Rotating");
            list1.rotate();
            System.out.println("Size: " + list1.size +  " Top: " + list1.first());
            System.out.println("List after rotation: " + list1);
        }
        System.out.println("********************************");

        while(!list1.isEmpty()){
            System.out.println("dequeue-ing");
            System.out.println("Removed: " + list1.dequeue());
            System.out.println("List after dequeue: " + " Top = " + list1.first() + ": "+ list1);

        }
    }

    private int top;
    private int size = 0;
    private Object[] data;

    public ArrayQueue_hw1(int capacity){
        data = new Object[capacity];
        top = -1;

    }

    @Override
    public void rotate() {
        if(this.size <= 1){
            return;
        }else {
            top = ((top + 1) + this.data.length) % this.data.length;
        }
    }

    @Override
    public void enqueue(Object o) {
        if(this.isEmpty()) {
            top++;
            this.data[top] = o;
            size++;
        }else if(size == data.length){
            throw new IllegalStateException("List Full!");
        }else{
            this.data[((top + size) + this.data.length) % data.length] = o;
            size++;
        }
    }

    @Override
    public E dequeue() {
        if (this.isEmpty()) return null;
        E val = (E) this.data[this.top];
        this.data[this.top] = null;
        this.top = ((top + 1) + data.length) % data.length;
        size--;
        return val;
    }

    @Override
    public E first() throws IllegalArgumentException{
        if(this.isEmpty()){
            System.out.println("Empty List");
            return null;
        }else{
            return (E)this.data[top];
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString(){
        if(this.isEmpty()) return "Size = 0 : []";
        StringBuilder sb = new StringBuilder("Size = " + this.size + " [");
        for(int i = 0; i < size - 1; i++){
            sb.append(this.data[((top + i) + data.length) % data.length] + " ");
        }
        sb.append(data[(top + size - 1) % data.length] + "]");
        return sb.toString();
    }
}

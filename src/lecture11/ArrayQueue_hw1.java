package lecture11;

import lecture10.Queue;

public class ArrayQueue_hw1<E> implements Queue<E> {

    public static void main(String[] args) {
        ArrayQueue_hw1 list1 = new ArrayQueue_hw1(20);
//        for(int i = 0; i < 24; i++){
//            list1.enqueue(i);
//        }
//        System.out.println("original list: " + list1);
//        for(int i = 1; i <= 5; i++) {
//            System.out.println("Item " + i + ": " + list1.get(i));
//        }
//        System.out.println("Item " + 15 + ": " + list1.get(15));
//        System.out.println("Item " + 23 + ": " + list1.get(30));
        for(int i= 0; i < 50; i++){
            list1.set(0,i);
        } list1.set(10, 1000);
        System.out.println(list1);

    }

    private Object[] data;
    private int first, size;

    public ArrayQueue_hw1(int cap) {
        this.data = new Object[cap];
        this.first = 0;
        this.size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (this.size == this.data.length) resize(2 * data.length);
        int next = (this.first + this.size) % this.data.length;
        this.data[next] = e;
        this.size++;
    }

    @Override
    public E dequeue() {
        if (this.isEmpty()) return null;
        E val = (E) this.data[this.first];
        this.data[this.first] = null;
        this.first = (this.first + 1) % this.data.length;
        this.size--;
        return val;
    }

    @Override
    public E first() {
        return (E) this.data[this.first];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    private void resize(int newSize){
        Object[] newData = new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[(first + i) % data.length];
        }
        first = 0;
        this.data = newData;
    }


    // homework
    // #2
    public E get(int index){
        if(index > size) throw new IndexOutOfBoundsException("No Such Item!");
        return (E)this.data[(first + index - 1 + data.length) % data.length];
    }
    public void set(int index, E e){
        if(index < 0 || index > size ){
            System.out.println("Invalid Index!");
            return;
        } else if(isEmpty()) {
            System.out.println("Empty list. Setting first item:");
            enqueue(e);
            return;
        }else {
            if(this.size == data.length) {
                this.resize(2*data.length);
            }else{
                this.resize(data.length);
            }
            for(int i = first + size; i > index; i-- ){
                data[i] = data[i-1];
            }
            data[index] = e;
        }
        size++;

    }

    public String toString(){
        if(isEmpty()) return "Size = 0 : []";
        StringBuilder sb = new StringBuilder("Size = " + this.size + " [");
        for(int i = 0; i < size - 1; i++){
            sb.append(this.data[((first + i) + data.length) % data.length] + " ");
        }
        sb.append(data[(first + size - 1) % data.length] + "]");
        return sb.toString();
    }

}

package lecture9;
/*
 * Homework
 * # 1
 * In the lecture, we implemented a Stack using an array.  Implement a Stack using an ArrayList.
 * Are there any advantages to the ArrayList implementation over an array?
 */

import lecture8.ArrayList;

public class ArrayListStack<E> implements Stack<E> {

    public static void main(String[] args) {
        ArrayListStack<Integer> list = new ArrayListStack<>(10);
        for(int i = 0; i <= 9; i++){
            list.push(i);
            System.out.println("Stack list size after pushing: " + list.size());
            System.out.println("Top of list: " + list.top());
        }
        while(!list.isEmpty()){
            System.out.println(list.pop());
            System.out.println("ArrayListStack size after popping: " + list.size());
            System.out.println("Top of list: " + list.top());
        }
    }

    private ArrayList<E> data;

    public ArrayListStack(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    @Override
    public void push(E e) {
        data.add(e);
    }

    @Override
    public E pop() {
        if (this.data.isEmpty()) return null;
        E val = this.top();
        this.data.remove(this.data.size() - 1);
        return val;
    }

    @Override
    public E top() {
        if(data.size() == 0) return null;
        return (E) this.data.get(data.size() - 1);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.size() == 0;
    }

}

package lecture10;

import java.util.Comparator;

public abstract class AbstractQueue<E> implements Queue<E> {
    public void sort(Comparator<E> comp){
        mergeSort(this,comp);
    };
    public static <E> void merge(Queue<E> s1, Queue<E> s2, Queue<E> s, Comparator<E> comp){
        while(!s1.isEmpty() && !s2.isEmpty()){
            if(comp.compare(s1.first(),s2.first()) < 0){
                s.enqueue(s1.dequeue());
            }else{
                s.enqueue(s2.dequeue());
            }
        }
        while(!s1.isEmpty()){
            s.enqueue(s1.dequeue());
        }
        while(!s2.isEmpty()){
            s.enqueue(s2.dequeue());
        }
    }
    public static <E> void mergeSort(Queue<E> s, Comparator<E> comp){
        int n = s.size();
        if(n < 2) return;

        Queue<E> s1 = new LinkedQueue<>();
        Queue<E> s2 = new LinkedQueue<>();
        while(s1.size() < n / 2){
            s1.enqueue(s.dequeue());
        }
        while(!s.isEmpty()){
            s2.enqueue(s.dequeue());
        }
        mergeSort(s1, comp);
        mergeSort(s2, comp);

        merge(s1,s2,s,comp);

    }
}

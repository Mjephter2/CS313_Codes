package lecture4;

public interface List<E> {

    void set(int i, E e);

    void add(int i, E e);

    E get(int i);

    E remove(int i);

    int size();

    boolean isEmpty();

}

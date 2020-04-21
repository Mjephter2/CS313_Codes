package lecture1;

public interface Container<E> {

    void setItem(E item);

    E getItem();

    E dump();

}

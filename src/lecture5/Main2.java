package lecture5;

public class Main2 {

    public static void main(String[] args) {

        SinglyLinkedList<Character> l = new SinglyLinkedList<>();

        l.addLast('a');
        l.addLast('b');
        l.addLast('c');
        l.addLast('d');

        System.out.println("Initial list: " + l + ". Size " + l.size());
        l.removeLast();
        l.removeLast();
        l.removeLast();
        l.removeLast();
        System.out.println("After removals: " + l + ". Size " + l.size());


    }

}
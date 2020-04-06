package lecture5;

public class Main {

    public static void main(String[] args) {

        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');

        Node z = new Node('z');

        a.next = b;
        b.next = c;

        //adding an element between existing nodes is O(1)
        Node temp = b.next;
        b.next = z;
        z.next = temp;

        printNodes(a);

    }

    public static void printNodes(Node start) {

        Node current = start;
        while(current != null) {
            System.out.printf("%s --> ", current.data);
            current = current.next;
        }
        System.out.println("null");
    }

}
package lecture1;
/*
*Homework
* #2
 */

public class DividedBox<P,Q> extends Box<P> {
    private Q item2;

    public DividedBox() {
        this.setItem(null);
        this.item2 = null;
    }
    public DividedBox(P item1, Q item2){
        this.setItem(item1);
        this.setItem2(item2);
    }
    public DividedBox(P item1, Q item2, double length, double width, double height){
        super(item1,length, width, height);
        this.setItem2(item2);
    }

    public Q getItem2() {
        return item2;
    }

    public void setItem2(Q item2) {
        this.item2 = item2;
    }

    @Override
    public P dump() {
       return null;
    }
    public void dump_dividedBox(){
        this.setItem(null);
        this.setItem2(null);
    }

    @Override
    public String toString() {
        return "Box containing: " + getItem().getClass().getSimpleName() + " '" + getItem() + "' and "  +
                                    getItem2().getClass().getSimpleName() + " '" + getItem2() + "'\n";
    }

    public static void main(String[] args) {
        DividedBox<String, Integer> divBox1;
        String item1 = "Welcome!";
        Integer item2 = 2020;
        divBox1 = new DividedBox<>(item1, item2, 20,20,20);
        System.out.println(divBox1);
        divBox1.setItem2(2021);
        System.out.println(divBox1);
        divBox1.setItem("Welcome Home");
        System.out.println(divBox1);
    }
}

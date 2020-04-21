package lecture1;

public class Box<P> extends ShippingContainer<P> {

    private P item;

    public Box(P item) {
        this.item = item;
    }

    public Box() {
        this(null);
    }

    public Box(P item, double l, double w, double h) {
        super(l,w,h);
        this.item = item;
    }

    @Override
    public void setItem(P item) {
        this.item = item;
    }

    @Override
    public P getItem() {
        if (this.item == null) throw new EmptyBoxException("Box is empty");
        return this.item;
    }

    @Override
    public P dump() {
        P temp = getItem();
        this.item = null;
        return temp;
    }


    /*
    * Homework
    * #1
    */
    public static void main(String[] args) {
        int length = 40, width = -20, height = 30;

        try{
            if(width <= 0) throw new Exception("Negative Width");
            Box<String> b1 = new Box<>("Box1_content",length, width, height);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}

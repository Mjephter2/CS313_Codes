package lecture1;
/*
*Homework
* #3
 */

public class ShippingCrate<T> extends ShippingContainer<T> {
    private int num_boxes;
    private Box[] content;

    public ShippingCrate(){
        super();
        num_boxes = 0;
        content = new Box[num_boxes];
    }
    public ShippingCrate(double crate_size, double box_size){
        super(crate_size,crate_size,crate_size);
        num_boxes = (int)(this.getVolume() / (box_size*box_size*box_size));
        content = new Box[num_boxes];
        for(int i = 0; i< num_boxes; i++){
            content[i] = new Box(null,box_size,box_size,box_size);
        }

    }

    public int getNum_boxes() {
        return num_boxes;
    }

    public Box[] getContent() {
        return content;
    }


    @Override
    public void setItem(T item) {

    }

    @Override
    public T getItem() {
        return null;
    }

    @Override
    public T dump() {
        return null;
    }
    public String toString(){
        String s = "";
        for(int i = 0; i < num_boxes; i++){
            s += (content[i].getItem()) + ". Box Size: " + content[i].getLength() + " by " + content[i].getWidth() + " by " + content[i].getHeight() +  "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        ShippingCrate<String> crate1 = new ShippingCrate<>(30, 9);
        System.out.println("Number of boxes: " + crate1.getNum_boxes());
        for(int i =0; i < crate1.getNum_boxes(); i++){
            (crate1.getContent())[i].setItem("Box " + i + " content");
        }
        System.out.println(crate1);

        int[] x = new int[10];
    }
}

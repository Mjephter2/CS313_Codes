package lecture1;

public abstract class ShippingContainer<T> implements Container<T> {

    private double length, width, height;

    public ShippingContainer(double l, double w, double h) {
        this.length = l;
        this.width = w;
        this.height = h;
    }


    public ShippingContainer() {
        this(1,1,1);
    }

    public double getVolume() {
        return length * width * height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isFull() {
        return this.getItem() != null;
    }

    public String toString() {
        return "Box containing " + this.getItem() + ". Size: " + length + " by " + width + " by " + height;
    }

}

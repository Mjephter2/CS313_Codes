package lecture4;

public abstract class AbstractList<E> implements lecture4.List<E> {

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    public String toString() {
        if (this.size() == 0) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size() - 1; i++) {
            sb.append(this.get(i) + ", ");
        }
        sb.append(this.get(this.size()-1) + "]");
        return sb.toString();
    }

}

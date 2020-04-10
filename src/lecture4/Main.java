package lecture4;
/*
 * @author: Jephter Maurice
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list1.add(i);
        }
        System.out.println("size: " + list1.size());
        System.out.println(list1);
//        list1.remove(0);
//        System.out.println(list1);
//        list1.remove(3,7);
//        System.out.println(list1);
        list1.removeRange2(1,8);
        System.out.println(list1);
        System.out.println("size: " + list1.size());
    }
}
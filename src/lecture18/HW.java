package lecture18;

import java.util.Comparator;

public class HW {

    /**
     *
     * comparator to order integers in non-decreasing order
     */
    public static class Comp_asc<Integer> implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return (int) o1 - (int) o2;
        }
    }
    /**
     *
     * comparator to order integers in decreasing order
     */
    public static class comp_desc<Integer> implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return (int) o2 - (int) o1;
        }
    }
    /**
     *
     * comparator to order negative in decreasing order and positive integers in ascending order
     */
    public static class comp_negPos<Integer> implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            int o1_int = (int) o1;
            int o2_int = (int) o2;

            if(o1_int < 0 && o2_int < 0){
                return o2_int-o1_int;
            }
            return o1_int - o2_int;
        }
    }
}

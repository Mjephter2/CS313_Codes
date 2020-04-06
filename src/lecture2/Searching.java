package lecture2;

public class Searching {

    public static void main(String[] args) {

        //how many primitive operations occur in the linear search function with this input?

        char[] characters = { 'b', 'e', 'g', 'k', 'p', 'q' };

        int idx = linearSearch('p', characters);

        System.out.println(idx);


        //best case runtime for this data? worst case runtime?

        //how many primitive operations occur in the binary search function with this input?

    }

    public static int linearSearch(char target, char[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++)
            if (target == arr[i]) return i;
        return -1;
    }

    /*
     * O(log(n))
     */
    public static int binarySearch(char target, char[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (target > arr[m]) i = m + 1;
            else j = m;
        }
        if (target == arr[i]) return i;
        return -1;
    }
}

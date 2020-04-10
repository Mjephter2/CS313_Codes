package lecture3;

public class ComplexityPractice1 {

    //O(n)
    public static int method1(int[] arr) {
        int sum = 0;
        //note we increment by 2
        for (int i = 0; i < arr.length; i+=2) {
            sum += arr[i];
        }
        return sum;
    }

    //O(n)
    public static void method2(int[] arr) {
        int oddCount = 0, evenCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) evenCount++;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] % 2 != 0) oddCount++;
        }
        System.out.printf("Evens: %d, odds: %d", evenCount, oddCount);
    }

    //O(n^2)
    public static boolean method3(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == arr[j]) return true;
            }
        }
        return false;
    }

    //consider: what is the input size?
    //O(n)
    public static int method4(char target, char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            //invoke the linearSearch method from last lecture
            int idx = lecture2.Searching.linearSearch(target, arr[i]);
            if (idx != -1) return idx;
        }
        return -1;
    }

    //O(n)
    public static int[] method5(int[] arr, int diff) {
        int[] result = new int[arr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[i] + diff;
        }
        return result;
    }

    /*
     * Additional
     * Returns the sum of the first n integers of the given array
     * O(n)
     */
    public static int linearSum(int[ ] data, int n) {
        if (n == 0)
            return 0;
        else
        return linearSum(data, n - 1) + data[n - 1];
    }
    /*
     * Reverses the contents of sub-array data[low] through data[high] inclusive.
     * O(n)
     */
    public static void reverseArray(int[ ] data, int low, int high) {
        if (low < high) {
            int temp = data[low];
            data[low] = data[high];
            data[high] = temp;
            reverseArray(data, low + 1, high - 1);
        }
    }
    /*
     * Computes the value of x raised to the nth power, for non negative integer n.
     * O(n)
     */
    public static double power(double x, int n){
        if(n == 0) return 1;
        else{
            return x * power(x, n - 1);
        }
    }
    /*
     * Better power function
     * O(log(n))
     */
    public static double power2(double x, int n){
        if(n == 0) return 1;
        else{
            double partial = power(x,n/2);
            double result = partial * partial;
            if(n % 2 == 1) result *= x;
            return result;
        }

    }
    /*
     * Returns the sum of the sub-array data[low] through data[high] inclusive
     * O(n)
     */
    public static int binarySum(int[] data, int low, int high){
        if(low > high) return 0;
        else if(low == high) return data[low];
        else{
            int mid = (low + high) / 2;
            return binarySum(data,low, mid) + binarySum(data, mid + 1, high);
        }
    }
    /*
     * Returns true if there are no duplicates values from data[low] to data[high]
     * Inefficient use of recursion
     * O(2^n)
     */
    public static boolean unique3(int[] data, int low, int high){
        if(low >= high) return true;
        else if(!unique3(data, low, high -1 )) return false;
        else if(!unique3(data, low + 1, high)) return false;
        else return (data[low] != data[high]);
    }
    /*
     *Returns the nth Fibonacci number(inefficiently)
     * O(2^n)
     */
    public static int fibonacciBad(int n){
        if(n <= 1) return n;
        else{
            return fibonacciBad(n - 2) + fibonacciBad(n - 1);
        }
    }
    /*
     * Returns array containing the pair of Fibonacci numbers, F(n) and F(n-1)
     * O(n)
     */
    public static long[] fibonacciGood(int n){
        if(n <= 1){
            long[] answer = {n,0};
            return answer;
        }else{
            long[] temp = fibonacciGood(n - 1);
            long[] answer = {temp[0] + temp[1], temp[0]};
            return answer;
        }
    }
    /*
     * Infinite recursion
     * Do not call this (infinite) Fibonacci version
     */
    public static int fibonacciVeryBad(int n){
        return fibonacciVeryBad(n);
    }


}

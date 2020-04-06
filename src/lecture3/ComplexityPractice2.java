package lecture3;

public class ComplexityPractice2 {

    //O(n^2)
    public static int method1(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
        return sum;
    }

    //O(n)
    // factorial
    public static int method3(int n) {
        if (n == 1) return 1;
        return n * method3(n - 1);
    }

    //O(n^2)
    // palindrome test
    public static boolean method2(String s) {
        if (s.length() <= 1) return true;
        char start = s.charAt(0);
        char end = s.charAt(s.length()-1);
        String chopped = s.substring(1, s.length() - 1);
        return (start == end) && method2(chopped);
    }

    //O(2^n)
    // Fibonacci
    public static int method4(int n) {
        if (n <= 1) return n;
        return method4(n-2) + method4(n-1);
    }

    //O(n)
    public static int method5(int[] arr, int start, int end) {
        if (start > end) return 0;
        if (start == end) return arr[start];
        int midpoint = (start + end) / 2;
        return method5(arr, start, midpoint) + method5(arr, midpoint + 1, end);
    }

}

package lecture2;

public class LoopPrimitives {

    public static void main(String[] args) {

        //example 1
        //how many primitive operations occur in the forLoop function?
        int s1 = forLoop();

        //example 2
        //how many primitive operations occur in the forLoop2 function?
        int[] arr = { 0, 1, 2, 3, 4 };
        int s2 = forLoop2(arr);

    }

    //plus 2 for method invocation and var
    public static int forLoop() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum = sum + i;
        }
        return sum;
    }

    //5 elements
    public static int forLoop2(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }



    //equivalent to forLoop()
    public static int whileLoop() {
        int sum = 0;
        int i = 0;
        while (i < 5) {
            sum = sum + i;
            i++;
        }
        return sum;
    }

}

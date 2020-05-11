package lecture21;

import java.util.Random;

public class hw21 {

    private static Random rand = new Random();

    public static void main(String[] args) {
        String test1 = "Hello";
        for(int i = 0; i < 10; i++) {
            System.out.println(myHashCode(test1, 10));
        }
    }

    public static int myHashCode(String input, int numCell){
        int hCode = 0;
        int length = input.length();
        for(int i = 0; i < length; i++){
            hCode += input.charAt(i) * rand.nextInt(97);
        }
        hCode = hCode % numCell;
        return hCode;
    }
}

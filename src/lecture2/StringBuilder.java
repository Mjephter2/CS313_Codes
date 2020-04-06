package lecture2;

public class StringBuilder {

    public static void main(String[] args) {

        String s = repeat('a', 100);

        System.out.println(s);
    }

    //challenge: how many primitive operations occur in the repeat function (in terms of input size n)?
    //consider the fact that a String is not a primitive data type
    //what is the best big O characterization of repeat(...)?
    public static String repeat(char c, int n) {
        String result = "";
        for (int i = 0; i < n; i++)
            result += c;
        return result;
    }

}

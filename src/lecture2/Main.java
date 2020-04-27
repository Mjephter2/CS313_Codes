package lecture2;

import java.lang.StringBuilder;
public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(repeat1('#',12000000));
        //System.out.println(repeat2('#',12000000));  // way faster
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        System.out.println(elapsed);
    }

    public static String repeat1(char c, int n){
        String answer = "";
        for(int i = 0; i < n; i++){
            answer +=c;
        }
        return answer;
    }
    public static String repeat2(char c, int n){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }

    /*
    *Homework
    * # 3 a
     */
    public static String constantRuntime(){
        return "Hello";
    }

    /*
     *Homework
     * # 3 b
     */
    public static int linearRuntime(int[] par){
        int sum = 0;
        for(int i = 0; i < par.length; i++){
            sum += par[i];
        }
        return sum;
    }

    /*
     *Homework
     * # 3 c
     */
    public static int[][] quadraticRuntime(int[] par){
        int size = par.length;
        int[][] answer = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; i < size; i++){
               answer[i][j] = par[i];
            }
        }
        return answer;
    }
}

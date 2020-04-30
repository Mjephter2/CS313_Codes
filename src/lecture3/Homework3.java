package lecture3;

public class Homework3 {
    public static void main(String[] args) {

//        int[] array1 = new int[10];
//        for(int i = 0; i < array1.length; i++){
//            array1[i]= i + 1;
//        }
//        System.out.println(printArray(array1));
//        System.out.println("*******************");
//        array1 = arrayInsert(array1, 1, 2);
//        System.out.println(printArray(array1));
//        System.out.println("********************");
//        array1 = arrayInsert(array1, 10, 1);
//        System.out.println(printArray(array1));
//        System.out.println("*********************");

        char[] array2 = new char[5];
        for(int i = 0; i < array2.length; i++){
           array2[i]= (char)('a' + i);
        }
        System.out.println(printArray(array2));
        array2 = arrayRemove(array2, 4);
        System.out.println(printArray(array2));
        array2 = arrayRemove(array2, 0);
        System.out.println(printArray(array2));
        array2 = arrayRemove(array2, 4);
        System.out.println(printArray(array2));
    }

    /*
     * 3- Write the code to define an insert(...) method that takes an array of integers as an argument,
     * an integer that is to be inserted, and the index where it is to be inserted.
     * The method should return an array with the insertion performed
     */
    public static int[] arrayInsert(int[] data, int index, int target){
        int data_size = data.length;
        if(index < 0) return data;
        else if(index >= data.length) return data;
        else{
            int[] answer = new int[data_size + 1];
            for(int i = 0; i < index; i++){
                answer[i] = data[i];
            }
            for(int i = index + 1; i < answer.length; i++){
                answer[i] = data[i - 1];
            }
            answer[index] = target;
            return answer;
        }
    }
    /*
     * 4 - Write the code to define a remove(...) method that takes an array of characters as an argument
     * and the index of a character that is to be removed.  Return an array with the removal performed.
     */
    public static char[] arrayRemove(char[] data, int index){
        int size = data.length;
        if(index < 0 || index >= size) return data;
        else{
            char[] answer = new char[size - 1];
            for(int i = 0; i < index; i++){
                answer[i] = data[i];
            }
            for(int i = index; i < answer.length; i++){
                answer[i] = data[i + 1];
            }
            return answer;
        }
    }


    // function to print the content of an array of integers
    private static String printArray(int[] data){
        String answer = "[ ";
        for(int i = 0; i < data.length; i++){
            answer += data[i] + " ";
        }
        answer += "]";
        return answer;
    }
    // function to print the content of an array of characters
    private static String printArray(char[] data){
        String answer = "[ ";
        for(int i = 0; i < data.length; i++){
            answer += data[i] + " ";
        }
        answer += "]";
        return answer;
    }
}

package Misc;

import java.util.Random;

public class BubbleSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[20];
        for(int i = 0; i < 20; i++){
            array[i] = rand.nextInt(100);
        }
        System.out.println("Original Array: " + print(array));
        bubbleSort(array);
        System.out.println("Array after bubble sort: " + print(array));

    }

    private static String print(int[] array) {
        StringBuilder s = new StringBuilder("[");
        for(int i = 0; i < array.length - 1; i++){
            s.append(array[i] + ", ");
        }
        s.append(array[array.length - 1] + "]");
        return s.toString();
    }

    /*
     * Repeatedly compares adjacent items
     * larger items "bubble" right
     * O(n^2)
     */
    public static void bubbleSort(int[] list){
        int i, j, temp = 0;
        for (i = 0; i < list.length - 1; i++) {
            for (j = 0; j < list.length - 1; j++) {
                if (list[j] > list[j + 1]) {
                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }
}

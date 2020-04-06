package Misc;

import java.util.Random;

public class SelectionSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[20];
        for(int i = 0; i < 20; i++){
            array[i] = rand.nextInt(100);
        }
        System.out.println("Original Array: " + print(array));
        selectionSort(array);
        System.out.println("Array after selection sort: " + print(array));
    }
    private static String print(int[] array) {
        StringBuilder s = new StringBuilder("[");
        for(int i = 0; i < array.length - 1; i++){
            s.append(array[i] + ", ");
        }
        s.append(array[array.length - 1] + "]");
        return s.toString();
    }
    public static void selectionSort(int[] list){
        int i, j, minValue, minIndex, temp = 0;
        for (i = 0; i < list.length; i++) {
            minValue = list[i];
            minIndex = i;
            for (j = i; j < list.length; j++) {
                if (list[j] < minValue) {
                    minValue = list[j];
                    minIndex = j;
                }
            }
            if (minValue < list[i]) {
                temp = list[i];
                list[i] = list[minIndex];
                list[minIndex] = temp;
            }
        }
    }
}

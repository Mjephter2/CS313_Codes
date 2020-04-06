package lecture9;

/*
 * Homework
 * #2
 * Write a boolean method that uses a Stack to determine if a String is a palindrome (a palindrome is a String that
 * reads the same forwards and backwards, i.e. “racecar”).  Hint: There are multiple ways to do this, but the simplest
 * is to loop through the String twice.
 */

import java.util.Scanner;

public class Homework_n2 {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter a string to determine if it is a palindrome! ");
        String input = userInput.nextLine();

        if(isPalindrome(input)){
            System.out.println(input + " is a palindrome!");
        }else{
            System.out.println(input + " is not a palindrome!");
        }
    }

    public static boolean isPalindrome(String s){
        Stack<Character> stack = new LinkedStack<>();
        int midIndex;
        if(s.length() % 2 == 0){
            midIndex = s.length() / 2;
        }else{
            midIndex = s.length() / 2 + 1;
        }
        for(int i = s.length() / 2; i >= 0; i--){
            stack.push(s.charAt(i));
        }
        for(int j = s.length() - 1; j >= s.length() - midIndex; j--){
            if(stack.pop() != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}

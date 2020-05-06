package lecture20;

import lecture18.Entry;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class HW20 {

    public static void main(String[] args) {
        File testFile = new File("/Users/jephtermaurice/OneDrive/College/Spring 2020/CSCI 313/CS313_Codes/src/lecture20/5200.txt");
        File outFile = new File("/Users/jephtermaurice/OneDrive/College/Spring 2020/CSCI 313/CS313_Codes/src/lecture20/outTestFile.txt");

        UnsortedTableMap<String, Integer> freq = new UnsortedTableMap<>();
        try(Scanner input = new Scanner(testFile).useDelimiter("[^a-zA-Z]+");
            PrintWriter output = new PrintWriter(outFile)){

            output.write("Top 10 words in the file: " + testFile.getPath() + "\n");
            while(input.hasNext()){
                String word = input.next().toLowerCase();
                Integer count = freq.get(word);
                if(count == null) count = 0;
                freq.put(word, count + 1);
            }
            int count = 0;
            while(!freq.isEmpty() && count < 10){
                String word = topWord(freq);
                output.write( ++count + ".\t" + word + "\t:\t" + freq.remove(word) + " times\n");
                }


        }catch(IOException e){
            System.out.println("Error opening file! " + e.getMessage());
        }
    }
    //O(n)
    private static String topWord(UnsortedTableMap<String, Integer> map){
        String answer = null;

        Iterator<Integer> freq = map.values().iterator();
        Integer maxFreq = Integer.MIN_VALUE;
        while(freq.hasNext()){
            Integer curr = freq.next();
            if(maxFreq < curr){
                maxFreq = curr;
            }
        }
        for(Entry<String, Integer> entry: map.entrySet()){
            if(maxFreq == entry.getValue()){
                answer = entry.getKey();
            }
        }
        return answer;
    }
}

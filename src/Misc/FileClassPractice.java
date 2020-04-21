package Misc;

import java.io.File;

public class FileClassPractice {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Jephter\\OneDrive\\College\\Spring 2020\\CSCI 313\\Lecture 4 - Feb 5";
        diskUsage(new File(fileName));
    }

    /*
     * Calculates the total disk usage (in bytes) of the portion of the file system rooted
     * at the given path, while printing a summary akin to the standard 'du' Unix tool
     */
    public static long diskUsage(File root){
        long total = root.length();
        if(root.isDirectory()){
            for(String childName: root.list()){
                File child = new File(root, childName);
                total += diskUsage(child);
            }
        }
        System.out.println(total + "\t" + root);
        return total;
    }
}

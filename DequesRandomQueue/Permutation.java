// Permutation.java
// Reads strings and outputs them at random using a Randomized Queue data structure
// by Eric Mancini

// Reads in strings from a file/stdin, then
// prints k of them, uniformly and at random


/* 
Compile: 
javac-algs4 Permutation.java
Run: 
java-algs4 Permutation 3 < distinct.txt
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


public class Permutation {
    
    
   public static void main(String[] args)
   {
       if (args.length < 1) {
           throw new IllegalArgumentException("Must provide an argument for number of items to print!");
       }
       // number of items to print, based on command line input
       int k = Integer.parseInt(args[0]);
       
       RandomizedQueue myRQ = new RandomizedQueue();
       
       System.out.println("Hello, World. This was run from Permutation.");
       while (!StdIn.isEmpty()) {
           String newItem = StdIn.readString();
           //System.out.println(test);
           myRQ.enqueue(newItem);
       } 
       
       if (k > myRQ.size()){
           throw new IllegalArgumentException("Cannot print more items than were read in!");
       }
       
       System.out.println("=== Input Read! ===");
       myRQ.printArray();
       System.out.println("=== End input read ===");

       
       // Iterator version:
       System.out.println("=== Iterator Version: ===");
       Iterator myIt = myRQ.iterator();
       /*
       while(myIt.hasNext()){
           System.out.println(myIt.next());
       }
       */
       for (int i = 0; i < k; i++){
           System.out.println(myIt.next());
       }
       System.out.println("=== Class method version: ===");
       for (int j = 0; j < k; j++){
           System.out.println(myRQ.dequeue());
       }
       
       
   }
}
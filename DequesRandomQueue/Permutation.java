// Permutation.java
// Reads strings and outputs them at random using a Randomized Queue data structure
// by Eric Mancini


/* 
Compile: 
javac-algs4 Permutation.java
Run: 
java-algs4 Permutation 3 < distinct.txt
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


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
       System.out.println("=== Input Read! ===");
       myRQ.printArray();
       
       // TODO: print k items at random
       // TODO: Use the iterable interface to accomplish this!
       
   }
}
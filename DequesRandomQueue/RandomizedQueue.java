// RandomizedQueue.java
// Implements the randomized queue data structure from:
// http://coursera.cs.princeton.edu/algs4/assignments/queues.html
// By Eric Mancini

import java.util.Iterator;
import java.util.Random;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a; // array of Items
    private int n; // number of items in the array
    
   // construct an empty randomized queue 
   public RandomizedQueue()
   {
       // cast the new array as one of Items because Java/Reasons/Etc.
       // "the ugly cast"
       a = (Item[]) new Object[1];
       n = 0;        
   }
   
   // is the randomized queue empty?
   public boolean isEmpty()
   {
       return n == 0;
   }
       
   // return the number of items in the randomized queue    
   public int size()
   {
       System.out.println(a.length);
       return n;
   }
       
   // add the item
   public void enqueue(Item item)
   {
       System.out.println("Adding " + item + " . . . ");
       // if the array is full, make a new array that's twice as big
       if (n == (a.length) && a.length > 0){
           System.out.println("Array is too small! Growing...");
           grow();
           System.out.println("New array size: " + a.length);
       }
       
       // StdRandom.shuffle(a);
       
       // add the item, increment our current location and number of items counters
       a[n] = item;
       n += 1;

       
   }
   
   // double the array size if it gets full
   private void grow(){
       Item[] temp = (Item[]) new Object[n * 2];
       for (int i = 0; i < a.length; i++){
           temp[i] = a[i];
       }
       // make a point to the new, larger array
       a = temp;
       
       // Test: print the new array
       /*
       for (int j = 0; j < a.length; j++){
           System.out.print(a[j]);
       }
       */
   }
   
   // TODO: Remove from public API
   public void printArray(){
       System.out.print("Array: - ");
       for (int i = 0; i < a.length; i++){
           System.out.print(a[i]);
           System.out.print(" - ");
       }
       System.out.println();
   }
   
   // Halve the array if we're using a quarter of our space or less
   private void shrink(){
       System.out.println("Shrinking array...");
       Item[] temp = (Item[]) new Object[a.length / 2];
       for (int i = 0; i < n; i++){
           temp[i] = a[i];
       }
       a = temp;       
   }   
  
   // remove and return a random item
   public Item dequeue()
   {
       if (n < 1){
            throw new java.lang.IllegalArgumentException("Empty RQ; can't dequeue");
       }
       Random rand = new Random();
       int r = rand.nextInt(n);
       System.out.println("I would remove index " + r);
       // remove the item at that index and shorten the array if needed
       Item chosen_one = a[r];
       a[r] = a[n - 1];
       a[n - 1] = null;       
       n -= 1;
       if (n <= a.length / 4){
           shrink();
       }
       return chosen_one;
   }
   
     
   // return a random item (but do not remove it)
   public Item sample()
   {
       Random rand = new Random();
       int r = rand.nextInt(n);
       return a[r];
   }
   
   /*         
   // return an independent iterator over items in random order
   public Iterator<Item> iterator()
   {
   }
   */
   
   // TODO: Update this boilerplate taken from my prior data structure
   public Iterator<Item> iterator()
   {
    return new RndQueueIterator();   
   }
   

// an iterator, doesn't implement remove() since it's optional
    private class RndQueueIterator implements Iterator<Item> {
        private Item[] iteratorArray;
        private int i;

        public RndQueueIterator()
        {
            i = 0;
            // build an array of just the right size
            iteratorArray = (Item[]) new Object[n];
            StdRandom.shuffle(iteratorArray);            
        }

        public boolean hasNext()
        {
            return i <= (iteratorArray.length - 1);
        }
        
        public void remove()
        { 
            // not implemented
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (!hasNext()) {
                throw new IllegalArgumentException("Nothing left!");
            }
            Item respItem = a[i];
            i++;
            return respItem;
        }
    }
          
   // unit testing (optional)
   public static void main(String[] args)
   {
       System.out.println("Hello, World. This was run from RandomizedQueue.");
       RandomizedQueue<Integer> testRQ = new RandomizedQueue<Integer>();
       System.out.println("Array size: ");
       System.out.println(testRQ.size());
       testRQ.printArray();
       testRQ.enqueue(3);
       testRQ.printArray();
       testRQ.enqueue(2);
       testRQ.printArray();
       testRQ.enqueue(4);
       testRQ.printArray();
       testRQ.enqueue(2);
       testRQ.printArray();
       // testRQ.sample();
       System.out.println(testRQ.dequeue());
       testRQ.printArray();
       System.out.println(testRQ.dequeue());
       testRQ.printArray();
       System.out.println(testRQ.dequeue());
       testRQ.printArray();
       System.out.println(testRQ.dequeue());
       testRQ.printArray();
       testRQ.enqueue(7);
       testRQ.printArray();

   }
}
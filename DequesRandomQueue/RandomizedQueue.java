// RandomizedQueue.java
// Implements the randomized queue data structure from:
// http://coursera.cs.princeton.edu/algs4/assignments/queues.html
// By Eric Mancini

// 12/23/3017 -- dev environment moved to laptop

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a; // array of Items
    private int n; // size of array 
    
   // construct an empty randomized queue 
   public RandomizedQueue()
   {
       // hoo hoo ha ha
   }
   
   // is the randomized queue empty?
   public boolean isEmpty()
   {
       // true if the array has 0 items, false if it has any items
       return n == 0;
   }
       
   // return the number of items on the randomized queue    
   public int size()
   {
       return 0;
   }
       
   // add the item
   public void enqueue(Item item)
   {
   }
   
   /*
   // remove and return a random item
   public Item dequeue()
   {
   }
       
   // return a random item (but do not remove it)
   public Item sample()
   {
   }
       
   // return an independent iterator over items in random order
   public Iterator<Item> iterator()
   {
   }
   */
   
   // TODO: Update this boilerplate taken from my prior data structure
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator()
   {
    return new RndQueueIterator();   
   }
   

// an iterator, doesn't implement remove() since it's optional
    private class RndQueueIterator implements Iterator<Item> {
        private int i;

        public RndQueueIterator()
        {
            i = n - 1;
        }

        public boolean hasNext()
        {
            return i >= 0;
        }
        
        public void remove()
        { 
         // not implemented  
        }

        public Item next()
        {
            if (!hasNext()) {
                throw new IllegalArgumentException("Nothing left!");
            }
            return a[i--];
        }
    }
          
   // unit testing (optional)
   public static void main(String[] args)
   {
       System.out.println("Hello, World. This was run from RandomizedQueue.");
   }
}
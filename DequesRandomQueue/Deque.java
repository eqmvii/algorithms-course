// Deque.java
// Implements the deque queue/stack combo data structure from:
// http://coursera.cs.princeton.edu/algs4/assignments/queues.html
// By Eric Mancini


import java.util.Iterator;

// Implements a deque - a combination list/stack data structure
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of the deque
    private Node<Item> last;     // end of the deque
    private int n; // number of items in the deque

// helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
   
   // construct an empty deque
   public Deque()
   {
       first = null;
       n = 0;
   }
   
  // is the deque empty? 
   public boolean isEmpty()
   {
       if (first == null){
           return true;
       }
       else {
           return false;
       }
   }
   
   // return the number of items on the deque
   public int size()
   {
       if (n == 0){
           return 0;
       }
       System.out.println("Items in your deque:");
       // For testing purposes only, print out everything in testDeq
       Node<Item> crawler = first;
       while (crawler.next != null){
           System.out.print(crawler.item + " <-> ");
           crawler = crawler.next;
       }   
       System.out.print(crawler.item);
       System.out.println();
       
       return n;
   }
   
   // add the item to the front of the linked list
   // like 
   public void addFirst(Item item)
   {
       if (item == null){
           throw new java.lang.IllegalArgumentException("Can't add null to first");
       }
       
       // create a new node
       Node<Item> newNode = new Node<Item>();
       // add the item to the new node
       newNode.item = item;
       // made the new node point at the old first node
       newNode.next = first;
       // make first point at this node
       first = newNode;
       
       // increment n
       n++;
   }
   
   // add the item to the end of the linked list
   public void addLast(Item item)
   {
       
       if (item == null){
           throw new java.lang.IllegalArgumentException("Can't add null to last");
       }
              
       // Handle case of this being the first thing called
       if (n == 0) {
           addFirst(item);
           return;
       }
       
       // crawl to the end of the linked list
       Node<Item> crawler = first;
       while (crawler.next != null){
           crawler = crawler.next;
       }   
       // create a new node
       Node<Item> newNode = new Node<Item>();
       newNode.item = item;
       newNode.next = null;
       crawler.next = newNode;
       n++;
       
       
   }
   
   
   // remove and return the item from the front of the linked list
   public Item removeFirst()
   {
       if (n == 0){
           throw new java.lang.IllegalArgumentException("Can't remove first; list empty");
       }
       
       // save the item in the first node to return
      Item removedItem = first.item;
      // move the first pointer to the next item
      first = first.next;
      n--;
      
      return removedItem;
   }
   
   
   // remove and return the item from the end of the linked list 
   public Item removeLast()
   {             
       if (n == 0){
           throw new java.lang.IllegalArgumentException("Can't remove last; list empty");
       }
       
       // if there's only one node, return it
       if (n == 1) {
           Item returnItem = first.item;
           first = null;
           n--;
           return returnItem;
       }
       
       // crawl to the end of the linked list
       Node<Item> crawler = first;
       while (crawler.next.next != null){
           crawler = crawler.next;
       }   
       Item returnItem = crawler.next.item;
       crawler.next = null;
       n--;
       return returnItem;
   }
    
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator()
   {
    return new DequeIterator(first);   
   }
   

// an iterator, doesn't implement remove() since it's optional
    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> first)
        {
            current = first;
        }

        public boolean hasNext()
        {
            return current != null;
        }
        
        public void remove()
        { 
         // not implemented  
        }

        public Item next()
        {
            if (!hasNext() ) {
                throw new IllegalArgumentException("Nothing left!");
            }
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }  
   
   // unit testing
   public static void main(String[] args)
   {
       System.out.println("Hello, World. This was run from Deque.");
       Deque<String> testDeq = new Deque<String>();
       
       if (testDeq.isEmpty()){
           System.out.println("Your testDeque is empty :(");
       } else {
           System.out.println("Your testDeque has some stuff in it!");
       }
       
       System.out.println( "Items in testDeque: " + testDeq.size());
       
       testDeq.addFirst("Hello");
       
       if (testDeq.isEmpty()){
       System.out.println("Your testDeque is empty :(");
       } else {
           System.out.println("Your testDeque has some stuff in it!");
       }
       
       System.out.println( "Items in testDeque: " + testDeq.size());
       
        testDeq.removeFirst();
        
       if (testDeq.isEmpty()){
       System.out.println("Your testDeque is empty :(");
       } else {
           System.out.println("Your testDeque has some stuff in it!");
       }
       
       System.out.println( "Items in testDeque: " + testDeq.size());
       
       // testDeq.addLast("Ruin anything?");
       testDeq.addFirst("KArl");
       testDeq.addFirst("You my friend");
       testDeq.addFirst("ohhey");
       testDeq.addLast("THIS WAS ADDED AS LAST");
       testDeq.addFirst("Now I am first!");
       testDeq.addLast("Real Actual Last");
       System.out.println( "Items in testDeque: " + testDeq.size());
       System.out.println("Removed: " + testDeq.removeFirst());
       System.out.println("Removed: " + testDeq.removeFirst());
       System.out.println("Removed: " + testDeq.removeFirst());
       System.out.println("Removed: " + testDeq.removeLast());
       System.out.println("Removed: " + testDeq.removeLast());
       // testDeq.removeLast();
       // testDeq.removeFirst();
       
       System.out.println("Removed: " + testDeq.removeFirst());
       
       if (testDeq.isEmpty()){
       System.out.println("Your testDeque is empty :(");
       } else {
           System.out.println("Your testDeque has some stuff in it!");
       }
       
       System.out.println( "Items in testDeque: " + testDeq.size());
   }
   
}
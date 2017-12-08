import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of the deque

// helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
   
   // construct an empty deque
   public Deque()
   {
   }
   
  // is the deque empty? 
   public boolean isEmpty()
   {
       return false;
   }
   
   // return the number of items on the deque
   public int size()
   {
       return 0;
   }
   
   // add the item to the front
   public void addFirst(Item item)
   {
   }
   
   // add the item to the end
   public void addLast(Item item)
   {
   }
   
   /*
   // remove and return the item from the front
   public Item removeFirst()
   {
      
   }
   
   // remove and return the item from the end
   public Item removeLast()
   {
      
   }
    */
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator()
   {
    return new ListIterator(first);   
   }
   

// an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()
        { 
         // not implemented  
        }

        public Item next() {
            if (!hasNext()) {
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
   }
   
}
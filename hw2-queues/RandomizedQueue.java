import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implements a Randomized queue, where items are
 * removed or sampled in a uniform random manner.
 * @author mike
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * The first node.
     */
    private Node head;
    /**
     * The last node.
     */
    private Node tail;

    /**
     * count is the number of items on the queue.
     */
    private int count;

    /**
     * Construct an empty randomized dequeue.
     */
    public  RandomizedQueue() {
        head = null;
        tail = null;
    }

    /**
     * is the queue empty?
     * @return true if empty
     */
    public boolean isEmpty() {
        if (head == null) {
            count = 0;
            return true;
        }
        return false;
    }

    /**
     * @return the number of items on the queue.
     */
    public int size() {
        return count;
    }

    /**
     * insert the item at the head.
     * @param Item
     */
    public void enqueue(Item item) {

        if (item == null) {
            String msg = "'null value' argument in enqueue.";
            throw new NullPointerException(msg);
        }
        Node oldFront = head;
        head = new Node();
        head.nodeItem = item;
        head.nextNodeBack = oldFront;
        if (oldFront != null) { // if the queue was NOT empty
            oldFront.nextNodeFront = head;
        }
        if (oldFront == null) { // if the queue WAS empty
            tail = head;
        }
        count++;
    }

    /**
     * delete and return a random item.
     * @return Item
     */
    public Item dequeue() {
        if (isEmpty()) {
            String msg = "dequeue() : the queue is empty !";
            throw new NoSuchElementException(msg);
        }
        //StdRandom.setSeed(2);  // for DEBUG
        int randInt = StdRandom.uniform(0, count);
        //System.out.println("randint returns " + randInt);  // for DEBUG
        // start at the head of the queue
        Node current = head;
        // move randInt nodes along the queue
        for (int i = 0; i < randInt; i++) {
            current = current.nextNodeBack;
            //System.out.println(i);  // for DEBUG
        }
        Item item = current.nodeItem;
        // point the nodes around this one to each other

        // this is the ONLY node
        if (current.nextNodeFront == null && current.nextNodeBack == null) {
            head = null;
            tail = null;
            count = 0;
        }
        // this IS the head node
        if (current.nextNodeFront == null && current.nextNodeBack != null) {
            current.nextNodeBack.nextNodeFront = null;
            head = current.nextNodeBack;
            count--;
        }
        // this IS the tail node
        if (current.nextNodeBack == null && current.nextNodeFront != null)  {
            current.nextNodeFront.nextNodeBack = null;
            tail = current.nextNodeFront;
            count--;
                   }
        // this is any node between head and tail
        if (current.nextNodeBack != null && current.nextNodeFront != null) {
            current.nextNodeFront.nextNodeBack = current.nextNodeBack;
            current.nextNodeBack.nextNodeFront = current.nextNodeFront;
            count--;
                    }
        // remove this node
        current = null;  //TODO is this needed for memory free?
        return item;
    }

    /**
     * @return (but do not delete) a random item.
     */
    public Item sample() {
        // return (but do not delete) a random item
        if (isEmpty()) {
            String msg = "dequeue() : the queue is empty !";
            throw new NoSuchElementException(msg);
        }
        int randInt = StdRandom.uniform(0, count);
        // start at the head of the queue
        Node current = head;
        // move randInt nodes along the queue
        for (int i = 0; i < randInt; i++) {
            current = current.nextNodeBack;
        }
        Item item = current.nodeItem;
        return item;
    }

    /**
     * Return an independent iterator over items in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomNodeIterator();
        //return new ListIterator();
    }

    /**
     * Helper methods.
     */

    /**
     *  Inner classes.
     */


    /**
     * This Node class has two pointers, a forward pointer
     *  and a backward pointer, allowing the linked list to
     *  be used in a deque data structure.
     */
    private final class Node {
        /**
         * Reference to an instance of type'Item'.
         */
        private Item nodeItem;
        /**
         * Reference to adjacent node in head direction.
         */
        private Node nextNodeFront;
        /**
         * Reference to adjacent node in tail direction.
         */
        private Node nextNodeBack;
        /**
         * Sets all three fields to null value.
         */
        private Node() {
            nodeItem = null;
            nextNodeFront = null;
            nextNodeBack = null;
        }
    };


    /**
     * Contains the methods needed for the Iterable interface.
     */
    private class RandomNodeIterator implements Iterator<Item> {
        /**
         * create a local copy of the first node in the linked list.
         */
        //private Deque<Item> locQ = new Deque<Item>();
        private Item[] rA =  (Item[]) new Object[count];
        private int index;

        /**
         * 
         */
        private RandomNodeIterator() {
            index = count - 1;
            Node current = head;

            if (head != null) {
                for (int i = 0; i < count; i++) {
                    rA[i] = current.nodeItem;
                    current = current.nextNodeBack;
                }
            }
            //shuffle
            StdRandom.shuffle(rA);
        }

        /**
         * @return true if the iteration has more elements.
         */
        public boolean hasNext() {
            return (index >= 0);
            //return !(locQ.isEmpty());
        }

        /**
         *  Removes the last element.  NOT supported.
         */
        public void remove() { /* not supported */
            String msg = "Don't use the remove() method!";
            throw new UnsupportedOperationException(msg);
        }

        /**
         *@return the next element in the iteration.
         */
        public Item next() {
            if (index >= 0 && index < count) {
                Item item = rA[index--];
                return item;
            } else {
                String msg = "";
                throw new NoSuchElementException(msg);
            }
        }
    };

    /**
     * Contains the methods needed for the Iterable interface.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * create a local copy of the first node in the linked list.
         */
        private Node current = head;

        /**
         * @return true if the iteration has more elements.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         *  Removes the last element.  NOT supported.
         */
        public void remove() { /* not supported */
            String msg = "Don't use the remove() method!";
            throw new UnsupportedOperationException(msg);
        }

        /**
         *@return Returns the next element in the iteration.
         */
        public Item next() {
            Item item = current.nodeItem;
            if (item == null) {
                String msg = "";
                throw new NoSuchElementException(msg);
            }
            current = current.nextNodeBack;
            return item;
        }
    };
}

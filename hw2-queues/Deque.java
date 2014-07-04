import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 *  Implements a double ended queue an array.
 * @author Michael Thornton
 *
 * @param <Item> A place holder the clients type or object being stored.
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * arr is the array holding queued items.
     * arr is sized in the constructor.
     */
    private Item[] arr;

    /**
     * front is the index of the first item in the queue
     */
    private int front;

    /**
     * back is the index of the last item in the queue
     */
    private int back;

    /**
     * capacity is the size of the array
     * this is a dynamic value since the array
     * can resize on demand
     */
    private int capacity;

    /**
     * queueLength is the number of items on the deque.
     */
    private int queueLength;

    /**
     * Construct an empty deque.
     */
    @SuppressWarnings("unchecked")
    public Deque() {
        int startingSize = 8;
        arr = (Item[]) new Object[startingSize];
        front = arr.length / 2;
        back = arr.length / 2;
        queueLength = 0;
    }

    /**
     * is the deque empty?
     * @return true if empty
     */
    public boolean isEmpty() {
        if (queueLength == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return the number of items on the deque.
     */
    public int size() {
        return queueLength;
    }

    /**
     * insert the item at the front.
     * @param item
     */
    public void addFirst(Item item) {
        if (item == null) {
            String msg = "'null value' argument in addFirst.";
            throw new NullPointerException(msg);
        }
        if (front == 0) {
            resizeAndCenter();
        }
         arr[--front] = item;
         queueLength++;
    }

    /**
     *  insert the item at the end.
     * @param item
     */
    public void addLast(Item item)  {
        if (item == null) {
            String msg = "'null value' argument in addLast.";
            throw new NullPointerException(msg);
        }
        if (back >= arr.length - 1) {
            resizeAndCenter();
        }
        arr[++back] = item;
        queueLength++;
     }

    /**
     * delete and return the item at the front.
     * @return the first item in the queue
     */
    public Item removeFirst() {
        if (isEmpty()) {
            String msg = "removeFirst() : deque is empty !";
            throw new NoSuchElementException(msg);
        }
        Item item = arr[front++];
        queueLength--;
        checkForOneQuarterCapacity();
        return item;
    }

    /**
     * delete and return the item at the end.
     * @return the last item in the queue
     */
    public Item removeLast() {
        if (isEmpty()) {
            String msg = "removeLast() : deque is empty !";
            throw new NoSuchElementException(msg);
        }
        Item item = arr[back--];
        queueLength--;
        checkForOneQuarterCapacity();
        return item;
    }

    /**
     * return an iterator over items in order from front to back.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    /**
     * Helper methods.
     */
    private void checkForOneQuarterCapacity() {
        if ( queueLength <= capacity / 4) {
            resizeAndCenter();
        }
    }
    private void resizeAndCenter() {
        //shrink
        //grow
        // copy old queue to center of new queue
        // reassign pointer
    }

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
         * Reference to the previous node.
         */
        //private Node nextNodeFront;
        /**
         * Reference to the next node.
         */
        //private Node nextNodeBack;
        /**
         * Sets all three fields to null value.
         */
        private Node() {
            nodeItem = null;
            //nextNodeFront = null;
            //nextNodeBack = null;
        }
    };

    /**
     * An inner class.
     * contains the methods needed for the Iterable interface.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * create a local copy of the first node in the linked list.
         */
        //private Node current = front;

        /**
         * @return true if the iteration has more elements.
         */
        public boolean hasNext() {
            return  false;
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
            Item item = arr[0];
            //TODO
            if ( false) {
                String msg = "";
                throw new NoSuchElementException(msg);
            }
            return item;
        }
    };
}
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 *  Implements a double ended queue using linked lists.
 * @author Michael Thornton
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    /**
     * front is the first node in the linked list
     * if the deque is empty, front is null valued.
     * if there is only one node, front and back are the same.
     */
    private Node front;
    /**
     * back is the last node in the linked list.
     * if the deque is empty, back is null valued.
     * if there is only one node, front and back are the same.
     */
    private Node back;

    /**
     * Construct an empty deque.
     */
    public Deque() {
        front = null;
        back = null;
    }

    /**
     * is the deque empty?
     * @return true if empty
     */
    public boolean isEmpty() {
        if (front == null) {
            return true;
        }
        return false;
    }

    /**
     * @return the number of items on the deque.
     */
    @SuppressWarnings("unused")
    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            int count = 0;
            for (Item i : this) {
                count++;
            }
            return count;
        }
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
        Node oldFront = front;
        front = new Node();
        front.nodeItem = item;
        front.nextNodeBack = oldFront;
        if (oldFront != null) { // if the deque was NOT empty
            oldFront.nextNodeFront = front;
        }
        if (oldFront == null) { // if the deque WAS empty
            back = front;
        }
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
        Node oldBack = back;
        back = new Node();
        back.nodeItem = item;
        back.nextNodeFront = oldBack;
        if (oldBack != null) { // if the deque was NOT empty.
            oldBack.nextNodeBack = back;
        }
        if (oldBack == null) { // if the deque WAS empty
            front = back;
        }
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
        Item item = front.nodeItem;
        // if this node IS the only one in the queue
        if (front.nextNodeBack == null && front.nextNodeFront == null) {
            front = null;
            back = null;
        } else {
            front.nextNodeBack.nextNodeFront = null;
            front = front.nextNodeBack;
        }
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
        Item item = back.nodeItem;
     // if this node IS the only one in the queue
        if (front.nextNodeBack == null && front.nextNodeFront == null) {
                back = null;
                front = null;
        } else {
            back.nextNodeFront.nextNodeBack = null;
            back = back.nextNodeFront;
        }
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
        private Node nextNodeFront;
        /**
         * Reference to the next node.
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
     * An inner class.
     * contains the methods needed for the Iterable interface.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * create a local copy of the first node in the linked list.
         */
        private Node current = front;

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
            //TODO
            if (item == null || current == null) { //did this fix the error?
                String msg = "";
                throw new NoSuchElementException(msg);
            }
            current = current.nextNodeBack;
            return item;
        }
    };
}
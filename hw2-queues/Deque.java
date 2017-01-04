//import java.lang.NullPointerException;
//import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *  Implements a double ended queue an array.
 * @author Michael Thornton
 *
 * @param <Item> A place holder the clients type or object being stored.
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * the initial and the smallest array size.
     */
    private static final int START_SIZE = 8;

    /**
     * Reduce the queue by this factor.
     * It is not equal to 2, in order to avoid thrashing
     */
    private static final int SHRINK_FACTOR = 4;

    /**
     * arr is the array holding queued items.
     * arr is sized in the constructor.
     */
    private Item[] arr;

    /**
     * front is the index of the first item in the queue.
     */
    private int front;

    /**
     * back is the index of the last item in the queue.
     */
    private int back;

    /**
     * Capacity is the size of the array.
     * This is a dynamic value since the array can be resized on demand.
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
        arr = (Item[]) new Object[START_SIZE];
        capacity = START_SIZE;
        // with one element in the queue front=back
        // with an empty queue front>back
        front = capacity / 2 + 1;
        back = capacity / 2;
        queueLength = 0;
    }

    /**
     * is the deque empty? .
     * Return true if empty
     */
    public boolean isEmpty() {
        if (queueLength == 0) {
            return true;
        }
        return false;
    }

    /**
     * Return the number of items on the deque.
     */
    public int size() {
        return queueLength;
    }

    /**
     * @param item , insert the item at the front.
     */
    public void addFirst(Item item) {
        if (item == null) {
            String msg = "'null value' argument in addFirst.";
            throw new NullPointerException(msg);
        }
        if (front == 0) {
            resizeOrCenter();
        }
        arr[--front] = item;
        queueLength++;
    }

    /**
     * 
     * @param item , insert the item at the end.
     */
    public void addLast(Item item)  {
        if (item == null) {
            String msg = "'null value' argument in addLast.";
            throw new NullPointerException(msg);
        }
        if (back == capacity - 1) {
            resizeOrCenter();
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
        final Item item = arr[front];
        arr[front] =  null; // free the memory for removed item
        front++;
        queueLength--;
        checkForShrinkSize();
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
        final Item item = arr[back];
        arr[back] = null; // free the memory for removed item
        back--;
        queueLength--;
        checkForShrinkSize();
        return item;
    }

    /**
     * return an iterator over items in order from front to back.
     * @return ArrayIterator() the iterator method.
     */
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    /**
     * HELPER METHODS.
     */

    /**
     * Determine if the queue meets the shrink criteria.
     * only after removing an item.
     */
    private void checkForShrinkSize() {
        if (isShrinkSize()) {
            shrinkAndCenter();
        }
    }
    
    /**
     * resizeOrCenter() is used when either the first or the last  array
     * element is used. The size of the queue is NOT the trigger, so it
     * checks the need for shrinking or growing or simply re-centering.
     */
    private void resizeOrCenter() {
        if (isShrinkSize()) {
            shrinkAndCenter();
        } else if (queueLength > capacity / 2) {
            growAndCenter();
        } else {
            center(capacity);
        }
    }

    /**
     *  Reduce the capacity and call the center method.
     */
    private void shrinkAndCenter() {
        center(capacity / 2);
    }

    /**
     *  Increase the capacity and call the center method.
     */
    private void growAndCenter() {
        center(capacity * 2);
    }

    /**
     * Creates a new array of size indicated by the argument.
     * Copies and centers the existing queue in the new array.
     * Changes variables and pointers to appropriate new values.
     * @param newArraySize is the capacity of the new array.
     */
    private void center(final int newArraySize) {
        @SuppressWarnings("unchecked")
        Item[] newArray = (Item[]) new Object[newArraySize];
        int  newFront = (newArraySize / 2) - (queueLength / 2);
        for (int i = 0; i < queueLength; i++) {
            newArray[newFront + i]  = arr[front + i];
        }
        front = newFront;
        back = newFront + queueLength - 1;
        // reassign pointer
        arr = newArray;
        capacity = newArraySize;
    }

    /**
     * @return true if the array and queue meet the shrink criteria.
     */
    private boolean isShrinkSize() {
        if ((queueLength > START_SIZE)
                && (queueLength <= capacity / SHRINK_FACTOR)) {
            return true;
        }
        return false;
    }

    /**
     *  INNER CLASSES.
     */

    /**
     * An inner class with methods for the Iterable interface.
     */
    private class ArrayIterator implements Iterator<Item> {

        /**
         * create an index for the iterator.
         */
        private int index = front;

        /**
         * @return true if the iteration has more elements.
         */
        public boolean hasNext() {
            if (queueLength > 0 && index <= back) {
                return true;
            }
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
            Item item = arr[index];
            if (item == null) {
                String msg = "array[" + String.valueOf(index)
                        + "] does not exist.";
                throw new NoSuchElementException(msg);
            }
            index++;
            return item;
        }
    }
}
//import java.lang.NullPointerException;
//import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Implements a Randomized queue.
 * Items are stored in the usual enqueue fashion,
 * but are removed or sampled in a uniform random manner.
 * @author Michael Thornton
 *
 * @param <Item> A place holder for the type or object being stored.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

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
     * queueLength is the number of items on the queue.
     */
    private int queueLength;

    /**
     * Construct an empty deque.
     */
    @SuppressWarnings("unchecked")
	public RandomizedQueue() {
        arr = (Item[]) new Object[START_SIZE];
        capacity = START_SIZE;
        // with one element in the queue front=back
        // with an empty queue front>back
        front = capacity / 2 + 1;
        back = capacity / 2;
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
     *  insert the item at the end.
     * @param item
     */
    public void enqueue(final Item item)  {
        if (item == null) {
            String msg = "'null value' argument in enqueue method.";
            throw new NullPointerException(msg);
        }
        if (back == capacity - 1) {
            resizeOrCenter();
        }
        arr[++back] = item;
        queueLength++;
    }


    /**
     * delete and return a random item.
     * @return a random item from the queue
     */
    public Item dequeue() {
        if (isEmpty()) {
            String msg = "in dequeue method, accessing empty queue !";
            throw new NoSuchElementException(msg);
        }
        //StdRandom.setSeed(1);
        int index = front + StdRandom.uniform(queueLength);
        Item item = arr[index];
        shift(index); // removes element and adjusts variables
        checkForShrinkSize();
        return item;
    }

    /**
     * return (but do not delete) a random item.
     * @return a random item from the queue
     */
    public Item sample() {
        if (isEmpty()) {
            String msg = "In sample method, accessing empty queue !";
            throw new NoSuchElementException(msg);
        }
        Item item = arr[front + StdRandom.uniform(queueLength)];
        return item;
    }

    /**
     * return an iterator over items in order from front to back.
     * @return ArrayIterator() the iterator method.
     */
    public Iterator<Item> iterator() {
        return new RandomIterator();
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
     * Creates a new array of size indicated by the argument.
     * Copies and centers the existing queue in the new array.
     * Changes variables and pointers to appropriate new values.
     * @param removedIndexNumber is the capacity of the new array.
     */
    private void shift(final int removedIndexNumber) {
        int index = removedIndexNumber;
        // if the queue only had one element,
        // it was removed, so don't shift anything
        if (queueLength == 1) {
            arr[index] = null;
            front = capacity / 2 + 1;
            back = capacity / 2;
            queueLength = 0;
            return;
        }
        // shift the smaller array segment
        if (index - front <= back - index) {
            while (index != front) {
                arr[index] = arr[index - 1];
                index--;
            }
            arr[front] = null; // free unused memory
            front++;
        } else {
            while (index != back) {
                arr[index] = arr[index + 1];
                index++;
            }
            arr[back] = null; // free unused memory
            back--;
        }
        queueLength -= 1;
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
    private class RandomIterator implements Iterator<Item> {

        /**
         * create an index for the iterator.
         */
        private int localIndex;
        /**
         * create an index for the queue, to be randomized.
         */
        private int[] randomIndex;

        private RandomIterator() {
            randomIndex =  new int[queueLength];
            for (int i = 0; i < queueLength; i++) {
                randomIndex[i] = i;
            }
            StdRandom.shuffle(randomIndex);
         }
        /**
         * @return true if the iteration has more elements.
         */
        public boolean hasNext() {
            if (localIndex < queueLength) {
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
            if (!hasNext()) {
                String msg = "No next() element";
                throw new NoSuchElementException(msg);
            }
            Item item = arr[front + randomIndex[localIndex]];
            if (item == null) {
                String msg = "array[" + String.valueOf(randomIndex[localIndex])
                        + "] does not exist.";
                throw new NoSuchElementException(msg);
            }
            localIndex++;
            return item;
        }
    };
}

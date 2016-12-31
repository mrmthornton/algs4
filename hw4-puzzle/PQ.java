import edu.princeton.cs.algs4.StdOut;

/**
 * @author mike
 */
public final class PQ<Key extends Comparable<Key>> {
    private int N = 0;
    private final Key[] pq;

    @SuppressWarnings("unchecked")
    PQ(int capacity) {
        pq = (Key[]) new Comparable[capacity+1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1,N--);
        sink(1);
        pq[N+1] = null; // prevent loitering
        return max;
    }

    private void swim(int k) {
        while ( k>1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while ( 2*k <= N) {
            int j = 2*k;
            if ( j< N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k =j;
        }
    }

    /**
     * helper functions
     */
    private boolean less( int i, int j) {
        return pq[i].compareTo(pq[j]) < 1;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        PQ<String> pQ = new PQ<String>(28);
        pQ.insert("A");
        pQ.insert("B");
        pQ.insert("C");
        pQ.insert("D");
        pQ.insert("E");
        pQ.insert("F");
        pQ.insert("G");
        pQ.insert("H");
        pQ.insert("I");
        pQ.insert("J");
        pQ.insert("K");
        pQ.insert("L");
        pQ.insert("M");
        pQ.insert("N");
        pQ.insert("O");
        StdOut.print(pQ.delMax());
        StdOut.print(pQ.delMax());
        StdOut.print(pQ.delMax());
        StdOut.print(pQ.delMax());
        StdOut.print(pQ.delMax());
        StdOut.print(pQ.delMax());
        StdOut.print(pQ.delMax());
        StdOut.print(pQ.delMax());
    }

}

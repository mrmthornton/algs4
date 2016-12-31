import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();

        int k = Integer.parseInt(args[0]);
        //System.out.println(k);

        while (!StdIn.isEmpty()) {
            rQ.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rQ.dequeue());
        }
    }
}

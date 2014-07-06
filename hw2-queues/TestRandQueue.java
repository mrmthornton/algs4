
/**
 * Test class RandomizedQueue.
 * @author mike
 */
public final class TestRandQueue {
    /**
     * does nothing.
     */
    private TestRandQueue() {   }

    /**
     * exercises the RandomizedQueue including exception conditions.
     * @param args None expected
     */
    public static void main(final String[] args) {
        RandomizedQueue<String>rDek = new RandomizedQueue<String>();
        if (rDek.isEmpty()) {
            System.out.println("Queue is empty.");
        }

        System.out.println("size() returns " + rDek.size());

//        rDek.enqueue(null);  // test exception in enqueue method.
//        rDek.iterator().remove();  // test exception in iterator remove() method.
//        rDek.iterator().next();  // test exception in iterator next() method. 

        rDek.enqueue("\t(tabs)\t");
        rDek.enqueue("I");
        rDek.enqueue("am");
        rDek.enqueue("speaking");
        rDek.enqueue("like");
        rDek.enqueue("Yoda");

        System.out.println("size() returns " + rDek.size());

        rDek.enqueue("I");
        rDek.enqueue("am");
        rDek.enqueue("the");
        rDek.enqueue("man");
        rDek.enqueue("from");
        rDek.enqueue("Degoba");

        System.out.println("size() returns " + rDek.size());

        for (String s : rDek) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (int i = 0; i < 12; i++) {
            System.out.print(rDek.dequeue() + " ");
        }
        System.out.println();

        rDek.enqueue("one");
        rDek.enqueue("two");
        rDek.enqueue("three");
        rDek.enqueue("four");
        rDek.enqueue("five");

        // testing sample of empty queue
        // uncomment next line for testing
        //for (int i = 0; i < 6; i++)
        {
            System.out.print ("testing sample() exception ");
            System.out.println(rDek.sample() + " ");
        }

        System.out.println("size() returns " + rDek.size());

        // iterate over queue
        for (String s : rDek) {
            System.out.print(s + " ");
        }
        // starting clean with new  Randomized Queue
        RandomizedQueue<String> rDek2 = new  RandomizedQueue<String>();
        for (int i = 0; i<500; i++) {
            rDek2.enqueue("A");
        }
        for (int i = 0; i<500; i++) {
            rDek2.sample();
        }
        for (int i = 0; i<500; i++) {
            rDek2.dequeue();
        }
        System.out.println();
        System.out.println("this should fail");
        rDek2.sample();
        System.out.println("size() returns " + rDek2.size());

    }
}

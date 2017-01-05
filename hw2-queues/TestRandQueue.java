
/**
 * Test class RandomizedQueue.
 * @author mike
 */
public final class TestRandQueue {
    RandomizedQueue<String> Rq;
    
    private TestRandQueue() {
        /**
         * exercises the RandomizedQueue including exception conditions.
         * @param args None expected
         */
        Rq = new RandomizedQueue<String>();
    }

    /**
     * No arguments are expected.
     */
    public static void main(final String[] args) {
        TestRandQueue Trq = new TestRandQueue();

        // CHECKING EXCEPTIONS
        //Trq.Rq.iterator().next();        // test exception in iterator next(), no next. 
        //Trq.Rq.iterator().remove();   // test exception in iterator remove().
        //Trq.Rq.enqueue(null);           // test exception in enqueue method.
        //Trq.Rq.dequeue();                 // test exception in dequeue method.

        // CHECKING METHODS
        // enqueue
        Trq.Rq.enqueue("enqueue");
        if (Trq.Rq.size() != 1) {
            System.out.println("Failed : size should be 1.");
        }
        // dequeue
        if (Trq.Rq.dequeue() != "enqueue") {
            System.out.println("Failed: values do not match");
        }
        // isEmpty
        Trq = new TestRandQueue();
        if (!Trq.Rq.isEmpty()) {
            System.out.println("Failed : queue should be empty.");
        }
        // iterator
        Trq.Rq.enqueue("a");
        Trq.Rq.enqueue("b");
        Trq.Rq.enqueue("c");
        for (String s : Trq.Rq) {
            System.out.print(s);
        }
        Trq.Rq.iterator();
        ///Trq.Rq.sample();
        //Trq.Rq.size();
        
        System.out.println("size() returns " + Trq.Rq.size());



        Trq.Rq.enqueue("\t<--tabs-->\t");
        Trq.Rq.enqueue("I");
        Trq.Rq.enqueue("am");
        Trq.Rq.enqueue("speaking");
        Trq.Rq.enqueue("like");
        Trq.Rq.enqueue("Yoda");

        System.out.println("size() returns " + Trq.Rq.size());

        Trq.Rq.enqueue("I");
        Trq.Rq.enqueue("am");
        Trq.Rq.enqueue("the");
        Trq.Rq.enqueue("man");
        Trq.Rq.enqueue("from");
        Trq.Rq.enqueue("Degoba");

        System.out.println("size() returns " + Trq.Rq.size());

        for (String s : Trq.Rq) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (int i = 0; i < 12; i++) {
            System.out.print(Trq.Rq.dequeue() + " ");
        }
        System.out.println();

        Trq.Rq.enqueue("one");
        Trq.Rq.enqueue("two");
        Trq.Rq.enqueue("three");
        Trq.Rq.enqueue("four");
        Trq.Rq.enqueue("five");

        // testing sample of empty queue
        // uncomment next line for testing
        //for (int i = 0; i < 6; i++)
        {
            System.out.print("testing sample() exception ");
            System.out.println(Trq.Rq.sample() + " ");
        }

        System.out.println("size() returns " + Trq.Rq.size());

        // iterate over queue
        for (String s : Trq.Rq) {
            System.out.print(s + " ");
        }
        // starting clean with new  Randomized Queue
        Trq.Rq = new  RandomizedQueue<String>();
        for (int i = 0; i < 500; i++) {
            Trq.Rq.enqueue("A");
        }
        for (int i = 0; i < 500; i++) {
            Trq.Rq.sample();
        }
        for (int i = 0; i < 500; i++) {
            Trq.Rq.dequeue();
        }
        System.out.println();
        //System.out.println("this should fail");
        //Trq.Rq2.sample();
        System.out.println("size() returns " + Trq.Rq.size());

    }
}

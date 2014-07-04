




public class TestDeque {

    public static void main(String[] args) {
        Deque<String>dek = new Deque<String>();

        System.out.println("size() returns " + dek.size());

        //dek.addFirst(null);  // test exception in addFirst method.
        //dek.addLast(null);  // test exception in addLast method.
        //dek.iterator().remove(); // test exception in iterator remove method.

        // add N items, remove N+1 expecting exception
        dek.addFirst("one");
        dek.addFirst("two");
        dek.addFirst("three");
        System.out.println("size() returns " + dek.size());
        dek.removeFirst();
        dek.removeFirst();
        dek.removeFirst();
        //dek.removeFirst(); // uncomment for testing exception
        for (String s : dek) {
            System.out.println("iterator finds : " + s);
        }
        System.out.println("size() returns " + dek.size());

        dek.addLast("10");
        dek.addLast("9");
        dek.addLast("8");
        dek.removeLast();
        dek.removeLast();
        dek.removeLast();
        //dek.removeLast();
        for (String s : dek) {
            System.out.println("iterator finds : " + s);
        }
        System.out.println("size() returns " + dek.size());


        dek.addFirst("\t\t");
        dek.addFirst("I");
        dek.addFirst("am");
        dek.addFirst("speaking");
        dek.addFirst("like");
        dek.addFirst("Yoda");
        for (String s : dek) {
            System.out.println(s);
        }
        for (int i = 0; i < 6; i++) {
            dek.removeFirst();
        }

        System.out.println("size() returns " + dek.size());

        dek.addLast("I");
        dek.addLast("am");
        dek.addLast("the");
        dek.addLast("man");
        dek.addLast("from");
        dek.addLast("Degoba");

        System.out.println("size() returns " + dek.size());

        for ( String s : dek) {
            System.out.print(s + " ");
            }
        System.out.println();

        dek.removeFirst();
        dek.removeFirst();
        dek.removeFirst();

        dek.removeLast();
        dek.removeLast();
        dek.removeLast();

        System.out.println("size() returns " + dek.size());

        for ( String s : dek) {
            if (dek.iterator().hasNext()) {
                System.out.print(s + " ");
            }
        }
        System.out.println();
    }
}

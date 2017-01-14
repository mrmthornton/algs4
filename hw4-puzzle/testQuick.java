import edu.princeton.cs.algs4.Quick;

public class testQuick {
    public static void main(String[] args) {

        String [] a = {"a", "b","a", "b","a", "a","a", "a", "b","b", "b"};
        Quick.sort(a);
        for (String s : a) {
            System.out.print(s);
        }
        System.out.println();
        String s = "hi";
        System.out.println(s);
        s = "new";
        System.out.println(s);
    }
}

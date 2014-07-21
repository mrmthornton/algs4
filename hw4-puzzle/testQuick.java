
public class testQuick {
    public static void main(String[] args) {
        Quick q = new Quick();
        String [] a = {"a", "b","a", "b","a", "a","a", "a", "b","b", "b"};
        q.sort(a);
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

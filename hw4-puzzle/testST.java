import edu.princeton.cs.algs4.ST;

public class testST {
    public static void main(String[] args)
    {
        ST<String, Integer> st = new ST<String, Integer>();
        for (int i =0; i < args.length; i++)
        {
            String key = args[i];
            st.put(key, i);
        }
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}

import edu.princeton.cs.algs4.StdOut;

public class temp {
    private static final double pos = Double.POSITIVE_INFINITY;
    private static final double neg = Double.NEGATIVE_INFINITY;
    
    public static void main(String[] args) {

        if (neg < pos) 
            StdOut.println("less"); 
        if (pos == pos) 
            StdOut.println("equal");
        if (pos > neg) 
            StdOut.println("greater");
    }
}

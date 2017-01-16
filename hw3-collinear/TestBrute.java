
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class TestBrute {

    public static void main(String[] args) {
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point [] arr = new Point[N];
    
        for (int i = 0; i < N; i++) {
            arr[i] = new Point(in.readInt(), in.readInt());
            arr[i].draw();
        }
        in.close();
        
        final int fieldSize = 32768;
        StdDraw.setXscale(0, fieldSize);
        StdDraw.setYscale(0, fieldSize);
        StdDraw.show();
        
        Arrays.sort(arr);
        
        Stopwatch timer = new Stopwatch();  // start timer
    
        BruteCollinearPoints brute = new BruteCollinearPoints(arr);
                
        // stop and print timer
        StdOut.println(timer.elapsedTime());
        
        // print number of segments found
        StdOut.println(brute.numberOfSegments());
        
        // loop over all segments
        for (LineSegment l: brute.segments()) {
            if (l == null) { break; }
            // print the line endpoints
            StdOut.println(l.toString());
            // draw all the line segments
            l.draw();
            StdDraw.show();
        }
    
    }
}

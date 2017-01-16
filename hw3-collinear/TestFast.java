
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class TestFast {

    
    /**
     * The input file first line is the number of points.
     * The subsequent lines contain coordinates. For example 512 256
     * @param args . The input file.
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        
        final int fieldSize = 32768;

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();  // get the number of points
        
        // create the canvas
        StdDraw.setXscale(0, fieldSize);
        StdDraw.setYscale(0, fieldSize);
        StdDraw.show(0);

        // create arrays for point storage and manipulation
        Point [] pointsFromInput = new Point[N];  // all the points
        
        //fill the array with unsorted input
        for (int idx = 0; idx < N; idx++) {
            pointsFromInput[idx] = new Point(in.readInt(), in.readInt());
        }
        
        // draw the points on the canvas
        for (Point p : pointsFromInput) {
            p.draw();
        }
        
        // display the updated canvas
        StdDraw.show(0);
        
        // start a timer
        Stopwatch timer = new Stopwatch();  // start timer
        
        // instanciate the fast class line finder 
        FastCollinearPoints fast = new FastCollinearPoints(pointsFromInput);   
        
        // stop and print timer
        StdOut.println(timer.elapsedTime());
        
        // print the number of line segments 
        StdOut.println(fast.numberOfSegments());
        
        // loop over all segments
        for (LineSegment l: fast.segments()) {
            if (l == null) { break; }
            // print the line endpoints
            StdOut.println(l.toString());
            // draw all the line segments
            l.draw();
            StdDraw.show(0);
        }
        StdOut.println();
        in.close();
    }
}
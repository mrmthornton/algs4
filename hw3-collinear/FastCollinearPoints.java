
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class FastCollinearPoints {
    private static final int POINTS_IN_LINE = 4;

    private Point [][] lines;
    private int numberOfSegments;
    
    public FastCollinearPoints() { }
    //public FastCollinearPoints(Point [], points) { }
    
    private FastCollinearPoints(int N) {
        lines = new Point[N * (N - 1) / POINTS_IN_LINE][2];
        numberOfSegments = 0;
    }
        
    private boolean isUnique(Point[] newLine) {
        if (numberOfSegments == 0 || isNotDuplicate(newLine)) {
            saveLine(newLine);
            numberOfSegments++;
            return true;
        }
        return false;
    }

    private void saveLine(Point[] newLine) {
        for (int i = 0; i <= 1; i++) {
            lines[numberOfSegments][i] = newLine[i];
        }
    }
        
    private boolean isNotDuplicate(Point[] newLine) {
        // for each line in lines compare first two points against 
        // the first two points in aNewLine.
        for (int i = 0; i < numberOfSegments; i++) {
            if (lines[i][0] == newLine[0] && lines[i][1] == newLine[1]) {
                return false;
            }
        }
        return true;
    }

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
        Point [] pointsInOrder = new Point[N];  // all the points
        Point [] arr = new Point[N]; // sorted by slope
        Point [] line = new Point[N]; // lines found
        
        /**
         * this will not work internally. ?
         */
        FastCollinearPoints lines = new FastCollinearPoints(N);
        
        //fill the array with unsorted input
        for (int idx = 0; idx < N; idx++) {
            pointsInOrder[idx] = new Point(in.readInt(), in.readInt());
        }
        // sort the input array
        Arrays.sort(pointsInOrder); // **************** does this accomplish anything ?
        
        // draw the points on the canvas
        for (Point p : pointsInOrder) {
            p.draw();
        }
        // display the updated canvas
        StdDraw.show(0);
        
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = pointsInOrder[idx];
        }

        // if there are enough input points to form a line
        if (N >= POINTS_IN_LINE) {
            // start a timer
            Stopwatch timer = new Stopwatch();  // start timer
            // loop over all points
            for (int i = 0; i < N; i++) {
                // on each pass, select a different point for the temporary origin
                Point origin = pointsInOrder[i];
                // sort the points by slope, with respect to the temporary origin
                Arrays.sort(arr, origin.SLOPE_ORDER); 
                // loop over all points (including the origin)
                int j = 0; // the index of all points
                int next = 0; // the index of same-slope points
                while (j < N) {
                    // store the temporary origin as the start of potential line
                    line[0] = origin; 
                    // the slope from the origin the next point(which may be the origin)
                    double slope = origin.slopeTo(arr[j]);  
                    // loop over points with same relative slope
                    while (j + next < N  // while there are still more points and 
                            && origin.slopeTo(arr[j + next]) == slope) {  // the slope is the same
                        line[next + 1] = arr[j + next]; // add the point to a potential line
                        next++;
                    }
                    // if the line contains enought points
                    if (next >= POINTS_IN_LINE - 1) {
                        // sort before comparing to known lines
                        Arrays.sort(line, 0, next + 1);
                        // if the line is unique
                        if (lines.isUnique(line)) {
                            //print all the points in the line
                            for (int n = 0; n < next; n++) { // print all points
                                StdOut.print(line[n].toString() + " -> ");
                            }
                            StdOut.println(line[next].toString() );
                            // draw the line
                            line[0].drawTo(line[next]);
                            StdDraw.show(0);
                        }
                        j = j + next;
                    } else {
                        j++;
                    }
                    next = 0;
                }
            }
            StdOut.println(timer.elapsedTime()); // stop and print timer
        }
    }
}


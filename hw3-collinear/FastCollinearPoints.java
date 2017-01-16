
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class FastCollinearPoints {
    private static final int POINTS_IN_LINE = 4;

    private int N;
    private Point [] pointsInOrder;  // all the points, sorted
    private Point [] arr; // sorted by slope
    private Point [] line; // holds Points as the line is being formed
    private Point [][] lines; // the found lines(points)
    private LineSegment[] lineSegments; // the found lines(segments)
    private int numberOfSegments;
    
    public FastCollinearPoints(Point [] points) { 
        N = points.length; 
        lines = new Point[N * (N - 1) / POINTS_IN_LINE][2];
        lineSegments = new LineSegment[N * (N - 1) / POINTS_IN_LINE];
        numberOfSegments = 0;
        
        // create arrays for point storage and manipulation
        pointsInOrder = new Point[N];  // all the points
        arr = new Point[N]; // sorted by slope
        line = new Point[N]; // lines found

        // make a copy of the input array
        pointsInOrder = Arrays.copyOf(points, N);
        // sort the local copy
        Arrays.sort(pointsInOrder); // **************** does this accomplish anything ?
        
        // make a copy of the points which will be repeatedly sorted by slope
        arr = Arrays.copyOf(pointsInOrder, N);

        // if there are enough input points to form a line
        if (N >= POINTS_IN_LINE) {
            // loop over all points
            for (int i = 0; i < N; i++) {
                // on each pass, select a different point for the temporary origin
                Point origin = pointsInOrder[i];
                // sort the points by slope, with respect to the temporary origin
                //Arrays.sort(arr, origin.SLOPE_ORDER); 
                Arrays.sort(arr, origin.slopeOrder()); 
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
                        if (isUnique(line[0], line[next])) {
                            // add the new line to lines and lineSegments
                            saveLine(line[0], line[next]);
                            // increment the number of found line segments
                            numberOfSegments++;
                            //print all the points in the line
                            //StdOut.println(line[0].toString() + " -> " + line[next].toString());
                        }
                        // skip any points included in the line and continue the loop
                        j = j + next;
                    } else {
                        // increment the point index and continue the loop
                        j++;
                    }
                    next = 0;
                }
            }
        }
    }

    public int numberOfSegments() {
        return lines.length;
    }
    
    public LineSegment [] segments() {
        return lineSegments;
    }
    
    // if a line is found to be unique, it is added to the array lines
    private boolean isUnique(Point start, Point end) {
        if (numberOfSegments == 0 || !isDuplicate(start, end)) {
            return true;
        }
        return false;
    }

    private void saveLine(Point start, Point end) {
        lines[numberOfSegments][0] = start;
        lines[numberOfSegments][1] = end;
        lineSegments[numberOfSegments] = new LineSegment(start, end);
    }
        
    private boolean isDuplicate(Point start, Point end) {
        // for each line in lines compare against newline
        for (int i = 0; i < numberOfSegments; i++) {
            if (lines[i][0] == start && lines[i][1] == end) {
                return true;
            }
        }
        return false;
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
        StdOut.println(fast.numberOfSegments);
        
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
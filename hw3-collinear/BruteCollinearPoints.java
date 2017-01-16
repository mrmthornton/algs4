
import java.util.Arrays; 

public class BruteCollinearPoints {
    private static final int POINTS_IN_LINE = 4;

    /**
     * Constant negative infinity.
     */
    private static final double NEG_INF = Double.NEGATIVE_INFINITY;
    
    private int N;
    private Point [] arr; // sorted by slope
    private Point [] line; // holds Points as the line is being formed
    private LineSegment[] lineSegments; // the found lines(segments)
    private int numberOfSegments;
    
 
    /**
     * Find all line segments containing 4 or more points.
     * @param points . An array containing type Point .
     */
    public BruteCollinearPoints(Point[] points) {
        N = points.length; 
        arr = new Point[N];
        line = new Point[POINTS_IN_LINE];
        lineSegments = new LineSegment[N * (N - 1) / POINTS_IN_LINE];
        numberOfSegments = 0;

        // make a copy of the input array and sort it.
        arr = Arrays.copyOf(points, N); 
        //for (int i = 0; i < N; i++) {
        //    arr[i] = points[i];
        //}
        Arrays.sort(arr); // **************** does this accomplish anything ?
        // for(Point p : arr) {               // for debug
        //     StdOut.println(p.toString());  // for debug
        // }                                  // for debug
        
        // find all of the lines, with collinear points numbering at least "POINTS_IN_LINE". 
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int m = k + 1; m < N; m++) {
                        double s1 = arr[i].slopeTo(arr[j]);
                        double s2 = arr[i].slopeTo(arr[k]);
                        double s3 = arr[i].slopeTo(arr[m]);
                        if (s1 == s2 && s1 == s3 && s1 != NEG_INF) {
                            line[0] = arr[i];
                            line[1] = arr[j];
                            line[2] = arr[k];
                            line[3] = arr[m];
                            lineSegments[numberOfSegments] = new LineSegment(line[0], line[3]);
                            numberOfSegments++;
                            // Arrays.sort(line); // for debug only
                            // StdOut.print(line[0].toString() + " -> "); // for debug only
                            // StdOut.print(line[1].toString() + " -> "); // for debug only
                            // StdOut.print(line[2].toString() + " -> "); // for debug only
                            // StdOut.println(line[3].toString()); // for debug only
                            // line[0].drawTo(line[3]); // for debug only
                            // StdDraw.show(0); // for debug only
                        }
                    }
                }
            }
        }
    }

    /**
     * The number of line segments found in the input.
     * returns the number of line segments found.
     */
    public int numberOfSegments() {
        return numberOfSegments;
    }
    
    /**
     * The array of line segments, of type LineSegment .
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, numberOfSegments);
    }
}

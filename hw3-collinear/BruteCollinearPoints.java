
import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private static final int POINTS_IN_LINE = 4;

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
        Arrays.sort(arr);
        for(Point p : arr) {
            StdOut.println(p.toString());
        }
        
        // find all of the lines, with collinear points numbering at least "POINTS_IN_LINE". 
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int m = k + 1; m < N; m++) {
                        try {
                            double s1 = arr[i].slopeTo(arr[j]);
                            double s2 = arr[i].slopeTo(arr[k]);
                            double s3 = arr[i].slopeTo(arr[m]);
                            if (s1 == s2 && s1 == s3) {
                                line[0] = arr[i];
                                line[1] = arr[j];
                                line[2] = arr[k];
                                line[3] = arr[m];
                                lineSegments[numberOfSegments] = new LineSegment(line[0], line[3]);
                                numberOfSegments++;
                                //Arrays.sort(line); // for debug only
                                //StdOut.print(line[0].toString() + " -> "); // for debug only
                                //StdOut.print(line[1].toString() + " -> "); // for debug only
                                //StdOut.print(line[2].toString() + " -> "); // for debug only
                                //StdOut.println(line[3].toString()); // for debug only
                                //line[0].drawTo(line[3]); // for debug only
                                //StdDraw.show(0); // for debug only
                            }
                        } catch(NullPointerException ex) {
                            StdOut.print(ex.toString());
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
        return lineSegments;
    }
}

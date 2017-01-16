
import java.util.Arrays; 

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
        //for (int i = 0; i < N; i++) {
        //    arr[i] = points[i];
        //}
        Arrays.sort(arr); 
        
        // check for duplicate points
        for (int i=0; i<N-1; i++) {
            Point p = arr[i];
            Point q = arr[i+1];
            if (p.compareTo(q)==0) {
                throw new IllegalArgumentException(); // found the same point twice
            }
        }
        
        // find all of the lines, with collinear points numbering at least "POINTS_IN_LINE". 
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int m = k + 1; m < N; m++) {
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

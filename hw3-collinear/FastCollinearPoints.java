
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
        Arrays.sort(pointsInOrder);
        
        // check for duplicate points
        for (int i=0; i<N-1; i++) {
            Point p = pointsInOrder[i];
            Point q = pointsInOrder[i+1];
            if (p.compareTo(q)==0) {
                throw new IllegalArgumentException(); // found the same point twice
            }
        }
                
        // make a copy of the points which will be repeatedly sorted by slope
        arr = Arrays.copyOf(pointsInOrder, N);

        // if there are enough input points to form a line
        if (N >= POINTS_IN_LINE) {
            // LOOP OVER ALL POINTS in sorted by Point
            for (int i = 0; i < N; i++) {
                // on each pass, select a different point for the temporary origin
                Point origin = pointsInOrder[i];
                // sort the points by slope, with respect to the temporary origin
                // Arrays.sort(arr, origin.SLOPE_ORDER); 
                Arrays.sort(arr, origin.slopeOrder()); 
                
                // LOOP OVER ALL POINTS in sorted by slope (including the origin)
                int j = 0; // the index of all points
                int next = 0; // the index of same-slope points
                while (j < N) {
                    // store the temporary origin as the start of potential line
                    line[0] = origin; 
                    // the slope from the origin the next point(which may be the origin)
                    double slope = origin.slopeTo(arr[j]);
                                        
                    // while there is still another point with the same slope,
                    // add the point to the potential line segment,
                    // and increment the same-slope index (next)
                    while (j + next < N && origin.slopeTo(arr[j + next]) == slope) { 
                        line[next + 1] = arr[j + next]; 
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
                            // print all the points in the line
                            // StdOut.println(line[0].toString() + " -> " + line[next].toString());
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
        return numberOfSegments;
    }
    
    public LineSegment [] segments() {
        return Arrays.copyOf(lineSegments, numberOfSegments);
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
            if (start.compareTo(lines[i][0])==0 && end.compareTo(lines[i][1]) == 0) {
                return true;
            }
        }
        return false;
    }
}
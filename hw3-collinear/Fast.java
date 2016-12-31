import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class Fast {
    private static final int POINTS_IN_LINE = 4;

    private Point [][] lines;
    private int lineIndex;
    
    public Fast() { }
    
    private Fast(int N) {
        lines = new Point[N * (N-1)/POINTS_IN_LINE][2];
        lineIndex = 0;
    }
        
    private boolean isUnique(Point[] newLine) {
        if (lineIndex == 0 || isNotDuplicate(newLine)) {
            saveLine(newLine);
            lineIndex++;
            return true;
        }
        return false;
    }

    private void saveLine(Point[] newLine) {
        for (int i = 0; i <= 1; i++) {
            lines[lineIndex][i] = newLine[i];
        }
    }
        
    private boolean isNotDuplicate(Point[] newLine) {
        // for each line in lines compare first two points against 
        // the first two points in aNewLine.
        for (int i = 0; i < lineIndex; i++) {
            if (lines[i][0] == newLine[0] && lines[i][1] == newLine[1]) 
                return false;
        }
        return true;
    }
 
    public static void main(String[] args) {
        
        final int fieldSize = 32768;
        StdDraw.setXscale(0, fieldSize);
        StdDraw.setYscale(0, fieldSize);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();  // get the number of points

        // create arrays for point storage and manipulation
        Point [] pointsInOrder = new Point[N];  // all the points
        Point [] arr = new Point[N]; // sorted by slope
        Point [] line = new Point[N]; // line found
        Fast lines = new Fast(N);
        
        for (int idx = 0; idx < N; idx++) {
            pointsInOrder[idx] = new Point(in.readInt(), in.readInt());
        }
        Arrays.sort(pointsInOrder);
        for (Point p : pointsInOrder) {
            p.draw();
        }
        StdDraw.show(0);
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = pointsInOrder[idx];
        }

        
        if (N >= POINTS_IN_LINE) {
            //Stopwatch timer = new Stopwatch();  // start timer
            for (int i = 0; i < N; i++) {
                Point origin = pointsInOrder[i];
                Arrays.sort(arr, origin.SLOPE_ORDER); // sort by slope
                int j = 0;
                int next = 0;
                while (j < N) {
                    line[0] = origin; // start of potential line
                    double slope = origin.slopeTo(arr[j]);  // slope from origin to next start
                    while (j + next < N  // still more points
                            && origin.slopeTo(arr[j + next]) == slope) {  // slopes are equal
                        line[next + 1] = arr[j + next]; // save index
                        next++;
                    }
                    if (next >= POINTS_IN_LINE -1) {
                        Arrays.sort(line, 0, next + 1);
                        if (lines.isUnique(line)) {
                            for (int n = 0; n < next; n++) { // print all points
                                StdOut.print(line[n].toString() + " -> ");
                            }
                            StdOut.println(line[next].toString());
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
            //StdOut.println(timer.elapsedTime()); // stop and print timer
        }
    }
}


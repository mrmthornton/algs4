

import java.util.Arrays;

public class Fast {
    private static final int POINTS_IN_LINE = 4;

    public static void main(String[] args) {
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        Point[] arr = new Point[N];
        Point[] lines = new Point[N];
        Point negOnes = new Point(-1, -2);

        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            arr[i] = p;
            p.draw();
        }
        StdDraw.show(0);

        //Stopwatch timer = new Stopwatch();  // start timer

        for (int i = 0; i < N - 1; i++) { // arr[i] , up to N - lineSeqments is the origin
            if ( N -  i  > POINTS_IN_LINE ) { // sort for two or more elements
                // order the points by slope, starting after the faux origin
                Arrays.sort( arr, i+1, N-1,  arr[i].SLOPE_ORDER);
            }
            for (int n = i+1; n < N - POINTS_IN_LINE; n++) {
                // if the slope from element 'i' to elements 'n', 'n+1', 'n+2' 
                // are the same, then a 4 point collinear has been found.
                if (arr[i].SLOPE_ORDER.compare(arr[n], arr[n+1]) == 0 &&
                        arr[i].SLOPE_ORDER.compare(arr[n], arr[n+2]) == 0) {
                    lines[0] = arr[i];
                    lines[1] = arr[n];
                    lines[2] = arr[n+1];
                    lines[3] = arr[n+2];
                    int idx = 3;
                    // check for additional collinear points beyond the initial 4.
                    while (arr[i].SLOPE_ORDER.compare(arr[n], arr[n+idx]) == 0) {
                        lines[idx+1] = arr[n+idx];
                        idx++;
                        assert lines[idx] != null;
                    }
                    Arrays.sort(lines, 0, idx+1, negOnes.SLOPE_ORDER);
                    lines[0].drawTo(lines[idx]);
                    StdDraw.show(0);
                    for (int r=0; r<idx; r++) {
                        StdOut.print(lines[r].toString() + " -> ");
                        lines[r] = null; 
                    }
                    StdOut.println(lines[idx].toString());
                }
            }
        }
        //StdOut.println(timer.elapsedTime()); // stop and print timer
    }
}


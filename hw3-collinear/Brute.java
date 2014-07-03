import java.util.Arrays;

public class Brute {

    public static void main(String[] args) {
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        Point[] arr = new Point[N];
        Point[] line = new Point[N];
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


        for (int i = 0; i < N - 3; i++) {
            for (int j = i+1; j < N - 2; j++) {
                for (int k = j+1; k < N - 1; k++) {
                    for (int m = k+1; m < N; m++) {
                        if(arr[i].SLOPE_ORDER.compare(arr[j], arr[k]) == 0 &&
                            arr[i].SLOPE_ORDER.compare(arr[k], arr[m]) == 0) {
                            line[0] = arr[i];
                            line[1] = arr[j];
                            line[2] = arr[k];
                            line[3] = arr[m];
                            Arrays.sort(line, 0, 4, negOnes.SLOPE_ORDER);
                            line[0].drawTo(line[1]);
                            StdOut.print(line[0].toString() + " -> ");
                            StdDraw.show(0);
                            line[1].drawTo(line[2]);
                            StdOut.print(line[1].toString() + " -> ");
                            StdDraw.show(0);
                            line[2].drawTo(line[3]);
                            StdOut.print(line[2].toString() + " -> ");
                            StdDraw.show(0);
                            StdOut.println(line[3].toString() );
                        }
                    }
                }
            }
        }
        //StdOut.println(timer.elapsedTime()); // stop and print timer
    }
}

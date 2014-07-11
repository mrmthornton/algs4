import java.util.Arrays;

public class Brute {
    private static final int POINTS_IN_LINE = 4;
    private Brute() { }

    public static void main(String[] args) {
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        final int fieldSize = 32768;
        StdDraw.setXscale(0, fieldSize);
        StdDraw.setYscale(0, fieldSize);
        StdDraw.show();

        Point [] arr = new Point[N];
        Point [] line = new Point[POINTS_IN_LINE];

        for (int i = 0; i < N; i++) {
            arr[i] = new Point(in.readInt(), in.readInt());
            arr[i].draw();
        }
        Arrays.sort(arr);
        StdDraw.show(0);

        //Stopwatch timer = new Stopwatch();  // start timer


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
                            Arrays.sort(line);
                            StdOut.print(line[0].toString() + " -> ");
                            StdOut.print(line[1].toString() + " -> ");
                            StdOut.print(line[2].toString() + " -> ");
                            StdOut.println(line[3].toString());
                            line[0].drawTo(line[3]);
                            StdDraw.show(0);
                        }
                    }
                }
            }
        }
        //StdOut.println(timer.elapsedTime()); // stop and print timer
        in.close();
    }
}

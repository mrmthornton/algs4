/**
 * Tests the class Percolation using a grid of N by N blocks.
 *  Competes T trials.
 *  Gathers and reports statistics.
 */
public class PercolationStats {
    /**
     *  N is the grid dimension.
     */
    private Integer N;
    /**
     * T is the number of trials to perform.
     */
    private Integer T;
    /**
     * Save the trial percolation statistics in pArray.
     */
    private double[] pArray;
    /**
     * The mean of percolation thresholds.
     */
    private double mu;
    /**
     * The standard deviation of percolation thresholds.
     */
    private double sigma;
    /**
     *  The Confidence Interval Boundary.
     */
    private static final double CIB = 1.96;
    /**
     * Perform T independent computational experiments on an N-by-N grid.
     * @param n The grid dimension
     * @param t The number of trials
     */
    public PercolationStats(final int n, final int t) {
        N = n;
        T = t;
        if (N <  1)  {
            throw new java.lang.IllegalArgumentException(
                    "'block size' argument N is zero or negative");
        }
        if (T <  1) {
            throw new java.lang.IllegalArgumentException(
                    "'trials' argument T is zero or negative");
        }
        pArray = new double[T];
        for (int i = 0; i < T; i++) {
            pArray[i] = 0;
        }
        runTest();
    }
    /**
     * Create the Percolation instance.
     * Fill random sites until percolation occurs.
     * Loot T times, saving information for each trial
     */
    private void runTest() {
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            // open a randomly chosen site, and test for percolation.
            while (!perc.percolates()) {
                while (true) {
                    int randInt1 = StdRandom.uniform(1, N + 1);
                    int randint2 = StdRandom.uniform(1, N + 1);
                    if (perc.isOpen(randInt1, randint2)) {
                        continue;
                    } else {
                        perc.open(randInt1, randint2);
                        break;
                    }
                }
            }

            // calculate P when percolation occurs.
            //P is the number of open sites divided by the total sites.
            int countOpen = 0;
            for (int col = 1; col <= N; col++) {
                for (int row = 1; row <= N; row++) {
                    if (perc.isOpen(row, col)) {
                        countOpen++;
                    }
                }
            }
            double pTrial = countOpen / (double) (N * N);
            pArray[i] = pTrial;
        }
    }
    /**
     * @return the sample mean of percolation threshold.
     */
    public double mean() {
        mu = StdStats.mean(pArray);
        return mu;
    }
    /**
     * @return the sample standard deviation of percolation threshold.
     */
    public double stddev() {
        sigma = StdStats.stddev(pArray);
        return sigma;
    }
    /**
     * @return the lower bound of the confidence interval
     */
    public double confidenceLo() { 
        // using mean() rather than mu, and stddev() rather than sigma
        // ensures the current correct values are used.
        double ciLo = mean() - (CIB * stddev() /  Math.sqrt(T));
        return ciLo;
    }
    /**
     * @return the upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        double ciHi = mean() + (CIB * stddev() /  Math.sqrt(T));
        return ciHi;
    }
    /**
     * @param args N and T.
     */
    public static void main(final String[] args) {
        //for (String arg : args) {System.out.println(" " + arg);}

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        Stopwatch timer = new Stopwatch();
        PercolationStats stats = new PercolationStats(N, T);
        double runTime = timer.elapsedTime();
        System.out.println(runTime);

        System.out.println("mean                                =  "
                + stats.mean());
        System.out.println("stddev                              =  "
                + stats.stddev());
        System.out.print("95% confidence interval  =  ");
        System.out.print(stats.confidenceLo() + ",   " + stats.confidenceHi());
    }
}

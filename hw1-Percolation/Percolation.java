
/**
 * Creates an N  by N array with virtual TOP and BOTTOM locations.
 */
public class Percolation {
    /**
     * any site in the array can be blocked or open or full.
     */
    private enum Site { blocked, open, full };
    /**
     * the 2D array of type Site is named grid.
     */
    private Site[][] grid;
    /**
     * global variable N.
     */
    private int N;
    /**
     * uf tracks the union join trees.
     */
    private WeightedQuickUnionUF uf;
    /**
     * ufp tracks percolation (connection to bottom).
     */
    private WeightedQuickUnionUF ufp;
    /**
     * global variable indexSize is N*N +2 .
     * for the array and virtual top and bottom
     */
    private int indexSize;
    /**
     * The virtual location TOP.
     */
    private int top;
    /**
     *  The virtual location BOTTOM.
     */
    private int bottom;

    /**
     *  create an (N+1) by (N+1) grid with all sites = blocked.
     *  use N+1 since by convention the location index starts at 1.
     *  This uses some extra space, but is simple to implement.
     * @param n , the grid size parameter passed into the class.
     */
    public Percolation(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("'block size'"
                + " argument N is zero or negative");
        }
        this.N = n;
        indexSize = N * N + 2;
        uf = new WeightedQuickUnionUF(indexSize);
        ufp = new WeightedQuickUnionUF(indexSize);
        top = 0;
        bottom = indexSize - 1;

        grid = new Site[N + 1][N + 1];
        for (int j = 0; j <= N; j++) {
            for (int i = 0; i <= N; i++) {
                grid[i][j] = Site.blocked;
            }
        }
    }

    /**
     * Open site if it is not already open, and connect to open neighbors.
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     */
    public void open(final int i, final int j) {
        checkIndex(i, j);

        if (grid[i][j] == Site.blocked) {
            grid[i][j] = Site.open;
            isNeighborOpen(i, j);
        }
    }

    /**
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     * @return  is this site open?
     */
    public boolean isOpen(final int i, final int j) {
        checkIndex(i, j);  // check index bounds

        // an open site is not blocked, it can be open or full
        if (grid[i][j] != Site.blocked)  {
            return true;
        }
        return false;
    }
    /**
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     * @return  is this site full?
     */
    public boolean isFull(final int i, final int j) {
        checkIndex(i, j);  // check index bounds
        // translate i, j into a flat index for the union find
        int p = gridToIndex(i, j);
        if (uf.connected(p, top)) {
            return true;
        }
        return false;
    }
    /**
     * check each neighbor of a newly opened and full site.
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     */
    private void isNeighborOpen(final int i, final int j) {
        // translate i, j into a flat index for the union find
        int p = gridToIndex(i, j);

        // left neighbor exists and is open
        if (j > 1 && isOpen(i,  j - 1)) {
            uf.union(p, p - 1);
            ufp.union(p, p - 1);
        }
        // right neighbor exists and is open
        if (j < N && isOpen(i,  j + 1)) {
            uf.union(p, p + 1);
            ufp.union(p, p + 1);
        }
        if (i == 1) { // the top row is always connected to TOP
            uf.union(p, top);
            ufp.union(p, top);
        }
        // up neighbor exists and is open
        if (i > 1 && isOpen(i - 1,  j)) {
            uf.union(p, p - N);
            ufp.union(p, p - N);
        }
        // down neighbor exists and is open
        if (i < N && isOpen(i + 1,  j)) {
            uf.union(p, p + N);
            ufp.union(p, p + N);
        }
        if (i == N) { // the bottom row is connected to BOTTOM
            ufp.union(p, bottom);  // in the percolation union-find
        }
    }
    /**
     * Does the system percolate?
     * @return true for percolation
     */
    public boolean percolates() {
        if (ufp.connected(top, bottom)) {
            return true;
        }
        return false;
    }
    /**
     *
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     * @return integer, a flat array index 0 to N^2 - 1
     */
    private int gridToIndex(final int i, final int j) {
        // translate i, j into a flat index for the union find
        return (i - 1) * N + j;
    }
    /**
     * Check that the parameters  i and j are within bounds.
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     */
    private void checkIndex(final int i, final int j) {
        if (i < 1 || i > N) {
            throw new IndexOutOfBoundsException("row index I = "
                    + i + " out of bounds");
        }
        if (j < 1 || j > N) {
            throw new IndexOutOfBoundsException("column index J = "
                    + j + " out of bounds");
        }
    }
}

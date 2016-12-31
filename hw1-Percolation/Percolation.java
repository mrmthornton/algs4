
/**
 * Creates an N  by N array with virtual TOP and BOTTOM locations.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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
     * The virtual location BOTTOM.
     */
    private int bottom;
    /**
     * The total number of open sites. 
     */
    private int numberOpen;
    /**
     *  create an N by N grid with all sites = blocked.
     *  convert index(i=i-1;j=j-1), since by convention the location index starts at 1.
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
        numberOpen = 0;

        grid = new Site[N][N];
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                grid[i][j] = Site.blocked;
            }
        }
    }
    /**
     * Open site if it is not already open, and connect to open neighbors.
     * Increment numberOpen
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     */
    public void open(final int r, final int c) {
    	int i = r -1; int j = c - 1;
        checkIndex(i, j);

        if (grid[i][j] == Site.blocked) {
            grid[i][j] = Site.open;
            numberOpen++;
            isNeighborOpen(i, j);
        }
    }
    /**
     * Returns the total number of open sites.
     * @return numOpen
     */
    public int numberOfOpenSites() {
    	return numberOpen;
    }
    /**
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     * @return  is this site open?
     */
    public boolean isOpen(final int r, final int c) {
    	int i = r -1; int j = c - 1;
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
    public boolean isFull(final int r, final int c) {
    	int i = r -1; int j = c - 1;
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
     * @param i the row variable indexed at 0.
     * @param j the column variable indexed at 0.
     * i and j are index 0, r and c are index 1
     */
    private void isNeighborOpen(final int i, final int j) {
        // translate i, j into a flat index for the union find
        int p = gridToIndex(i, j);

        // i and j are index 0, r and c are index 1
        // isOpen uses r,c, row column indexing
        int r = i + 1; int c = j + 1;
        // left neighbor exists and is open
        if (j > 0 && isOpen(r,  c - 1)) {
            uf.union(p, p - 1);
            ufp.union(p, p - 1);
        }
        // right neighbor exists and is open
        if (j < N - 1 && isOpen(r,  c + 1)) {
            uf.union(p, p + 1);
            ufp.union(p, p + 1);
        }
        if (i == 0) { // the top row is always connected to TOP
            uf.union(p, top);
            ufp.union(p, top);
        }
        // up neighbor exists and is open
        if (i > 0 && isOpen(r - 1,  c)) {
            uf.union(p, p - N);
            ufp.union(p, p - N);
        }
        // down neighbor exists and is open
        if (i < N - 1 && isOpen(r + 1,  c)) {
            uf.union(p, p + N);
            ufp.union(p, p + N);
        }
        if (i == N - 1) { // the bottom row is connected to BOTTOM
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
        int offsetIndex = 1 + i * N + j; 
    	// The offset of 1 is used because the there are N * N grid locations,
        // plus a virual top at index 0, and a virual bottom at index N * N + 1
        // so the first grid location is index 1, not 0.
        return offsetIndex; 
    }
    /**
     * Check that the parameters  i and j are within bounds.
     * @param i the row variable indexed at 1.
     * @param j the column variable indexed at 1.
     */
    private void checkIndex(final int i, final int j) {
        if (i < 0 || i > N - 1) {
            throw new IndexOutOfBoundsException("row index I = "
                    + i + " out of bounds");
        }
        if (j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("column index J = "
                    + j + " out of bounds");
        }
    }
}

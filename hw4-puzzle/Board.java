
public class Board {
    private int [][] tiles;
    private Index2D [] goalPos; 
    private int [][] goalValue;
    private int N;
    
    public Board(int[][] tiles)           // construct a board from an N-by-N array of tiles
    {
        N =  tiles.length;
        if (N != tiles[0].length) { //  check for square matrix
            String msg = "This array is not NxN";
            throw new UnsupportedOperationException(msg);
        }
            this.tiles = new int[N][N];
            for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                assert tiles[i][j] < N * N  && tiles[i][j] >= 0;
                this.tiles[i][j] = tiles[i][j];
            }
        // setup arrays for goal values and goal positions
        goalValue = new int[N][N];
        goalPos = new Index2D[N * N + 1];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                goalValue[i][j] = linearIndex(i, j);
                goalPos[linearIndex(i, j)] = new Index2D(i, j);
            }
        goalValue[N-1][N-1] = 0;
        goalPos[0] = goalPos[N * N];
    }
   
    public int dimension()                 // board dimension N
    {
        return N;
    }
    
    public int hamming()                   // number of tiles out of place
    {
        int h = -1;  // the blank, value 0, will always be out of place
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                if (tiles[i][j] !=  linearIndex(i, j))
                    h++;
        return h;
    }

    public int manhattan()                 // sum of Manhattan distances between tiles and goal
    {
        int deltaSum = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (tiles[i][j] != 0) {
                    int deltaI = goalPos[tiles[i][j]].i - i;
                    int deltaJ = goalPos[tiles[i][j]].j - j;
                    deltaSum +=  Math.abs(deltaI) + Math.abs(deltaJ);
                }
            
        return deltaSum;
    }
    
    public boolean isGoal()                // is this board the goal board?
    {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != goalValue[i][j]) return false;
            }
        return true;
    }
    
    public Board twin() // a board obtained by exchanging two adjacent tiles in the same row
    {
        Board aTwin = new Board(tiles);
        int i = 0;
        while (i < 2) {
            if (aTwin.tiles[i][0] != 0 && aTwin.tiles[i][1] != 0) {
                swap(aTwin, i, 0, i, 1);
                break;
            } else 
                i++;
        }
        return aTwin;
    }
    
    public boolean equals(Object y)        //         System.out.println(twin.toString());does this board equal y?
    {
        if (y == this) return true;  // are the references pointing to the same instance?

        if (y == null) return false; // is y non null?

        if (y.getClass() != this.getClass()) return false;  // is y the same of the same class?

        Board that = (Board) y;  // cast to actual type.

        if (this.N != that.N) return false;  //  check all significant fields are the same.
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        
        return true;         
    }
    
    public Iterable<Board> neighbors()     // all neighboring boards
    {   // the 'move' in this method is conceptually moving the space in the specified direction.
        Stack<Board> nbrs = new Stack<Board>();
        Index2D blank = findBlank();
        if (canMove('L', blank)) {
            Board nL = new Board(tiles);
            move('L', nL, blank);
            nbrs.push(nL);
        }
        if (canMove('R', blank)) {
            Board nL = new Board(tiles);
            move('R', nL, blank);
            nbrs.push(nL);
        }
        if (canMove('U', blank)) {
            Board nL = new Board(tiles);
            move('U', nL, blank);
            nbrs.push(nL);
        }
        if (canMove('D', blank)) {
            Board nL = new Board(tiles);
            move('D', nL, blank);
            nbrs.push(nL);
        }
        return nbrs;
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    // HELPER METHODS
    private boolean canMove(char dir, Index2D blank) {
        if (dir == 'L' && blank.j > 0)
            return true;
        if (dir == 'R' && blank.j < N - 1)
            return true;
        if (dir == 'U' && blank.i > 0)
            return true;
        if (dir == 'D' && blank.i < N - 1)
            return true;
        return false;
    }
    
    private Index2D findBlank() {
        Index2D blank = new Index2D(0, 0);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (tiles[i][j] == 0) {
                    blank = new Index2D(i, j);
                    break;
                }
        return blank;
    }
    
    private int linearIndex(int i, int j) {
        return i * N + j + 1;
    }
    private void move(char dir, Board nb, Index2D blank) {
        if (dir == 'L') swap(nb, blank.i, blank.j, blank.i, blank.j - 1);
        if (dir == 'R') swap(nb, blank.i, blank.j, blank.i, blank.j + 1);
        if (dir == 'U') swap(nb, blank.i, blank.j, blank.i - 1, blank.j);
        if (dir == 'D') swap(nb, blank.i, blank.j, blank.i + 1, blank.j);
    }
    
    private void swap(Board brd, int i1, int j1, int i2, int j2) {
        int temp = brd.tiles[i1][j1];
        brd.tiles[i1][j1] = brd.tiles[i2][j2];
        brd.tiles[i2][j2] = temp;
    }
    
    // INNER CLASSES
    
    private final class Index2D {
        private final int i;
        private final int j;
        
        Index2D(int i, int j) {
            this.i = i;
            this.j = j;            
        }
    }
}
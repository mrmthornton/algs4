import java.util.Comparator;

public class Solver {
    Comparator<Node> MANHATTAN = new Manhattan();
    MinPQ<Node> minpq = new MinPQ<Node>(MANHATTAN);
    
    public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
    {
        minpq.insert(new Node(initial, 0, null));
        solve();
    }
    
    public boolean isSolvable()             // is the initial board solvable?
    {
        return false;
    }
    
    public int moves()                      // min number of moves to solve initial board; -1 if no solution
    {
        return minpq.min().moves;
    }
    
    public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    {
        // temp code to make compiler happy.
        int [][] tempArr = {{1,2},{3,0}};
        Board brd = new Board(tempArr);
        Stack<Board> st = new Stack<Board>();
        st.push(brd);
        return st; 
    }
    
    // HELPER METHODS
     private void solve() {
         while (minpq.min().board.isGoal() != true) {
             // save and deque minimum priority node.
             Node removed = minpq.min();
             minpq.delMin();

             // add all nodes one move from the dequed node.
             int moves = removed.moves + 1;
             Iterable<Board> neighbors = removed.board.neighbors();
             for (Board board : neighbors) {
                 //if (board.equals(removed.previous.board) != true) {
                     minpq.insert(new Node(board, moves, removed));
                 //}
             }
         }
     }

    // INNER CLASSES
    private final class Node {
        private Board board = null;
        private Node previous = null;
        private int moves = 0;

        private Node(Board board, int moves, Node previous) {
        this.board = board;
        this.moves = moves;
        this.previous = previous;
        }
    };

    private final class  Manhattan implements Comparator<Node> {

        public int compare(final Node n1, final Node n2) {
            int priority1 = n1.moves + n1.board.manhattan();
            int priority2 = n2.moves + n2.board.manhattan();

            if (priority1 < priority2)
                return -1;
            else if (priority1 == priority2)
                return 0;
            else
                return 1;
        }

        @Override
        public boolean equals(final Object a) {
            String msg = "Not Implemented. Do NOT use this method.";
            throw new java.lang.UnsupportedOperationException(msg);            
        }
    };

    public static void main(String[] args)  // solve a slider puzzle (given below)
    {

    }
}

import java.util.Comparator;

public class Solver {
    private Node removed;
    private Node removedTwin;
    private Comparator<Node> MANHATTAN = new Manhattan();
    private MinPQ<Node> minpq = new MinPQ<Node>(MANHATTAN);
    private MinPQ<Node> twinpq = new MinPQ<Node>(MANHATTAN);
    
    public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
    {
        Board twin = initial.twin();
        minpq.insert(new Node(initial, 0, null));
        twinpq.insert(new Node(twin, 0, null));
        solve();
    }
    
    public boolean isSolvable()             // is the initial board solvable?
    {
        return removed.board.isGoal();
    }
    
    public int moves()                      // min number of moves to solve initial board; -1 if no solution
    {
        return removed.moves;
    }
    
    public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    {
        Stack<Board> solutionStack = new Stack<Board>();
        if (isSolvable()) {
            Node current = removed;
            while (true) {
                solutionStack.push(current.board);
                if (current.previous == null) break;
                current = current.previous;
            }
        } else {
            solutionStack = null;
        }
        return solutionStack; 
    }
    
    // HELPER METHODS
     private void solve() {
         while (minpq.min().board.isGoal() == false
                 && twinpq.min().board.isGoal() == false) {
             // save and deque minimum priority node.
             removed = minpq.min();
             minpq.delMin();
             // and twin
             removedTwin = twinpq.min();
             twinpq.delMin();

             // add all nodes one move from the dequed node.
             int moves = removed.moves + 1;
             Iterable<Board> neighbors = removed.board.neighbors();
             for (Board board : neighbors) {
                 if (removed.previous == null
                         || board.equals(removed.previous.board) == false) {
                     minpq.insert(new Node(board, moves, removed));
                 }
             }
             // and twin
             neighbors = removedTwin.board.neighbors();
             for (Board board : neighbors) {
                 if (removedTwin.previous == null
                         || board.equals(removedTwin.previous.board) == false) {
                     twinpq.insert(new Node(board, moves, removedTwin));
                 }
             }
         }
         removed = minpq.min();
         minpq.delMin();
         if (removed.board.isGoal() != true)
             removed = new Node(removed.board, -1, removed.previous);
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

public class Solver {
    MinPQ<Node> minpq = new MinPQ<Node>();
    
    public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
    {
        minpq.insert(new Node(initial, 0, null));
        Iterable<Board> nbrs = minpq.min().current.neighbors();
        for (Board brd : nbrs) {
            minpq.insert(brd);
        }
    }
    
    public boolean isSolvable()             // is the initial board solvable?
    {
        return false;
    }
    
    public int moves()                      // min number of moves to solve initial board; -1 if no solution
    {
        return 0;
    }
    
    public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    {
        int [][] tempArr = {{1,2},{3,0}};
        Board brd = new Board(tempArr);
        Stack<Board> st = new Stack<Board>();
        st.push(brd);
        return st; 
    }
    
    // HELPER METHODS
    private 
    // INNER CLASSES
    private final class Node {
        private Board current = null;
        private int moves = 0;
        private Board previous = null;
        
        private Node(Board brd, int moves, Board previous) {
        current = brd;
        this.moves = moves;
        this.previous = previous;
        }
    }
    
    public static void main(String[] args)  // solve a slider puzzle (given below)
    {
        
    }
}

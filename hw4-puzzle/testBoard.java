
public class testBoard {
    public static void main(String[] args) {
        int [][] arr = {{1,2,3},
                {4,0,5},
                {6,7,8}};

        Board testBed = new Board(arr);
        System.out.println(testBed.toString());

        Board twin = testBed.twin();
        System.out.println(twin.toString());

        Iterable<Board> nbrs = testBed.neighbors();
        System.out.println(nbrs.toString());

        System.out.println(testBed.dimension());
        System.out.println(testBed.hamming());
        System.out.println(testBed.manhattan());
        System.out.println(testBed.isGoal());

        int[][] goalArr = {{1,2,3},
                {4,5,6},
                {7,8,0}};
        Board goal = new Board(goalArr);
        System.out.println(goal.dimension());
        System.out.println(goal.hamming());
        System.out.println(goal.manhattan());
        System.out.println(goal.isGoal());

        int[][] twinTest = {{0,3,1},
                {2,5,4},
                {8,6,7}};
        Board twinboard = new Board(twinTest);
        System.out.println(twinboard.toString());
        System.out.println(twinboard.twin().toString());
    }
}

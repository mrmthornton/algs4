public class BounceingBalls
{
    public static void main(String[] args)
    {
        final int range = 100;
        final int N = Integer.parseInt(args[0]);
        
        double ballRadius = 1.0;
        double ballXpos, ballYpos;
        double ballXvel, ballYvel;
        
        Ball [] balls = new Ball [N];
                
        for (int i = 0;  i< N;  i++) {
            ballXpos = StdRandom.uniform(0, range);
            ballYpos = StdRandom.uniform(0, range);
            ballXvel = StdRandom.uniform(0.1, 1);
            ballYvel = StdRandom.uniform(0.1, 1);
            balls[i] = new Ball(ballRadius, ballXpos, ballYpos, ballXvel, ballYvel);
        }
        StdDraw.setXscale(0, range);
        StdDraw.setYscale(0, range);
        while (true)
        {
            StdDraw.clear();
            for (int i = 0; i < N; i++)
            {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}

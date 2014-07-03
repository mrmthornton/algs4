
public class Ball {

    private double rx, ry; // position
    private double vx, vy ; // velocity
    private final double radius; // radius
    
    public Ball()
    {
        radius = 2;
        // initialize position and velocity for each ball instance
    }
    
    public void move(double dt)
    {
        if((rx + vx*dt < radius) || (rx + vx*dt >1.0 - radius)) {vx = -vx;}
        if((rx + vy*dt < radius) || (rx + vy*dt >1.0 - radius)) {vy = -vy;}
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }
    
    public void draw()
    { StdDraw.filledCircle(rx,  ry, radius); }
}


public class Ball {

    private double rx, ry; // position
    private double vx, vy ; // velocity
    private final double radius; // radius
    
    public Ball()
    {
        // initialize position and velocity for each ball instance
        radius = 1;
        rx =0.5; ry = 0.5;
        vx = 0; vy = 0;
    }
    public Ball( double rad, double x, double y, double xVel, double yVel) {
        // client initialized ball
        radius = rad;
        rx = x; ry = y;
        vx = xVel; vy = yVel;
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

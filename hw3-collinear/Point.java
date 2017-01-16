/**
 * @author: Michael Thornton
 * Email:mrmthornton@gmail.com
 *
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 */

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Creates a tuple with two ordinates.
 * Implements the Comparable interface to
 * allow comparison between position and slope.
 */
public class Point implements Comparable<Point> {
    /**
     * Constant negative infinity.
     */
    private static final double NEG_INF = Double.NEGATIVE_INFINITY;
    /**
     * Constant positive infinity.
     */
    private static final double POS_INF = Double.POSITIVE_INFINITY;
    /**
     * Constant positive zero.
     */
    private static final double POS_ZERO = (1.0 - 1.0) / 1.0;  //   +0.0
    /**
     * Constant negative zero.
     */
    // private static final double NEG_ZERO = (1.0 - 1.0) / -1.0;  //   -0.0
    /**
     *  The x coordinate.
     */
    private final int x;
    /**
     * The  y coordinate.
     */
    private final int y;

    /**
     * create the point (x, y).
     * @param xComponent  The x coordinate of the new point.
     * @param yComponent  The y coordinate of the new point.
     */
    public Point(final int xComponent, final int yComponent) {
        /* DO NOT MODIFY */
        x = xComponent;
        y = yComponent;
    }

    /**
     * plot this point to standard drawing.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
        // StdDraw.circle(x, y, 10.0);
    }

    /**
     * draw line between this point and that point to standard drawing.
     * @param that , a point.
     */
    public void drawTo(final Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * @return a string representation of this point.
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     *      *  The invoking point p1 is less than the argument point p2,
     *  if and only if either p1.y < p2.y or if p1.y = p2.y and p1.x < p2.x .
     *  @return -1, 0, 1 as the result of lexicographical comparison.
     */
    public int compareTo(final Point that) {
        if (this.y < that.y 
            || (this.y  == that.y && this.x < that.x)) {
            return -1;    //  less than
        } else if (this.y == that.y && this.x == that.x) {
            return 0;     //  equal to 
        } else {
            return 1;     //  greater than
        }
    }

    /**
     *  Treat the slope of a horizontal line segment as positive zero .
     *  Treat the slope of a vertical line segment as positive infinity .
     *  Treat the slope of a degenerate line segment as negative infinity. 
     *  'this' and 'that' are points forming a line, the slope is determined by slopeTo().
     *   @param that . The foreign point in the comparison.
     * @return slope   given by the formula (y1 − y0) / (x1 − x0).
     */
    public double slopeTo(final Point that) {
        if (that.y == this.y && that.x == this.x) {  // degenerate case
            return NEG_INF;
        } else if (that.x == this.x) { // vertical line
            return POS_INF;
        } else if (that.y == this.y) {  // horizontal line
            return POS_ZERO;
        } else {
            return (double) (that.y - this.y) / (that.x - this.x);
        }
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }
    
    /**
     *  inner classes.
     */

    /**
     * The SLOPE_ORDER comparator compares points by slope from the invoking point (x0, y0).
     * Formally, the point (x1, y1) is less than the point (x2, y2) 
     * if and only if the slope (y1 − y0) / (x1 − x0)
     * is less than the slope (y2 − y0) / (x2 − x0).
     * Treat horizontal, vertical, and degenerate line segments
     * as in the slopeTo() method. 
     *      *  @return -1, 0 , 1 slope to p1 <, =, > slope to p2.
     */
    private class  SlopeOrder implements Comparator<Point> {

        public int compare(final Point p1, final Point p2) {
            double slope1 = Point.this.slopeTo(p1);
            double slope2 = Point.this.slopeTo(p2);
            // slopeTo() degenerative case returns NEG_INF;
            // slopeTo() vertical line returns POS_INF;
            // slopeTo() horizontal line returns POS_ZERO;

            if (slope1 < slope2) {
                return -1; 
            } else if (slope1 == slope2) {
                return 0; 
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(final Object a) {
            String msg = "Not Implemented. Do NOT use this method.";
            throw new java.lang.UnsupportedOperationException(msg);            
        }
    }
}
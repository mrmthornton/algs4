

/**
 * @author: Michael Thornton
 * Email:mrmthornton@gmail.com
 *
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 */

import java.util.Comparator;

/**
 * Creates a tuple with two ordinates.
 * Implements the Comparable interface to
 * allow comparison between position and slope.
 */
public class Point implements Comparable<Point> {

    /**
     *  The x coordinate.
     */
    private final int x;
    /**
     * The  y coordinate.
     */
    private final int y;
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
    @SuppressWarnings("unused")
    private static final double NEG_ZERO = (1.0 - 1.0) / -1.0;  //   -0.0

    /**
     *  compare points by slope.
     */
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    /**
     * create the point (x, y).
     * @param x  The x coordinate
     * @param y  The y coordinate
     */
    public Point( /**
     *  @return -1, 0 , 1 result of lexicographical comparison.
     *  The invoking point p1 is less than the argument point p2
     *  if and only if either p1.y < p2.y or if p1.y = p2.y and p1.x < p2.x.
     */int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * plot this point to standard drawing.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
        //StdDraw.circle(x, y, 0.30);
    }

    /**
     * draw line between this point and that point to standard drawing.
     * @param that
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * return string representation of this point.
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * @param that, the argument point.
     *  @return -1, 0 , 1 result of lexicographical comparison.
     *  The invoking point (x0, y0) is less than the argument point (x1, y1)
     *  if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     */
    public int compareTo(Point that) {
        if (this.y < that.y || (this.y  == that.y && this.x < that.x)) {
            return -1;  // this is less than 'that'
        }
        else if (this.y == that.y && this.x == that.x) {
            return 0;   // this is equal to 'that'
        }
        else {
            return 1;       // this is greater than 'that'
        }
    }

    /**
     * @param that, the argument point.
     * @return slope   given by the formula (y1 − y0) / (x1 − x0).
     *  Treat the slope of a horizontal line segment as positive zero
     *  Treat the slope of a vertical line segment as positive infinity
     *  Treat the slope of a degenerate line segment as negative infinity. 
     */
    public double slopeTo(Point that) {
        // degenerate case
        if (that.y == this.y && that.x == this.x) {
            return NEG_INF;
        }
        // vertical line
        else if (that.x == this.x) {
            return POS_INF;
        }
        // horizontal line
        else if (that.y == this.y) {
            return POS_ZERO;
        }
        else {
            return (double) (that.y - this.y) / (that.x - this.x);
        }
    }

    /**
     * helper methods
     */



    /**
     *  inner classes
     */

    /**
     * The SLOPE_ORDER comparator should compare points 
     * by the slopes they make with the invoking point (x0, y0).
     * Formally, the point (x1, y1) is less than the point (x2, y2) 
     * if and only if the slope (y1 − y0) / (x1 − x0)
     * is less than the slope (y2 − y0) / (x2 − x0).
     *  @return -1, 0 , 1 slope to p1 <, =, > slope to p2.
     * Treat horizontal, vertical, and degenerate line segments
     * as in the slopeTo() method. 
     */
    private class  SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(final Point p1, final Point p2) {
            double p1slope = Point.this.slopeTo(p1);
            double p2slope = Point.this.slopeTo(p2);
            // degenerative case returns NEG_INF;
            // vertical line returns POS_INF;
            // horizontal line returns POS_ZERO;

            // reverse the order of returned values if a negative slope
            // is involved. This allows negative slopes to be 'greater than'
            // positive slopes, and small values of negative slope are 
            // 'greater than' larger values. This is useful when ordering 
            // points in a continuous counter-clockwise manner.
            //int ifNeg = 1;
        
            if (p1slope == NEG_INF  &&  p2slope == POS_INF) {
                return -1;
            } else if ((p1slope == POS_INF && p2slope == POS_INF) ||
                            (p1slope == NEG_INF && p2slope == NEG_INF)) {
                return 0;
            } else if ((p1slope == POS_INF && p2slope == NEG_INF)) {
                return 1;
            } else if (p1slope < p2slope) {
                return -1;
            } else if (p1slope - p2slope == 0) {
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
    };



    /**
     * unit test.
     * @param args 
     */
    public static void main(String[] args) {

        Point p0 = new Point(-1, 1);
        Point p1 = new Point(0, 5);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(5, 0);
        Point p4 = new Point(-6, 0);

        Point[] a = new Point[5];
        a[0] = p0;
        a[1] = p1;
        a[2] = p2;
        a[3] = p3;
        a[4] = p4;
        for (Point p : a) {
            StdOut.println(p.x + " " + p.y);
        }
        StdOut.println();

        for (int i = 1; i<5; i++) {
            StdOut.println(p0.slopeTo(a[i]));
        }
        StdOut.println();

        int comp = p0.SLOPE_ORDER.compare(p1, p2);
        StdOut.println(comp);
    }
}
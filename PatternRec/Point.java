// Point.java
// Immutable data type that represents a point in the plane
// For Algorithms, Part I course
// Specifications from: http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
// base code provided from: http://coursera.cs.princeton.edu/algs4/testing/collinear/Point.java
// by Eric Mancini

// Goal API:
/*
public class Point implements Comparable<Point> {
   public Point(int x, int y)                         // constructs the point (x, y)

   public   void draw()                               // draws this point
   public   void drawTo(Point that)                   // draws the line segment from this point to that point
   public String toString()                           // string representation

   public               int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
   public            double slopeTo(Point that)       // the slope between this point and that point
   public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
}

*/
/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        
        System.out.print("Slope comparison: ");
        System.out.print(this.toString());
        System.out.print(" vs. ");
        System.out.print(that.toString());
        System.out.print(" -> ");
        
       
        // are the points equal?
        if (this.x == that.x && this.y == that.y){
            return Double.NEGATIVE_INFINITY;
        }
        // are the lines vertical?
        if (this.x == that.x){
            return Double.POSITIVE_INFINITY;
        }
        // are the lines horizontal?
        if (this.y == that.y){
            return 0;
        }
        
        // otherwise, do a simple slope calculation
        return (double) ( that.y -  this.y) / (  that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        
        // if x = x and y = y, return 0, as they are the same point
        if (this.x == that.x && this.y == that.y){
            return 0;
        }
        
        // if y = y, break ties with x
        if (this.y == that.y){
            if (this.x > that.x){
                return 1;
            }
            else {
                return -1;  
        }
        }
        
        // otherwise compare by y
        if (this.y > that.y){
            return 1;
        }
        else {
            return -1;
        }
    }

    
    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    
    // compares two points by the slope they make with this point
    // i.e. calculates the slope from a to this point, and b to this point
    // then compares a vs b based on those slopes
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>(){
        @Override
        public int compare(Point a, Point b) {
             // StackOverflow suggestion:            
            return Double.compare(slopeTo(a), slopeTo(b));            
        }        
        };        
    }
    


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        System.out.println("Point.java main function called!");
        // 4 points in a square
        Point a = new Point(1,1);
        Point b = new Point(1,2);
        Point c = new Point(2,2);
        Point d = new Point(2,1);
        
        //System.out.println(c.compareTo(a));
        
        // slope = 1
        System.out.print(a.slopeTo(c));
        System.out.println();
        // slope = 1
        System.out.print(c.slopeTo(a));
        System.out.println();
        // vertical line, slope = +infinity
        System.out.print(a.slopeTo(b));
        System.out.println();
        // horizontal line, slope = 0
        System.out.print(a.slopeTo(d));
        System.out.println();
        // same point, slope = -infinity
        System.out.print(a.slopeTo(a));
        System.out.println();
    }
}
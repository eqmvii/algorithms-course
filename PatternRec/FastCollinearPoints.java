// FastCollinearPoints.java
// Checks 4 points to see if they lie on the same line segment
// For Algorithms, Part I course
// Specifications from: http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
// by Eric Mancini

// Corner case info: Should work even with 5 or more collinear points
// Performance requirement: n^2 log(n) at worst runtime, n plus line segments returned space

import java.util.Comparator;
import java.util.Arrays;

public class FastCollinearPoints {
    
    private int numSegs = 0;
    // start with this much space for line segments, dynamically expanding and then trimming at the end
    private LineSegment[] segs = new LineSegment[1];    
    
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        System.out.println("Searching for line segments...");
        // create a storage array to store slope data
        
        // loop through the array of points
        
        // at each point, calculate slopeTo from that point to each other point
        
        // place all of those slopes in the storage array
        
        // sort the storage array
        
        // look through the storage array for any contiguous section of 3 or more slopes
        
        // That creates a line segment, sort points in the segment to find first/last
        
        // add first/last to the line segment list (avoid duplicates)
    }
    
    // the number of line segments
    public int numberOfSegments() {
        return numSegs;
    }
    
     // the line segments
    public LineSegment[] segments() {
        return segs;
    }
       
    // test client for brute force solution with simple list of points
   public static void main(String[] args) {
       System.out.println("Fast collinear algorithm test client running!");
       
       Point a = new Point(4,4);
       Point b = new Point(1,1);
       Point c = new Point(3,3);
       Point d = new Point(23,2);
       Point e = new Point(2,2);
       Point f = new Point (2,6);
       Point[] points = new Point[6];
       points[0] = a;
       points[1] = b;
       points[2] = c;
       points[3] = d;
       points[4] = e;
       points[5] = f;
       //System.out.println(a.toString());
       FastCollinearPoints tester = new FastCollinearPoints(points);
       System.out.println(tester.numberOfSegments());
       System.out.println(tester.segments().length);
       
   }
}
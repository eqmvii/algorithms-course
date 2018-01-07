// TestClient.java
// Test client for Collinear Points assignment
// by Eric Mancini
// boilerplate code from http://coursera.cs.princeton.edu/algs4/assignments/collinear.html

/*
 * Testing:
 * 
 * javac-algs4 TestClient.java
 * java-algs4 TestClient input8.txt 
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
//import java.util.Iterator;

public class TestClient {

public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    // choose fast or brute method below:
    
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    //BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}

}
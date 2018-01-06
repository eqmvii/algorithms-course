// BruteCollinearPoints.java
// Checks 4 points to see if they lie on the same line segment
// For Algorithms, Part I course
// Specifications from: http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
// by Eric Mancini

// Corner case info: No samples will have 5 or more collinear points
// Performance requirement: n^4 at worst runtime, n plus line segments returned space

// TODO: Think about vertical and horizontal line segments

import java.util.Comparator;
import java.util.Arrays;

public class BruteCollinearPoints {
   
    private int numSegs = 0;
    // start with this much space for line segments, dynamically expanding and then trimming at the end
    private LineSegment[] segs = new LineSegment[1];
   
   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points)
   {
       /*
       for (int i = 0; i < points.length; i++){
           System.out.print("Point: ");
           System.out.print(points[i].toString());
           System.out.println();
       }
       */
       
       // loop through the array of points checking all possible sets of 4 points
       for (int i = 0; i < points.length;i++){
           for (int j = i + 1; j < points.length; j++) {
               for (int k = j + 1; k < points.length; k++){
                   for (int l = k + 1; l < points.length; l++){
                       // use the comparator API to test 
                       // one point for having the same slope as
                       // two other points
                       Comparator<Point> mcTest = points[i].slopeOrder();
                       //System.out.println(points[i].slopeOrder().compare(points[j], points[k]));
                       
                       // Test by printing all point comparisons: 
                       /*
                       System.out.print("Comparing points: ");
                       System.out.print(points[i].toString() + points[j].toString() + points[k].toString() + points[l].toString());
                       System.out.println();                       
                       System.out.print("Slope test results: ");
                       System.out.print(mcTest.compare(points[j], points[k]) + " " + mcTest.compare(points[j], points[l]) + " " + mcTest.compare(points[l], points[k]));
                       System.out.println();
                       */
                       if (mcTest.compare(points[j], points[k]) == 0 && mcTest.compare(points[j], points[l]) == 0 && mcTest.compare(points[l], points[k]) == 0) 
                       {
                           System.out.println("Found a line segment!");
                           // add the 4 points to an array
                           Point[] fullSeg = new Point[4];
                           fullSeg[0] = points[i];
                           fullSeg[1] = points[j];
                           fullSeg[2] = points[k];
                           fullSeg[3] = points[l];
                          

                            
                            // test printing the array of points
                            System.out.print("Unordered segment points: ");
                            for (int jamie = 0; jamie < 4; jamie++){
                                System.out.print(fullSeg[jamie].toString());
                            }
                            System.out.println();
                            
                            // sort the array
                            Arrays.sort(fullSeg);
                            
                            System.out.print("Ordered segment points: ");
                            for (int kevin = 0; kevin < 4; kevin++){
                                System.out.print(fullSeg[kevin].toString());
                            }
                            System.out.println();
                           
                           // first and last points form the segment
                            LineSegment newSeg = new LineSegment(fullSeg[0], fullSeg[1]);
                            segs[numSegs] = newSeg;                           
                           
                           numSegs += 1;
                           // add 10 extra slots to the array if we've found too many segments
                           if (numSegs > (segs.length - 1) ){
                               expandSegs();
                           }
                       }
                           
                   }
               }
           }
       }
       
       // after searching for segments, trim the resulting array of extra space
       trimSegs();
   }
   
   private void expandSegs(){
       System.out.println("Expanding segs array");
       LineSegment[] temp = new LineSegment[numSegs + 10];;
       for (int i = 0; i < segs.length; i++){
           temp[i] = segs[i];
       }
       segs = temp;
   }
   
   private void trimSegs(){
       System.out.println("Code finished; triming segs array");
       LineSegment[] temp = new LineSegment[numSegs];
       for (int i = 0; i < numSegs; i++){
           temp[i] = segs[i];
       }
       segs = temp;
   }   
   
   // the number of line segments
   public int numberOfSegments()
   {
       return numSegs;
   }
   
   // the line segments
   public LineSegment[] segments()
   {
       return segs;
   }
  
   
   // test client for brute force solution with simple list of points
   public static void main(String[] args) {
       System.out.println("Brute Force test client running!");
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
       BruteCollinearPoints tester = new BruteCollinearPoints(points);
       System.out.println(tester.numberOfSegments());
       System.out.println(tester.segments().length);
   }
   
}
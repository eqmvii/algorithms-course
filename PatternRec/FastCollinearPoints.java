// FastCollinearPoints.java
// Checks 4 points to see if they lie on the same line segment
// For Algorithms, Part I course
// Specifications from: http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
// by Eric Mancini

// Corner case info: Should work even with 5 or more collinear points
// Performance requirement: n^2 log(n) at worst runtime, n plus line segments returned space

// TODO: How does this do with vertical or horizontal line segments?

// Unit testing:
/*
 * javac-algs4 FastCollinearPoints.java
 * java-algs4  FastCollinearPoints
 */

import java.util.Comparator;
import java.util.Arrays;

public class FastCollinearPoints {
    
    private int numSegs = 0;
    // start with this much space for line segments, dynamically expanding and then trimming at the end
    private LineSegment[] segs = new LineSegment[1];    
    
    // finds all line segments containing 4 or more points
    // Somewhat messy
    // TODO: Clean up "Don't Repeat Yourself" issues on pass to look for adjacent slopes
    public FastCollinearPoints(Point[] points) {
        System.out.println("Searching for line segments...");
        System.out.println();
        // create a storage array to store points sorted with respect to slopes to current point
        Point[] storage = new Point[points.length];
        
        // loop through the array of points
        for (int i = 0; i < points.length; i++){
            // at current point, add itself and all other points to new array 
            for (int j = 0; j < points.length; j++){
                storage[j] = points[j];
            }
            
            // Testing: print the new array pre-sorting
            /*
            System.out.print("Pre-sorted: ");
            for (int k = 0; k < storage.length; k++){
                System.out.print(storage[k].toString());
            }
            System.out.println();
            */
            
             // sort the storage array with respect to slopeOrder each point has to first point 
            Arrays.sort(storage, points[i].slopeOrder());      
            
            // Testing: print the new array post-sorting
            System.out.print("Sorted against ");
            System.out.print(points[i].toString());
            System.out.print(": ");
            for (int k = 0; k < storage.length; k++){
                System.out.print("[");
                System.out.print(storage[k].toString());
                System.out.print(", slope: ");
                System.out.print(String.format("%.2g", points[i].slopeTo(storage[k])));
                System.out.print("]");
            }
            System.out.println();
            System.out.println();
            
            // look through the storage array for 2 or more identical slopeOrders
            double prior_slope = storage[1].slopeTo(storage[0]);
            int prior_slope_location = 1;
            boolean streak = false;
            for (int l = 2; l < storage.length; l++){
                if (storage[l].slopeTo(storage[0]) == prior_slope){
                    streak = true;
                    // System.out.println("We're on a streak!");
                    // save the segment if we're at the end of the array and on a long enough streak
                    if (l == (storage.length -1) && l - prior_slope_location > 1)
                    {
                        // Test: print info about the segment
                        /*
                        System.out.println("This is a segment!");
                        System.out.print("Length: ");
                        System.out.print((l - prior_slope_location) + 2);
                        System.out.println();
                        */
                        
                        int segLength = (l - prior_slope_location) + 2;
                        
                        Point[] newSeg = new Point[segLength];
                        newSeg[0] = storage[0];
                        int counter = prior_slope_location;
                        for (int m = 1; m < segLength; m++){
                            newSeg[m] = storage[counter];
                            counter++;
                        }
                        Arrays.sort(newSeg);
                        
                        // Test: print the array of new points
                        System.out.print("Segment found: ");
                        for (int n = 0; n < newSeg.length; n++){
                            System.out.print(newSeg[n].toString());
                        }
                        System.out.println();
                        
                        addSeg(newSeg[0], newSeg[newSeg.length -1]);  
                        
                    }
                }
                else {
                    // if we were on a streak, that's a segment!
                    if (streak && l - prior_slope_location > 1){
                        // Test: print info about the segment
                        /*
                        System.out.println("This is a segment!");
                        System.out.print("Length: ");
                        System.out.print((l - prior_slope_location) + 1);
                        System.out.println();
                        */
                        
                        int segLength = (l - prior_slope_location) + 1;
                        
                        Point[] newSeg = new Point[segLength];
                        newSeg[0] = storage[0];
                        int counter = prior_slope_location;
                        for (int m = 1; m < segLength; m++){
                            newSeg[m] = storage[counter];
                            counter++;
                        }
                        Arrays.sort(newSeg);
                        
                        // Test: print the array of new points
                        System.out.print("Segment found: ");
                        for (int n = 0; n < newSeg.length; n++){
                            System.out.print(newSeg[n].toString());
                        }
                        System.out.println();
                        
                        addSeg(newSeg[0], newSeg[newSeg.length -1]);
                        
                    }
                    // otherwise move on 
                    else {
                        prior_slope_location = l;
                        prior_slope = storage[l].slopeTo(storage[0]);
                    }
                    streak = false;                    
                }
                
            }

            
        }      
       // after searching for segments, trim the resulting array of extra space
       trimSegs();        
       

    }
    
    private void addSeg(Point a, Point b) {
        LineSegment newSeg = new LineSegment(a, b);
        if (numSegs == 0){
            segs[0] = newSeg;
            numSegs += 1;
            // add 10 extra slots to the array if we've found too many segments
            if (numSegs > (segs.length - 1) ){
                expandSegs();
            }
            return;
        }
        else {
            // loop through existing segments, if we already have this one,
            // don't add it
            for (int p = 0; p < numSegs; p++){
                System.out.println(segs[p].toString() + " vs " + newSeg.toString());
                if (segs[p].toString().equals(newSeg.toString())){
                    System.out.print("Segment already found (");
                    System.out.print(newSeg.toString());
                    System.out.print(")");
                    System.out.println();
                    return;
                }
            }
            // segment is unique!
            segs[numSegs] = newSeg;
            numSegs += 1;
            // add 10 extra slots to the array if we've found too many segments
            if (numSegs > (segs.length - 1) ){
                expandSegs();
            }
        }
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
       Point g = new Point (5,5);
       Point h = new Point (94,2);
       Point[] points = new Point[8];
       points[0] = a;
       points[1] = b;
       points[2] = c;
       points[3] = d;
       points[4] = e;
       points[5] = f;
       points[6] = g;
       points[7] = h;
       //System.out.println(a.toString());
       FastCollinearPoints tester = new FastCollinearPoints(points);
       System.out.println(tester.numberOfSegments());
       System.out.println(tester.segments().length);
       
   }
}
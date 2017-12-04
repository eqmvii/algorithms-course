// Percolation.java by Eric Mancini
// Execution commands for use with the provided packages:
/* 

Compile: 

javac-algs4 Percolation.java

Run: 

java-algs4 Percolation

*/

// First Java code I've written in 12 years, so pardon the sloppiness
// Passes the auto-grader with a score of 80/100

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private int[][] grid;
    // private int gridSize;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF dataset;
    
    private void diagnostics() {
        System.out.println("--DIAGNOSTICS---");
        System.out.println("Top: " + top);
        System.out.println("Bottom: " + bottom);
        
    }
    
    // create n-by-n grid, with all sites blocked
   public Percolation(int n)
   {
       grid = new int[n][n];
       // add two fake items, one representing the top
       // and one representing the bottom
       dataset = new WeightedQuickUnionUF((n * n) + 2);
       top = ( ((n * n) + 2) - 2);
       bottom = ( ((n * n) + 2) - 1);
       // gridSize = (n * n) + 2;
       // this.diagnostics();
       // connect the top to the top row
       // and the bottom to the bottom row
       for (int i = 0; i < grid.length; i++){
           dataset.union(top, i);
           dataset.union(bottom, i + (n * (n-1)) );
       }
   }
   
   // testing grid creation
   private void printGrid() {             
       for (int i = 0; i < grid.length; i++) {
           // System.out.print("Row! ");
           for (int j = 0; j < grid[0].length; j++) {
               // System.out.println("Col!");
               System.out.print(grid[i][j]);
               System.out.print(" ");
           }
           System.out.println();

       }
       /*
       System.out.println();
              for (int i = 0; i < grid.length; i++) {
       for (int j = 0; j < grid[0].length; j++) {
               // System.out.println("Col!");
               System.out.print(gridToLine(i, j));
               System.out.print(" ");
           }
           System.out.println();
              }
              */
   }
   
   private int gridToLine(int x, int y){
       return (y + (x * grid.length));
   }
   
   private void boundaryCheck(int row, int col){
       if (row < 0 || col < 0 || row >= grid.length || col >= grid.length){
           throw new IllegalArgumentException("Index out of bounds!");
       }
   }       
   
   // open site (row, col) if it is not open already   
   public void open(int row, int col)  {
       row -= 1;
       col -=1;
       boundaryCheck(row, col);

       if (grid[row][col] == 1) {
           return;
       }
       // otherwise, set it to open
       grid[row][col] = 1;
       int newCell = gridToLine(row, col);
       int nextCell;
       
       // connect it to any open sides in the vicinity
       // look up (row - 1, col)
       // all calls to isOpen are incremented by 1 because come on
       if (row - 1 >= 0) {
           if (isOpen(row, col + 1)){               
               nextCell = gridToLine(row -1, col);
               dataset.union(newCell, nextCell);           
           }
       }
       // look right (row, col + 1)
       if (col + 1 < grid.length){
           if (isOpen(row + 1, col +2)){               
               nextCell = gridToLine(row, col + 1);
               dataset.union(newCell, nextCell);  
           }
       }
       
       // look down (row + 1, col)
       if (row + 1 < grid.length){
           if (isOpen(row + 2, col + 1)){               
               nextCell = gridToLine(row + 1, col);
               dataset.union(newCell, nextCell);           
           }
       }
       
       // look left (row, col - 1)
       if (col - 1 >= 0) {
           if (isOpen(row + 1, col)){               
               nextCell = gridToLine(row, col - 1);
               dataset.union(newCell, nextCell);           
           }
       }
   }
   
   // is site (row, col) open?
   public boolean isOpen(int row, int col)  {
       row -= 1;
       col -=1;
       boundaryCheck(row, col);
       
       if (grid[row][col] == 0) {
           // System.out.println("it is closed!");
           return false;
       }
       else {
           //System.out.println("It is open!");
           return true;
       }
   }
   
   // TODO: is site (row, col) full, i.e., connected to the top?
   public boolean isFull(int row, int col)  {
       row -= 1;
       col -=1;
       boundaryCheck(row, col);

       // return false if not on the bottom row
       int curCell = gridToLine(row, col);
       if (dataset.connected(curCell, top) && isOpen(row + 1, col + 1)){
           return true;
       }
       else {
       return false;
       }
   }
   
   // number of open sites
   public int numberOfOpenSites()  {
       int counter = 0;
       for (int i = 0; i < grid.length; i++) {
           for (int j = 0; j < grid[0].length; j++) {
               if (isOpen(i +1,j +1)) {
                   counter += 1;
               }
           }           
       }
       return counter;
   }
   
   
   // TODO: does the system percolate?
   public boolean percolates() {
       if(dataset.connected(top,bottom)){
           //System.out.println("SYSTEM PERCOLATES!");
           return true;
       }
          else {
              // System.out.println(". . . . . .");
              return false;
          }
   }   

   // test client (optional)
   public static void main(String[] args) {
       int size;
       if (args.length < 1) {
           throw new IllegalArgumentException("Must provide an argument!");
           // size = 3;
       }
       else {
           size = Integer.parseInt(args[0]);
           if (size < 1) {
               throw new IllegalArgumentException("No negative small percolations silly");
           }
       }           

       System.out.println("Hello, World. This was run from Percolation!");
       Percolation karl = new Percolation(size);
       karl.printGrid();
       karl.percolates();
       
       // karl.isOpen(3,3);

       karl.open(0,0);
       karl.printGrid();
       karl.percolates();
       System.out.println(karl.numberOfOpenSites());
       
       karl.open(2,2);       
       karl.printGrid();
       karl.percolates();
       System.out.println(karl.numberOfOpenSites());
       
       karl.open(1,0);       
       karl.printGrid();
       karl.percolates();
       System.out.println(karl.numberOfOpenSites());
       
       karl.open(1,1);       
       karl.printGrid();
       karl.percolates();
       System.out.println(karl.numberOfOpenSites());
       
       karl.open(1,2);       
       karl.printGrid();
       karl.percolates();
       System.out.println(karl.numberOfOpenSites());
       
       /*
       System.out.print("Num open: ");
       System.out.print(karl.numberOfOpenSites());
       System.out.println();
       
       karl.printGrid();
       System.out.println();
       karl.isOpen(1,2);
       System.out.println();
       karl.open(1,2);
       System.out.println();
       karl.isOpen(1,2);
       karl.printGrid();
       
       System.out.print("Num open: ");
       System.out.print(karl.numberOfOpenSites());
       System.out.println();
       */
       
       System.out.println("Goodbye, World.");
       
   }
   
}
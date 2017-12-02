// PercolationStats.java
// Execution commands for use with the provided packages:
// Compile: javac-algs4 PercolationStats.java
// Run: java-algs4 PercolationStats

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    
    private Percolation myPerc;
    private int rowLength;
    private double[] trialCountArray;
    
   // perform trials independent experiments on an n-by-n grid
   public PercolationStats(int n, int trials)
   {
       rowLength = n;
       trialCountArray = new double[trials];
       int trial_counter;
       double testo;
       // do trials trials
       for (int i = 0; i < trials; i++) {
           // build a brand new percolation
           myPerc = new Percolation(n);
           // percolate it
           
           trial_counter = percTest(myPerc);
           trialCountArray[i] = (double) trial_counter / ((double) n * (double) n );
           
           /*
           System.out.println();
           System.out.print(trial_counter);
           System.out.print(" / ");
           System.out.print(n * n);
           System.out.println();
           testo = (double) trial_counter / ((double) n * (double) n );
           System.out.print("Testo: ");
           System.out.print(testo);
           System.out.println();
           */
           
           // myPerc.diagnostics();
       }
       
       // print each trial result
       /*
       for (int j = 0; j < trials; j++){
           System.out.print(trialCountArray[j]);
           System.out.print(" trials..."); 
       }
       */
       
       System.out.println();
       System.out.println();
       System.out.print(" - - - mean: ");
       System.out.print(mean(trialCountArray));
       System.out.println();
       System.out.print(" - - - stddv: ");
       System.out.print(stddev(trialCountArray));
       System.out.println();
       System.out.print(" - - - conflow: ");
       System.out.print( confidenceLo(mean(trialCountArray), stddev(trialCountArray), (double) trials) );
       System.out.println();
        System.out.print(" - - - confhi: ");
       System.out.print( confidenceHi(mean(trialCountArray), stddev(trialCountArray), (double) trials) );
       System.out.println();
       System.out.println();
   }
   
   private int percTest(Percolation testPerc){
       int row, col, counter;
       counter = 0;
       //int sanity = 0;
       while (testPerc.percolates() == false) {
           //sanity++;
           row = StdRandom.uniform(0, rowLength);
           col = StdRandom.uniform(0, rowLength);           
                      
           // System.out.print(row);
           // System.out.print(" ");
           // System.out.print(col);
           // System.out.println();      

           // randomly open a grid square
           if (testPerc.isOpen(row, col) == false){
               testPerc.open(row,col);
               // System.out.println("Opened a square!");
               counter++;
           }
           // test to see if it percolates
           // return the total number of grid squares needed to be opened

           /*
           if (sanity > 50){
               System.out.println("Forever loop :(");
               break;
           }
           */
           //break;
    
       }
       // System.out.print("Counter: ");
       // System.out.print(counter);
       // System.out.println();
       //testPerc.printGrid();
       return counter;
   }
   
   // sample mean of percolation threshold
   public double mean(double[] trialArray)
   {
       return StdStats.mean(trialArray);
   }
   
   // sample standard deviation of percolation threshold
   public double stddev(double[] trialArray)         
   {
       return StdStats.stddev(trialArray);
   }
   
   // low  endpoint of 95% confidence interval
   public double confidenceLo(double m, double s, double trials)
   {
       return ( m - ( (1.96 * s) / Math.sqrt(trials) ) );
   }
   
   // high endpoint of 95% confidence interval
   public double confidenceHi(double m, double s, double trials)    
   {
       return ( m + ( (1.96 * s) / Math.sqrt(trials) ) );
   }
   

   // test client
   public static void main(String[] args)
   {
       int n; int t;
       if (args.length != 2){
           // throw new IllegalArgumentException("Index out of bounds!");
           n = 4;
           t = 1;
       }
       else {
           n = Integer.parseInt(args[0]);
           t = Integer.parseInt(args[1]);
       }
       System.out.println("Hello, World. This was run from PercolationStats.");
       
       Stopwatch timer = new Stopwatch();
       PercolationStats test = new PercolationStats(n, t);
       System.out.println();
       System.out.print("Elapsed time: ");
       System.out.print(timer.elapsedTime());
       System.out.print(" seconds.");
       System.out.println();
       
       System.out.println("Goodbye, World.");
   }
}


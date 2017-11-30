// ******************* start of file Intro.java ******************
// (eric note: this code taken from a pdf intro to java found online)
// the overall class name must match the file prefix; i.e. the name of
// this file must be Intro.java
// usage: java Intro nums
// introductory program; reads integers from the command line,
// storing them in a linear linked list, maintaining ascending order,
// and then prints out the final list to the screen

/*
Compilation instructions: 

javac -cp . Intro.java

because of CP, it will look for included classes in the same directory, 
and hoover up the NumNode class. Magical!

*/

public class Intro { // standalone Java programs must have a main() function, which is the
                     // point where execution begins
    public static void main(String[] Args) {
        // note that locals have no public/private/... prefix; also, we
        // are using the fact that array objects, in this case Args, have
        // "length" variables built in
        int NumElements = Args.length;
        for (int I = 1; I <= NumElements; I++) {
            int Num;
            // need to do C’s "atoi()"; use parseInt(), a class method of
            // the Integer class; inverse operation is toString()
            Num = Integer.parseInt(Args[I - 1]);
            // create a new node
            NumNode NN = new NumNode(Num);
            NN.Insert();
        }
        System.out.println("final sorted list:");
        NumNode.PrintList();
    }
}

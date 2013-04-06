/* Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Course: COMSW3134
 * Assignment 2: List Utilities
 * File Description: On the command line, the arguments are a sequence of 
 * unsorted integers. The test class should first create an ImmutableList of the 
 * ints, then purge it, then sort it, then print it to stdout. Then, it should 
 * create a ListSet of the original ints and print it to stdout. 
 */

package lists;
import io.*;

public class ListSetTest{
    /*
    /*makeString*/
    public static String makeString(String[] arr){
        String toReturn = "";
        for (int i = 0; i<arr.length; i++)
            toReturn += arr[i].toString()+" ";
        return toReturn;
    }
    
    public static void main(String [] args){
        try{
            String str = makeString(args);
            ImmutableList IL = ImmutableList.parseIntImmutableList('('+str+')');
            IL = IL.purge();
            IL = IL.sort();
            IO.stdout.println("ImmutableList:\t" + IL.toString());
            ListSet LS = ListSet.ls(str);
            IO.stdout.println("ListSet:\t" + LS.toString());
        }
        catch(Exception e) {
            IO.stderr.println("ListSetTest only works with sequences of integers.");
        }
    }
}

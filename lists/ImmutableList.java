/* Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Course: COMSW3134
 * Assignment 2: List Utilities
 * File Description: ImmutableList handles linked lists of objects. Methods
 * include constructors, head(), tail(), isEmpty(), length(), find(x), reverse(),
 * nth(i), delete(x), insert(x), toString(), parseIntImmutableList(str), purge(),
 * sort, merge(list), and compareTo(list).
 */

package lists;
import io.*;

public class ImmutableList implements Comparable<ImmutableList>{
    private final Object data;
    private final ImmutableList next;
    public static final ImmutableList NIL = new ImmutableList(null, null);
    
    /*Constructor*/
    private ImmutableList (Object d, ImmutableList n){
        data = d;
        next = n;
    }
    
    /*factory methods for creating or extending ImmutableLists*/
    public static ImmutableList list (Object d){
        return new ImmutableList(d, ImmutableList.NIL);
    }
    public ImmutableList push (Object d){
        return new ImmutableList(d, this);
    }
    
    /*accessors*/
    public Object head(){
        return data;
    }
    
    public ImmutableList tail(){
        return next;
    }
    
    /* isEmpty test */
    public boolean isEmpty(){
        return this == ImmutableList.NIL;
    }
    
    /* x.length() returns the number of elements in x
     * base case: empty ImmutableList has 0 elements
     * otherwise: 1 + length of its tail
     */
    public int length(){
        return this.isEmpty()?
            0:
            1+this.tail().length();
    }
    
    /* x.find(y) returns ImmutableList.NIL if y is not an element of x and returns a
     * sublist of x whose head is y if y is an element of x
     * base case: if x is empty or its head is d, return x
     * otherwise: find d in x.tail()
     */
    public ImmutableList find (Object d){
        return this.isEmpty() || d.equals(this.head())?
            this:
            this.tail().find(d);
    }
    
    /* this.append(that) returns a new ImmutableList with all elements of this followed
     * by all elements of that.
     * base case: appending that onto the empty list is just that
     * otherwise: append that onto tail of this and then push the head of this
     *            onto the result
     */
    public ImmutableList append (ImmutableList that){
        return this.isEmpty()?
            that:
            this.tail().append(that).push(this.head());
    }
    
    /* x.reverse() returns a list with all elements of x in reverse order
     * base case: if x is empty, return x
     * otherwise: reverse the tail, and then append a one-element list
     *            containing the head onto the end
     */
    public ImmutableList reverse(){
        return this.isEmpty()?
            this:
            this.tail().reverse().append(ImmutableList.list(this.head()));
    }
    
    /* the nth element of a list (indexed from zero)
     * base case: if n is zero, return the head
     * otherwise: return the (n-1)th element of the tail
     */
    public Object nth(int n){
        return this.isEmpty() || n<0 ? null :
            n == 0 ? this.head() :
            this.tail().nth(n-1);
    }
    
    /* x.delete(y) returns a new list with all elements of x except those that
     * equal y
     * base case: if x is empty, return x
     * otherwise: if the head is equal to y, return the result of deleting y
     *            from the tail
     * otherwise: return the result of deleting y from the tail, but then push
     *            the head onto that result
     */
    public ImmutableList delete (Object d){
        return this.isEmpty()? this:
            d.equals(this.head())? this.tail().delete(d):
            this.tail().delete(d).push(this.head());
    }
    
    /* x.insert(y) assumes that x is a list of Comparables in order and returns
     * a new list with all the elements of x in addition to the new element y,
     * inserted in the correct position
     */
    public ImmutableList insert (Comparable d){
        return this.isEmpty() || ((Integer)d).compareTo(((Integer)this.head())) < 0 ?
            this.push(d):
            this.tail().insert(d).push(this.head());
    }
    
    /* toString() prints a string version (obj obj obj)
     */
    public String toString(){
        return this.isEmpty() ? "()":
            "(" + this.head().toString() + 
            (this.tail().isEmpty()? "" : " ") +
            this.tail().toString().substring(1);
    }
    
    /* parseIntImmutableList builds a list from int characters
     * format: (num num num)
     */
    public static ImmutableList parseIntImmutableList(String s){
        int openBracket = s.indexOf('(');
        int closeBracket = s.indexOf(')');
        if (openBracket!=0 || closeBracket!=s.length() - 1)
            throw new IllegalArgumentException(s);
        String[] intStrings =
            s.substring(openBracket + 1, closeBracket).split(" ");
        ImmutableList result = ImmutableList.NIL;
        for (int i = intStrings.length - 1; i >= 0; i--)
            result = result.push(Integer.parseInt(intStrings[i]));
        return result;
    }
    
    /* Returns a new list equivalent to "this" but with no duplicate elements
     * base case: return an empty list
     * otherwise: return the list without duplicates
     */
    public ImmutableList purge(){
        ImmutableList x = ImmutableList.NIL;
        Object head = this.head();
        return this.isEmpty()?
            this:
            this.delete(head).purge().push(head);   
    }
    
    /* Returns a new list made of the all elements of "this" but sorted. 
     * Must use merge sort algorithm.
     */
    public ImmutableList sort(){
        int length = this.length();
        //base case
        if (length<=1)
            return this;
        //otherwise split into two equal lists
        int middle = length/2;
        ImmutableList left = ImmutableList.NIL;
        ImmutableList right = ImmutableList.NIL;
        int count = 0;
        while (count<length){
            if (count<middle)
                left = left.push(this.nth(middle-count-1));
            if (count>=middle)
                right = right.push(this.nth(length + middle - count-1));
            count++;
        }
        //recursively split each side
        left = left.sort();
        right = right.sort();
        //merge and return
        return left.merge(right);
    }
    
    /* Returns the sorted sum of two lists.
     */
    public ImmutableList merge(ImmutableList right){
        ImmutableList toReturn = ImmutableList.NIL;
        ImmutableList left = this.reverse();
        right = right.reverse(); //reverse so that the the smallest obj are pushed last
        while (!left.isEmpty() || !right.isEmpty()){
            if (!left.isEmpty() && !right.isEmpty()){
                if (left.compareTo(right)<=0){
                    toReturn = toReturn.push(right.head());
                    right = right.tail();
                }
                else{
                    toReturn = toReturn.push(left.head());
                    left = left.tail();
                }
            }
            else if (!left.isEmpty()){
                toReturn = toReturn.push(left.head());
                left = left.tail();
            }
            else if (!right.isEmpty()){
                toReturn = toReturn.push(right.head());
                right = right.tail();
            }
        }
        return toReturn;
    }
    
    /* checks the types of both ImmutableLists' heads and calls the appropriate 
     * compareTo method Integer or String values.
     * otherwise: throw an exception
     */
    public int compareTo(ImmutableList that){
        if (this.head() instanceof Integer && that.head() instanceof Integer){
            Integer first = (Integer) this.head();
            Integer second = (Integer) that.head();
            return first.compareTo(second);
        }
        else if (this.head() instanceof String && that.head() instanceof String){
            String first = (String) this.head();
            String second = (String) that.head();
            return (first.compareTo(second));
        }
        else throw new IllegalArgumentException("compareTo requires two Immutable"
                                                    + "Lists of the same comparable");
    }
    
    public static void main(String [] args){
    }
}
    
    
    
    
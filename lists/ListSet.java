/* Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Course: COMSW3134
 * Assignment 2: List Utilities
 * File Description: ListSet that uses ImmutableLists to implement the Set
 * interface. Methods include constructors, 
 */
 
package lists;
import io.*;

public class ListSet implements Set{
    private ImmutableList list;
    
    /*Constructor*/
    private ListSet(ImmutableList l){
        list = l.purge();
    }
    
    /*factory methods for creating or extending ImmutableLists*/
    public static ListSet ls(String s){
        if (s.length()<1)
            return new ListSet(ImmutableList.NIL);
        else
            return new ListSet( (ImmutableList.parseIntImmutableList('(' + s + ')').purge() ).sort());
    }
    /* Calls ImmutableList.nth() to return the object at the given index */
    public Object get(int index){
        return this.list.nth(index);
    }
    
    /* Calls ImmutableList.length() to return the size of the set */
    public int size(){
        return this.list.length();
    }
    
    /* Calls ImmutableList.isEmpty() to test for the empty set */
    public boolean isEmpty(){
        return this.list.isEmpty();
    }
    
    /* Calls ImmutableList.find(e) to test whether it is included in the set */
    public boolean isMember(Object e){
        return this.list.find(e).isEmpty()?
            false:
            true;
    }
    
    /* Calls ImmutableList.append and ImmutableList.purge to return the union
     * of two sets */
    public ListSet union (Set that){
        ListSet That = (ListSet) that;
        return new ListSet(this.list.append(That.list).purge().sort());
    }
    
    /* Steps through a set, comparing each object to those contained in that,
     * returning a new set of all shared objects */
    public ListSet intersection (Set that){
        ImmutableList l = ImmutableList.NIL;
        int count = this.list.length();
        int i = 0;
        while(i<count){
            Object obj = this.list.nth(i);
            if (that.isMember(obj))
                l = l.push(obj);
            i++;
        }
        return new ListSet(l.sort());
    }
    
    /* Returns a copy of a given set */
    public ListSet copy(){
        return new ListSet(this.list.sort());
    }
    
    /* Calls push to add an object to a given set */
    public void add(Object e){
        if (!this.isMember(e))
            this.list = this.list.insert((Comparable)e);
    }
    
    /* Calls ImmutableList.delete to remove a given object */
    public void remove (Object e){
        this.list = this.list.delete(e);
    }
    
    /* Constructs a ListSetIterator for a chosen ListSet */
    public Iterator iterator(){
        return new ListSetIterator(this);
    }
    
    /* Returns a new empty set */
    public ListSet empty(){
        return new ListSet(ImmutableList.NIL);
    }
    
    /* Calls ImmutableList.toString() to print the elements of a set */
    public String toString(){
        return this.list.toString();
    }
    
    public static void main(String [] args){
    }
}
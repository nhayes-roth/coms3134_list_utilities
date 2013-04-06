/* Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Course: COMSW3134
 * Assignment 2: List Utilities
 * File Description: ListSetIterator defines a class of iterators for ListSet.
 * Methods include a constructor, hasNext(), and next().
 */

package lists;

public class ListSetIterator implements Iterator{
    private int nextIndex;
    private ListSet theListSet;
    
    public ListSetIterator(ListSet ls){
        this.nextIndex = 0;
        this.theListSet = ls;
    }
        
    
    public boolean hasNext(){
        return (this.nextIndex < this.theListSet.size());
    }
    
    public Object next(){
        return this.theListSet.get(this.nextIndex++);
    }
}
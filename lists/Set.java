/* Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Course: COMSW3134
 * Assignment 2: List Utilities
 * File Description: Set interface to be implemented in ListSet 
 */

package lists;
public interface Set {
    
    public int size();
    public boolean isEmpty();
    public boolean isMember(Object e);
    public Set union(Set that);
    public Set intersection (Set that);
    public Set copy();
    public void add(Object e);
    public void remove (Object e);
    public Iterator iterator();
    public Set empty();
    
}
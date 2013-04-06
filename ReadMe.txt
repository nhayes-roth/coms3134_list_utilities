Name: Nathan Hayes-Roth
UNI: nbh2113
Course: COMSW3134 - Data Structures
Assignment2: List Utilities

Package: io
IO.java - Standard IO class provided in course resources.

Package: lists
ImmutableList.java - handles linked lists of objects. Methods include constructors, head(), tail(), isEmpty(), length(), find(x), reverse(), nth(i), delete(x), insert(x), toString(), parseIntImmutableList(str), purge(), sort, merge(list), and compareTo(list).

Iterator.java - Iterator interface to be implemented by ListSetIterator

ListSet.java - ListSet uses ImmutableLists to implement the Set interface. Methods include constructors, get(i), size(), isEmpty(), isMember(e), union(that), intersection(that), copy(), add(e), remove(e), iteratr(), empty(), and toString().

ListSetIterator.java - ListSetIterator defines a class of iterators for ListSet. Methods include a constructor, hasNext(), and next().

ListSetTest.java - On the command line, the arguments are a sequence of unsorted integers. The test class should first create an ImmutableList of the ints, then purge it, then sort it, then print it to stdout. Then, it should create a ListSet of the original ints and print it to stdout. 

Set.java - Set interface to be implemented in ListSet

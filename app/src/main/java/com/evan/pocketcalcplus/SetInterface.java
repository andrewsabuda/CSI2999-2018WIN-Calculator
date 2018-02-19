package com.evan.pocketcalcplus;


public interface SetInterface<T>
{
    /** Gets the current number of entries in this set.
     @return  The integer number of entries currently in the set. */
    public int getCurrentSize();

    /** Sees whether this set is empty.
     @return  True if the set is empty, or false if not. */
    public boolean isEmpty();

    /** Adds a new entry to this set, avoiding duplicates.
     @param newEntry  The object to be added as a new entry.
     @return  True if the addition is successful, or
     false if the item already is in the set. */
    public boolean add(T newEntry);

    /** Retrieves all entries that are in this set.
     @return  A newly allocated array of all the entries in the set. */
    public T[] toArray();

} // end SetInterface

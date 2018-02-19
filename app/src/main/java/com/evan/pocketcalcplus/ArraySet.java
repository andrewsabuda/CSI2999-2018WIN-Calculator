package com.evan.pocketcalcplus;


import java.util.ArrayList;

//ArraySet class that implements the methods defined in SetInterface
public class ArraySet<T> implements SetInterface<T> {

    private ArrayList<T> history;

    public ArraySet()
    {
        history = new ArrayList<T>();
    }

    @Override
    public boolean isEmpty() {
        return history.isEmpty();
    }

    @Override
    public boolean add(T newEntry) {
        if(history.contains(newEntry)) //no duplicates
            return false;
        else
        {
            history.add(newEntry);
            return true;
        }
    }

    public boolean contains(T anEntry) {
        return history.contains(anEntry);
    }

    @Override
    public int getCurrentSize() {
        return history.size();
    }


    /** Retrieves all entries that are in this set.
     @return  A newly allocated array of all the entries in the set. */
    @Override
    public T[] toArray() {

        T[] result = (T[])new Object[history.size()];

        for(int i = 0 ; i < history.size(); i++){

            result[i] = history.get(i);

        }

        return result;
    }


}

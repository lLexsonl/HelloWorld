/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import PriorityQueue.Entry;

/**
 * An implementation of a class maintaining a set of maximal cost-performance entries using a sorted map.
 * Maintains a database of maximal (cost,performance) pairs.
 * @author Goodrich
 */
public class CostPerformanceDatabase {
    SortedTableMap<Integer,Integer> map = new SortedTableMap<>();
    /**
     * Contructs an initially empty database.
     */
    public CostPerformanceDatabase(){}
    /**
     * Returns the (cost, performance) entry with largest cost not exceeding c.
     * (or null if no entry exist with cost c or less).
     * @param cost
     * @return 
     */
    public Entry<Integer,Integer> best(int cost){
        return map.floorEntry(cost);
    }
    /**
     * Add a new entry with given cost c and performance p.
     * @param c
     * @param p 
     */
    public void add(int c, int p){
        Entry<Integer,Integer> other = map.floorEntry(c);
        if(other != null && other.getValue() >= p)
            return;
        map.put(c, p);
        //and now remove any entries that are dominated by the new one
        other = map.higherEntry(c);
        while(other != null && other.getValue() <= p){
            map.remove(other.getKey());
            other = map.higherEntry(c);
        }
    }
}

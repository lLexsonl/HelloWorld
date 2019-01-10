/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash;

import Map.UnsortedTableMap;
import PriorityQueue.Entry;
import java.util.ArrayList;

/**
 * A concrete hash map implementation using separate chaining.
 * @author Goodrich
 * @param <K> data type of key
 * @param <V> data type of value
 */
public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    //a fixed capacity array of UnsortedTableMap that serve as bucket
    private UnsortedTableMap<K,V>[] table;      // initialized within createTable
    public ChainHashMap(){ super();}
    public ChainHashMap(int cap){ super(cap);}
    public ChainHashMap(int cap, int p){ super(cap, p);}
    /**
     * Creates an empty table having length to current capacity.
     */
    @Override
    protected void createTable(){
        table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
    }
    /**
     * Returns value associated with key k in bucket with hash value h, or else null.
     * @param h
     * @param k
     * @return 
     */
    @Override
    protected V bucketGet(int h, K k){
        UnsortedTableMap<K, V> bucket = table[h];
        if(bucket == null) return null;
        return bucket.get(k);
    }
    /**
     * Associates key k with value v in bucket with hash value h; return old value.
     * @param h
     * @param k
     * @param v
     * @return 
     */
    @Override
    protected V bucketPut(int h, K k, V v){
        UnsortedTableMap<K,V> bucket = table[h];
        if(bucket == null)
            bucket = table[h] = new UnsortedTableMap<>();
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        n += (bucket.size() - oldSize);     // size may have increased
        return answer;
    }
    /**
     * Removes entry having key k from bucket with hash value h(if any).
     * @param h
     * @param k
     * @return 
     */
    @Override
    protected V bucketRemove(int h, K k){
        UnsortedTableMap<K,V> bucket = table[h];
        if(bucket == null) return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size());     // size may have decreased
        return answer;
    }
    /**
     * Returns an iterable collection of all key-value entries of the map.
     * @return 
     */
    @Override
    public Iterable<Entry<K, V>> entrySet(){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for(int h = 0; h < capacity; h++)
            if(table[h] != null)
                for(Entry<K,V> entry : table[h].entrySet())
                    buffer.add(entry);
        return buffer;
    }
}

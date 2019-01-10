/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import PriorityQueue.Entry;
import java.util.Iterator;

/**
 *
 * @author MIPC
 * @param <K> data type of the Key.
 * @param <V> data type of the Value.
 */
public abstract class AbstractMap<K,V> implements Map<K,V> {
    @Override
    public boolean isEmpty(){ return size() == 0;}
    //<editor-fold defaultstate="collapsed" desc="MapEntry Class.">
    protected static class MapEntry<K, V> implements Entry<K, V>{
        private K k;        //Key
        private V v;        //value
        public MapEntry(K key, V value){
            k = key;
            v = value;
        }
        //<editor-fold defaultstate="collapsed" desc="Setter y Getter methods.">
        //public methods of the Entry interface.
        @Override
        public K getKey(){return k;}
        @Override
        public V getValue(){return v;}
        //utilities not exposed as part of the Entry interface.
        public void setKey(K key){k = key;}
        public V setValue(V value){
            V old = v;
            v = value;
            return old;
        }
        //</editor-fold>
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="KeyIterator class.">
    private class KeyIterator implements Iterator<K>{
        private Iterator<Entry<K,V>> entries = entrySet().iterator();
        @Override
        public boolean hasNext(){ return entries.hasNext();}
        @Override
        public K next(){ return entries.next().getKey();}
        @Override
        public void remove(){throw new UnsupportedOperationException();}
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="KeyIterable class.">
    private class KeyIterable implements Iterable<K>{
        @Override
        public Iterator<K> iterator(){return new KeyIterator();}
    }
    //</editor-fold>
    @Override
    public Iterable<K> keySet(){return new KeyIterable();}
    //<editor-fold defaultstate="collapsed" desc="ValueIterator class.">
    private class ValueIterator implements Iterator<V>{
        private Iterator<Entry<K, V>> entries = entrySet().iterator();
        @Override
        public boolean hasNext(){ return entries.hasNext();}
        @Override
        public V next(){ return entries.next().getValue();}
        @Override
        public void remove(){ throw new UnsupportedOperationException();}
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="ValueIterable class.">
    private class ValueIterable implements Iterable<V>{
        @Override
        public Iterator<V> iterator(){ return new ValueIterator();}
    }
    //</editor-fold>
    @Override
    public Iterable<V> values(){ return new ValueIterable();}
}

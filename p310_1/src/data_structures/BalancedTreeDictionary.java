package data_structures;
/**
 *  Program # 4
 *   The Balanced Tree is a generic Data Structure that takes any object of type Comparable and stores them
 *  via their key into a balanced tree structure. Objects are directly accessed by their key. This Balanced Tree is
 *  implemented using java's Treemap API.
 *  CS310
 *  4/19/2020
 *  @author Logan Wang cssc1278
 */
import java.util.*;

public class BalancedTreeDictionary <K extends Comparable<K>,V extends Comparable<V>> implements DictionaryADT<K,V>{
    TreeMap<K,V> tree;
    int modCount;

    public BalancedTreeDictionary(){
        tree = new TreeMap<>();
        modCount =0;
    }

    @Override
    public boolean put(K key, V value) {
        tree.put(key,value);
        modCount++;
        return true;
    }

    @Override
    public boolean delete(K key) {
        if(tree.remove(key) == null)
            return false;
        modCount++;
        return true;
    }

    @Override
    public V get(K key) {
        return tree.get(key);
    }

    @Override
    public K getKey(V value) {
        for(Map.Entry<K,V> e : tree.entrySet()){
            if(e.getValue().compareTo(value) == 0)
                return e.getKey();
        }
        return null;
    }

    @Override
    public int size() {
        return tree.size();
    }

    //always returns false because its a tree
    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(tree.size() <= 0)
            return true;
        return false;
    }

    @Override
    public void clear() {
        tree.clear();
        modCount++;
    }

    @Override
    public Iterator<K> keys() {
        return new KeyIteratorHelper<K>();
    }

    @Override
    public Iterator<V> values() {
        return new ValueIteratorHelper<V>();
    }

    abstract class IteratorHelper<V> implements Iterator<V>{
        protected Map.Entry[] nodes;
        protected int index;
        protected int modCheck;

        public IteratorHelper(){
            nodes = new Map.Entry[tree.size()];
            int j=0;
            for(Map.Entry e : tree.entrySet()){
                nodes[j++] = e;
            }
            index =0;
            modCheck = modCount;
        }

        public boolean hasNext(){
            if(modCheck!= modCount)
                throw new ConcurrentModificationException();
            return index < tree.size();
        }

        public abstract V next();

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    class ValueIteratorHelper<V> extends IteratorHelper<V> {
        public ValueIteratorHelper(){
            super();
        }

        public V next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            int tmp = index;
            index++;
            return (V)nodes[tmp].getValue();
        }
    }

    class KeyIteratorHelper<K> extends IteratorHelper<K> {
        public KeyIteratorHelper(){
            super();
        }

        public K next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            int tmp = index;
            index++;
            return (K)nodes[tmp].getKey();
        }
    }

}

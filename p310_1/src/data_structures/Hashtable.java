package data_structures;
/**
 *  Program # 4
 *   The Hashtable is a generic Data Structure that takes any object of type Comparable and stores them
 *  via their .hashcode() key. Objects are directly accessed by their hashCode.
 *  CS310
 *  4/19/2020
 *  @author Logan Wang cssc1278
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
public class Hashtable<K extends Comparable<K>,V extends Comparable<V>> implements DictionaryADT<K,V>{

    private LinkedListDS<Wrapper<K,V>>[] arr;
    private int currSize;
    private int maxSize;
    int modCounter;

    public Hashtable(int size){
        arr = new LinkedListDS[size];
        for(int i = 0; i < size; i++){
            arr[i] = new LinkedListDS<Wrapper<K,V>>();
        }
        currSize =0;
        maxSize = size;
        modCounter =0;
    }

    @Override
    public boolean put(K key, V value) {
        int index = key.hashCode() % maxSize;
        Iterator test = arr[index].iterator();
        while(test.hasNext()){
            Wrapper temp = (Wrapper)test.next();
            if(temp.key.compareTo(key)==0)
                return false;
        }
        arr[index].addLast(new Wrapper<K,V>(key,value));
        currSize++;
        modCounter++;
        return true;
    }

    @Override
    public boolean delete(K key) {
        int index = key.hashCode() % maxSize;
        Iterator test = arr[index].iterator();
        while(test.hasNext()){
            Wrapper temp = (Wrapper)test.next();
            if(temp.key.compareTo(key) == 0){
                arr[index].remove(temp);
                currSize--;
                modCounter++;
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() % maxSize;
        Iterator test = arr[index].iterator();
        while(test.hasNext()){
            Wrapper temp = (Wrapper)test.next();
            if(temp.key.compareTo(key)==0){
                return (V)temp.value;
            }
        }
        return null;
    }

    //unless keys have some specific relation to the value, must brute force through values to find respective key
    @Override
    public K getKey(V value) {
        for(int i = 0; i < maxSize;i++){
            Iterator test = arr[i].iterator();
            while(test.hasNext()){
                Wrapper temp = (Wrapper)test.next();
                if(temp.value.compareTo(value) == 0){
                    return (K)temp.key;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(currSize <= 0)
            return true;
        return false;
    }

    @Override
    public void clear() {
        modCounter++;
        currSize =0;
        for(int i = 0; i < maxSize; i++){
            arr[i] = new LinkedListDS<Wrapper<K,V>>();
        }
    }

    @Override
    public Iterator<K> keys() {
        return new KeyIteratorHelper<>();
    }

    @Override
    public Iterator<V> values() {
        return new ValueIteratorHelper<>();
    }

    private class Wrapper<K extends Comparable<K>,V extends Comparable<V>> implements Comparable<Wrapper<K,V>>{
        K key;
        V value;

        public Wrapper(K key, V value){
            this.key = key;
            this.value = value;
        }

        public int compareTo(Wrapper<K,V> node){
            return (((Comparable)key).compareTo((K)node.key));
        }
    }

     class KeyIteratorHelper<K> implements Iterator<K>{
        protected Wrapper[] nodes;
        protected int index;
        protected int modCheck;

        public KeyIteratorHelper(){
            nodes = new Wrapper[currSize];
            index =0;
            int j =0;
            modCheck = modCounter;
            for(int i = 0; i < maxSize;i++){
                for(Wrapper obj : arr[i])
                    nodes[j++] = obj;
            }
            nodes = quickSort(); //TODO
        }

        public boolean hasNext(){
            if(modCheck!= modCounter)
                throw new ConcurrentModificationException();
            return index < currSize;
        }

        public K next(){
            return (K) nodes[index++].key;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

         private Wrapper[] quickSort(){
             quickSort(0,nodes.length-1);
             return nodes;
         }

         private void quickSort(int left, int right){
             if(right - left <= 0)
                 return;
             K pivot = (K)nodes[right].key;

             int partition = getPartition(left,right,pivot);
             quickSort(left, partition-1);
             quickSort(partition +1, right);
         }

         private int getPartition(int left, int right, K pivot){
             int lPtr = left -1;
             int rPtr = right;
             while(true){
                 while (nodes[++lPtr].key.compareTo(pivot) < 0);
                 while(rPtr > 0 && nodes[--rPtr].key.compareTo(pivot) > 0);
                 if(lPtr >=rPtr)
                     break;
                 else
                     swap(lPtr,rPtr);
             }
             swap(lPtr,right);
             return lPtr;
         }

         private void swap(int one, int two){
             Wrapper temp = nodes[one];
             nodes[one] = nodes[two];
             nodes[two] = temp;
         }
    }

    class ValueIteratorHelper<V> implements Iterator<V>{
        protected Wrapper[] nodes;
        protected int index;
        protected int modCheck;

        public ValueIteratorHelper(){
            nodes = new Wrapper[currSize];
            index =0;
            int j =0;
            modCheck = modCounter;
            for(int i = 0; i < maxSize;i++){
                for(Wrapper obj : arr[i])
                    nodes[j++] = obj;
            }
            nodes = quickSort();  // TODO
        }

        public boolean hasNext(){
            if(modCheck!= modCounter)
                throw new ConcurrentModificationException();
            return index < currSize;
        }

        public V next(){
            return (V) nodes[index++].value;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        private Wrapper[] quickSort(){
            quickSort(0,nodes.length-1);
            return nodes;
        }

        private void quickSort(int left, int right){
            if(right - left <= 0)
                return;
            K pivot = (K)nodes[right].key;

            int partition = getPartition(left,right,pivot);
            quickSort(left, partition-1);
            quickSort(partition +1, right);
        }

        private int getPartition(int left, int right, K pivot){
            int lPtr = left -1;
            int rPtr = right;
            for(;;){
                while (nodes[++lPtr].key.compareTo(pivot) < 0);
                while(rPtr > 0 && nodes[--rPtr].key.compareTo(pivot) > 0);
                if(lPtr >=rPtr)
                    break;
                else
                    swap(lPtr,rPtr);
            }
            swap(lPtr,right);
            return lPtr;
        }

        private void swap(int one, int two){
            Wrapper temp = nodes[one];
            nodes[one] = nodes[two];
            nodes[two] = temp;
        }
    }
}

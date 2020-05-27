package data_structures;
/**
 *  Program # 3
 *   The Binary Heap Priority Queue is a generic Data Structure that takes any object of type Comparable and stores them
 *   in order of insertion and removes them from highest priority to lowest priority. If objects have the same priority,
 *   the data structure will remove the object that has been in the queue the longest. Other relevant methods are included
 *   such as insert, remove, delete, isFull, clear, contains, peek, and a fast fail iterator so that you can use a for each loop
 *   on the data structure.
 *  CS310
 *  4/9/2020
 *  @author Logan Wang cssc1278
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import data_structures.PriorityQueue;
public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>{
    //declare variables, currSize = current Size
    private static final int DEFAULT_MAX_CAPACITY = 1000;
    int modCounter;
    int entryNum;
    private int currSize;
    private int maxSize;
    private Wrapper[] arr;

    //constructor
    public BinaryHeapPriorityQueue( int maxSize){
        modCounter = 0;
        currSize =0;
        this.maxSize = maxSize;
        arr = new Wrapper[maxSize];
        entryNum = 0;
    }

    //default constructor
    public BinaryHeapPriorityQueue(){
        this(DEFAULT_MAX_CAPACITY);
    }

    @Override
    public boolean insert(E object) {
        if(currSize >= maxSize || currSize < 0){
            return false;
        }else{
            arr[currSize++] = new Wrapper(object);
            int newIndex = currSize -1;
            int parentIndex = findParentIndex(newIndex);
            Wrapper<E> addedValue = arr[newIndex];
            while(parentIndex >= 0 && addedValue.compareTo(arr[parentIndex]) <  0){
                arr[newIndex] = arr[parentIndex];
                newIndex = parentIndex;
                parentIndex = findParentIndex(newIndex);
            }
            arr[newIndex] = addedValue;
            entryNum++;
            modCounter++;
            return true;
        }
    }

    private int findParentIndex(int index){
        if(index -1 <0)
            return -1;
        return (index-1)/2;
    }

    @Override
    public E remove() {
        if(!isEmpty()){
            E itemRemoved = (E) arr[0].data;
            if(currSize <=1){
                modCounter++;
                currSize--;
                return itemRemoved;
            }
            arr[0] = arr[currSize-- -1];
            Wrapper temp = arr[0];
            int curr = 0;
            int child = getNextChild(curr);
            while(child!= -1 && temp.compareTo(arr[child]) > 0){
                arr[curr] = arr[child];
                curr = child;
                arr[curr] = temp;
                child = getNextChild(curr);
            }

            modCounter++;
            return itemRemoved;
        }
        return null;
    }

    private int getNextChild(int currIndex){
        int right = (currIndex << 1) + 2;
        int left = right - 1;
        if(right < currSize){
            if(arr[left].compareTo(arr[right]) < 0)
                return left;
            return right;
        }
        if(left <currSize)
            return left;
        return -1;
    }

    private int checkBranches(int currIndex, E obj){
        int sum =0;
        if(obj.compareTo((E)arr[currIndex].data) == 0)
            sum++;
        int right = (currIndex << 1) + 2;
        int left = right - 1;
        if(right < currSize){
            if(obj.compareTo((E)arr[right].data) >= 0){
                sum+= checkBranches(right,obj);
            }
        }
        if(left <currSize) {
            if(obj.compareTo((E)arr[left].data) >= 0){
                sum+= checkBranches(left,obj);
            }
        }
        return sum;
    }

    private int findIndex(int currIndex, E obj){
        int tally =0;
        if(obj.compareTo((E)arr[currIndex].data) == 0)
            return currIndex;
        int right = (currIndex << 1) + 2;
        int left = right - 1;
        if(right < currSize){
            if(obj.compareTo((E)arr[right].data) >= 0){
                tally+= findIndex(right,obj);
            }
        }
        if(left <currSize) {
            if(obj.compareTo((E)arr[left].data) >= 0){
                tally+= findIndex(left,obj);
            }
        }
        return tally;
    }

    @Override
    public boolean delete(E obj) {
        if(isEmpty())
            return false;
        if(contains(obj)){
            while(contains(obj)){
                removeSpecific(obj);
            }
            modCounter++;
            return true;
        }else
            return false;
    }

    private void removeSpecific(E obj){
        int curr = findIndex(0,obj);
        arr[curr] = arr[currSize-- -1];
        int parent = findParentIndex(curr);
        if(curr == 0 || arr[parent].compareTo(arr[curr]) < 0)
            filterDown(curr);
        else
            filterUp(curr);
    }

    private void filterDown(int c){
        Wrapper temp = arr[c];
        int child = getNextChild(c);
        while(child!= -1 && temp.compareTo(arr[child]) > 0){
            arr[c] = arr[child];
            c = child;
            arr[c] = temp;
            child = getNextChild(c);
        }
    }

    private void filterUp(int c){
        int newIndex = c;
        int parentIndex = findParentIndex(newIndex);
        Wrapper<E> addedValue = arr[newIndex];
        while(parentIndex >= 0 && addedValue.compareTo(arr[parentIndex]) <  0){
            arr[newIndex] = arr[parentIndex];
            newIndex = parentIndex;
            parentIndex = findParentIndex(newIndex);
        }
        arr[newIndex] = addedValue;
    }


    @Override
    public E peek() {
        if(isEmpty())
            return null;
        return (E) arr[0].data;
    }

    @Override
    public boolean contains(E obj) {
        if(!isEmpty()){
            if(checkBranches(0,obj) > 0)
                return true;
            return false;
        }
        return false;
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public void clear() {
        modCounter++;
        currSize =0;
        entryNum =0;
    }

    @Override
    public boolean isEmpty() {
        if(currSize <= 0)
            return true;
        return false;
    }

    @Override
    public boolean isFull() {
        if(currSize >= maxSize)
            return true;
        return false;
    }

    private class Wrapper<E> implements Comparable<Wrapper<E>>{
        int number;
        public E data;

        public Wrapper(E data){
            this.data = data;
            number = entryNum++;
        }

        public int compareTo(Wrapper<E> obj){
            if(((Comparable<E>)data).compareTo(obj.data) == 0)
                return (number - obj.number);
            return ((Comparable<E>)data).compareTo(obj.data);
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class IteratorHelper implements Iterator<E>{
        int index;
        int modificationCounter;

        public IteratorHelper(){
            modificationCounter = modCounter;
            index =0;
        }

        public boolean hasNext(){
            if(modificationCounter != modCounter)
                throw new ConcurrentModificationException();
            return index<currSize;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public E next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            int tmp = index;
            index++;
            return (E) arr[tmp].data;
        }
    }

}

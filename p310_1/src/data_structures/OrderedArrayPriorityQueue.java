package data_structures;
/**
 *  Program # 1
 *  The Ordered Array Priority Queue is a generic Data Structure that takes any object of type Comparable and stores them
 *  by order of Priority and removes them from highest priority to lowest priority. If objects have the same priority,
 *  the data structure will remove the object that has been in the queue the longest. Other relevant methods are included
 *  such as insert, remove, delete, isFull, clear, contains, peek, and an iterator so that you can use a for each loop
 *  on the data structure.
 *  CS310
 *  2/4/2020
 *  @author Logan Wang cssc1278
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import data_structures.PriorityQueue;
public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>{
    private E[] arr;
    private static final int DEFAULT_MAX_CAPACITY = 1000;
    private int currSize;
    private int maxSize;

    public OrderedArrayPriorityQueue(int maxSize){
        currSize = 0;
        this.maxSize = maxSize;
        arr = (E[]) new Comparable[maxSize];
    }

    public OrderedArrayPriorityQueue(){
        this(DEFAULT_MAX_CAPACITY);
    }

    @Override
    public boolean insert(E object) {
        if(currSize < maxSize){
            int index = fLSISP(object,0,currSize-1);
            //shifts array so that there are no empty spaces
            for(int i = currSize-1; i >= index;i--){
                arr[i+1] = arr[i];
            }
            arr[index] = object;
            currSize++;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public E remove() {
        if(isEmpty())
            return null;
        return arr[--currSize];
    }

    @Override
    public boolean delete(E obj) {
        if(!contains(obj)){
            return false;
        }
        int initial = fLSISP(obj,0,currSize);
        int finalInstance = fFISP(obj,0,currSize);
       // int numOccurances = finalInstance - initial +1;
        int numOccurances = finalInstance - initial;
        currSize -= numOccurances;
        for(int i = initial; i < currSize;i++){
            arr[i] = arr[i+numOccurances];
          //  arr[i+numOccurances] = arr[i];
        }
        return true;
    }

    @Override
    public E peek() {
        if(isEmpty())
            return null;
        int temp = currSize-1;
        return arr[temp];
    }

    @Override
    public boolean contains(E obj) {
        if(currSize > 0){
            int temp = findIndexSpecific(obj,0,currSize);
            if( temp > -1 && temp < currSize)
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
        currSize =0;
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

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    private int fLSISP(E obj, int low, int high){ //Find Last Item of Same Priority (first occurance in array)
        if(low > high){
            return low;
        }
        int mid = (low + high)/2;
        if(((Comparable<E>)obj).compareTo(arr[mid]) >= 0)
            return fLSISP(obj,low,mid-=1);
        return fLSISP(obj,mid+=1,high);
    }

    private int fFISP(E obj, int low, int high){ //Find First Item of Same Priority (last occurance in array)
        if(low > high){
            return low;
        }
        int mid = (low + high)/2;
        if(((Comparable<E>)obj).compareTo(arr[mid]) <= 0)
            return fFISP(obj,mid+=1,high);
        return fFISP(obj,low,mid-=1);
    }

    private int findIndexSpecific(E obj, int low, int high){
        if(low > high && low <maxSize && high < maxSize){
            return -1;
        }
        int mid = (low + high)/2;
        if(obj.compareTo(arr[mid]) == 0)
            return mid;
        else if (((Comparable<E>)obj).compareTo(arr[mid]) >= 0)
            return findIndexSpecific(obj,low,mid-=1);
        return findIndexSpecific(obj,mid+=1,high);
    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "Priority Queue is empty";
        }
        String temp = "";
        for(int i =0; i<currSize;i++){
            temp += arr[i].toString() + ", ";
        }
        return temp.substring(0,temp.length() -2);
    }

    class IteratorHelper implements Iterator<E>{
        int index;

        public IteratorHelper(){
            index =0;
        }

        public boolean hasNext(){
            return index <currSize;
        }

        public void remove(){
            if(index < currSize){
                for(int j = index+1; j<currSize;j++){
                    arr[index++] = arr[j];
                    arr[j] = null;
                }
                currSize--;
            }
        }

        public E next(){
            if(!hasNext()){
                return null; //todo return null or throw new NoSuchELementException
            }
            int tmp = index;
            index++;
            return arr[tmp];
        }
    }
}

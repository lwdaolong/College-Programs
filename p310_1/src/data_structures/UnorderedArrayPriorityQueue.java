package data_structures;
/**
 *  Program # 1
 *   The Unordered Array Priority Queue is a generic Data Structure that takes any object of type Comparable and stores them
 *   in order of insertion and removes them from highest priority to lowest priority. If objects have the same priority,
 *   the data structure will remove the object that has been in the queue the longest. Other relevant methods are included
 *   such as insert, remove, delete, isFull, clear, contains, peek, and an iterator so that you can use a for each loop
 *   on the data structure.
 *  CS310
 *  2/4/2020
 *  @author Logan Wang cssc1278
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import data_structures.PriorityQueue;
public class UnorderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>{
    //declare variables, arr = Generic array, currSize = pointer to size of how many elements in array, maxSize = max
    //size
    private E[] arr;
    private static final int DEFAULT_MAX_CAPACITY = 1000;
    private int currSize;
    private int maxSize;

    @SuppressWarnings("unchecked")
    //modular constructor, input for maxSize
    public UnorderedArrayPriorityQueue(int maxSize){
        currSize =0;
        this.maxSize = maxSize;
        arr = (E[]) new Comparable[maxSize];
    }

    //default constructor
    public UnorderedArrayPriorityQueue(){
        this(DEFAULT_MAX_CAPACITY);
    }

    @Override
    public boolean insert(E object) {
        if(currSize >= maxSize || currSize < 0){
            return false;
        }else{
            arr[currSize++] = object;
            return true;
        }
    }

    @Override
    public E remove() {
        if(isEmpty()){
            return null;
        }else{
            Comparable min = arr[0];
            int y =0;
            //locates object to be removed
            for(int i = 1; i <currSize; i++){
                if(min.compareTo(arr[i]) > 0){
                    min = (Comparable) arr[i];
                    y =i;
                }
            }
            //once object located, reorganizes array (pushes each element to the left to fill up empty space made
            //by shifting object)
            for(int j = y+1; j<currSize;j++){
                arr[y++] = arr[j];
                arr[j] = null;
            }
            currSize--;
            return (E) min;
        }
    }

    @Override
    public boolean delete(E obj) {
        if(!contains(obj)){
            return false;
        }
        /*
        int temp = findSpecific(obj);
        while(temp > -1){
            removeIndex(temp);
            temp = findSpecific(obj);
        }*/
        UnorderedArrayPriorityQueue<E> temp = new UnorderedArrayPriorityQueue<E>(maxSize);
        for(int i =0; i < currSize; i++){
            if(arr[i].compareTo(obj)!=0){
                temp.insert(arr[i]);
            }
        }
        clear();
        Iterator<E> iter = temp.iterator();
        for(int i =0; i < temp.size(); i++){
                arr[i] = iter.next();
                currSize++;
        }
        return true;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }else{
            Comparable min = arr[0];
            //locates object of highest priority that has been in the queue the longest
            for(int i = 1; i <currSize; i++){
                if(min.compareTo(arr[i]) > 0)
                    min = (Comparable) arr[i];
            }
            return (E) min;
        }
    }

    @Override
    public boolean contains(E obj) {
            for(int i = 0; i <currSize; i++){
                if(obj.compareTo(arr[i])==0)
                    return true;
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

    private int findSpecific(E obj){ //finds oldest instance of specific object
        if(!isEmpty()){
            for(int i = 0; i <currSize; i++){
                if(obj.equals(arr[i]))
                    return i;
            }
        }
        return -1;
    }

    private void removeSpecific(E obj){
        int index = findSpecific(obj);
        if(index >-1){
            for(int j = index+1; j<currSize;j++){
                arr[index++] = arr[j];
                arr[j] = null;
            }
            currSize--;
        }
    }

    private void removeIndex(int a){
        if(a > -1 && a < currSize){
            for(int j = a+1; j<currSize;j++){
                arr[a++] = arr[j];
                arr[j] = null;
            }
            currSize--;
        }
    }

    //todo
    //todo ASK IF U NEED THIS
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
            removeIndex(index);
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

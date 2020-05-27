package data_structures;
/**
 *  Program # 2
 *   TODO desc.
 *  CS310
 *  3/9/2020
 *  @author Logan Wang cssc1278
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import data_structures.PriorityQueue;
public class UnorderedLinkedListPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>{
    //declare variables, currSize = current Size, head = start of LL, tail = end of LL, modCounter for fastfail Iterator
    private static final int DEFAULT_MAX_CAPACITY = 1000;
    int modCounter;
    private int currSize;
    private Node<E> head;
    private Node<E> tail;

    @SuppressWarnings("unchecked")
    //constructor
    public UnorderedLinkedListPriorityQueue(){
        modCounter =0;
        currSize =0;
        head = tail = null;
    }

    @Override
    public boolean insert(E object) {
        Node<E> temp = new Node<E>(object);
        if(isEmpty())
            tail = temp;
        temp.next = head;
        head = temp;
        currSize++;
        modCounter++;
        return true;
    }

    @Override
    public E remove() {
        Node<E> prev = null;
        Node<E> current = head;
        Node<E> next = null;
        Node<E> temp = null;
        if(head == null){
            return null;
        }else if(head.next == null){
            temp = head;
            head = tail =null;
            currSize--;
            modCounter++;
            return temp.data;
        }
        temp = head;
        while(current.next != null){
            if(temp.data.compareTo(current.next.data) >= 0){
                prev = current;
                next = current.next.next;
                temp = current.next;
            }
            current = current.next;
        }
        if (prev == null){
            head = head.next;
            currSize--;
            modCounter++;
            return temp.data;
        }else if (temp !=  null && temp.next == null){
            tail = prev;
            prev.next = null;
            currSize--;
            modCounter++;
            return temp.data;
        }else{
            prev.next = temp.next;
            currSize--;
            modCounter++;
            return temp.data;
        }
    }

    @Override
    public boolean delete(E obj) {
        if (isEmpty()) {
            return false;
        } else if(!contains(obj)) {
            return false;
        }else{
                Node<E> prev = null;
                Node<E> current = head;

            while (contains(obj))
            {
                while(current!=null){
                    if(current.data.compareTo(obj)==0){
                        if(current.equals(head)){
                            head = head.next;
                            currSize--;
                            modCounter++;
                        }else if(current.next == null){
                            tail = prev;
                            prev.next = null;
                            currSize--;
                            modCounter++;
                        }else{
                            while(current != null && current.data.compareTo(obj) == 0){
                                prev.next = current.next;
                                currSize--;
                                modCounter++;
                                current = current.next;
                            }

                            if(current == null){
                                tail = prev;
                                prev.next = null;
                            }
                        }
                    }
                    prev = current;
                    if(current!=null)
                        current=current.next;
                }
            }
            return true;
            }
        }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }else{
            Node<E> current = head;
            Node<E> temp = head;
            while(current.next != null){
                if(current.data.compareTo(current.next.data) >= 0){
                    temp = current.next;
                }
                current = current.next;
            }
            return temp.data;
        }
    }

    @Override
    public boolean contains(E obj) {
        Node<E> current = head;
        while(current != null){
            if(current.data.compareTo(obj) == 0)
                return true;
            current = current.next;
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
        head=tail=null;
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
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class IteratorHelper implements Iterator<E>{
        Node<E> nodePtr;
        int index;
        int modificationCounter;

        public IteratorHelper(){
            nodePtr = head;
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
            E temp = nodePtr.data;
            nodePtr = nodePtr.next;
            index++;
            return temp;
        }
    }

        private class Node<E>{
            E data;
            Node<E> next;
            public Node(E data){
                this.data = data;
                next = null;
            }
        }
}

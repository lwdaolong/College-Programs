package data_structures;
/**
 *  Program # 4
 *   The Binary Search Tree is a generic Data Structure that takes any object of type Comparable and stores them
 *  via their key onto a binary search tree structure. Objects are directly accessed by their key.
 *  CS310
 *  4/19/2020
 *  @author Logan Wang cssc1278
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BinarySearchTree<K extends Comparable<K>,V extends Comparable<V>> implements DictionaryADT<K,V>{
    private int currSize;
    int modCounter;
    private Node<K,V> root;
    private boolean lastNodeWasKey;

    public BinarySearchTree(){
        currSize =0;
        modCounter =0;
        root = null;
    }


    @Override
    public boolean put(K key, V value) {
        if(root == null){
            root = new Node(key,value);
            modCounter++;
            currSize++;
            return true;
        }
        else{
            return insert(key,value,root,null,false);
        }
    }

    private boolean insert(K k, V v, Node n, Node parent, boolean wasLeft){

        if(n == null){
            if(wasLeft)
                parent.leftChild = new Node(k,v,parent);
            else
                parent.rightChild = new Node(k,v,parent);
            currSize++;
            modCounter++;
            return true;
        }else if(k.compareTo((K)n.key) == 0)
            return false;
        else if(k.compareTo((K)n.key) < 0)
            return insert(k,v,n.leftChild,n,true);
        else
            return insert(k,v,n.rightChild,n,false);
    }

    @Override
    public boolean delete(K key) {
        if(isEmpty())
            return false;
        Node deleteNode = findNode(key,root);
        if(deleteNode == null)
            return false;
        else{
            if(deleteNode.leftChild != null && deleteNode.rightChild !=null){ //node to delete has 2 children
                Node[] successorarr = new Node[1];
                inOrderNext(key, root, successorarr);
                Node successor = successorarr[0];
                if(deleteNode.parent == null) { //deleting root case
                    if(key.compareTo((K)successor.parent.key) ==0 ){ //parent of successor is root
                        successor.leftChild = successor.parent.leftChild;
                        successor.leftChild.parent = successor;
                        successor.parent = null;
                        root = successor;
                    }else{ // successor is left child of parent
                        successor.parent.leftChild = successor.rightChild;
                        if(successor.rightChild != null)
                            successor.rightChild.parent = successor.parent;
                        successor.leftChild = root.leftChild;
                        successor.rightChild = root.rightChild;
                        successor.rightChild.parent = successor;
                        successor.leftChild.parent = successor;
                        successor.parent = null;
                        root = successor;
                    }
                }else{ //deleting intermediate node case // set parent
                    //determine which side its on
                    if(key.compareTo((K)successor.parent.key) ==0 ){ //parent of successor is deleted
                        successor.leftChild = successor.parent.leftChild;
                        successor.leftChild.parent = successor;
                        successor.parent = successor.parent.parent;
                    }else{ // successor is left child of parent
                        successor.parent.leftChild = successor.rightChild;
                        if(successor.rightChild != null)
                            successor.rightChild.parent = successor.parent;
                        successor.leftChild = deleteNode.leftChild;
                        successor.rightChild = deleteNode.rightChild;
                        successor.parent = deleteNode.parent;
                    }
                    if(deleteNode.parent.rightChild!=null && deleteNode.parent.rightChild.equals(deleteNode))
                        successor.parent.rightChild = successor; //determine which side its on
                    else
                        successor.parent.leftChild = successor;
                }
            }else if(deleteNode.leftChild != null|| deleteNode.rightChild !=null){ //node to delete has 1 child
                if(deleteNode.parent == null){
                    if (deleteNode.leftChild != null) {
                        root = deleteNode.leftChild;
                        deleteNode.leftChild.parent = null;
                    }else{
                        root = deleteNode.rightChild;
                        deleteNode.rightChild.parent = null;
                    }
                }else if(deleteNode.parent.leftChild != null && deleteNode.parent.leftChild.equals(deleteNode)){
                    if (deleteNode.leftChild != null) {
                        deleteNode.parent.leftChild = deleteNode.leftChild;
                        deleteNode.leftChild.parent = deleteNode.parent;
                    }else{
                        deleteNode.parent.leftChild = deleteNode.rightChild;
                        deleteNode.rightChild.parent = deleteNode.parent;
                    }
                }else{
                    if (deleteNode.leftChild != null) {
                        deleteNode.parent.rightChild = deleteNode.leftChild;
                        deleteNode.leftChild.parent = deleteNode.parent;
                    }else{
                        deleteNode.parent.rightChild = deleteNode.rightChild;
                        deleteNode.rightChild.parent = deleteNode.parent;
                    }
                }
            }else { //node to delete has no children
                if (deleteNode.equals(root)) //todo  might need to use .compareTO but who knows
                    root = null;
                else if (deleteNode.parent.leftChild!= null && deleteNode.parent.leftChild.equals(deleteNode)) {
                        deleteNode.parent.leftChild = null;
                }else{
                    deleteNode.parent.rightChild = null;
                }
            }

            currSize--;
            modCounter++;
            return true;
        }
    }

    private void inOrderNext(K key, Node n, Node[] list){
        if(n != null){
            inOrderNext(key, n.leftChild,list);
            if (lastNodeWasKey){
                list[0] = n;
                lastNodeWasKey = false;
                return;
            }
            if(key.compareTo((K)n.key) == 0){
                lastNodeWasKey = true;
            }
            inOrderNext(key,n.rightChild,list);
        }
    }

    private Node findNode(K key, Node n){
        if(n == null)
            return null;
        if(key.compareTo((K)n.key) < 0)
            return findNode(key,n.leftChild);
        if(key.compareTo((K)n.key) > 0)
            return findNode(key,n.rightChild);
        return n;
    }

    @Override
    public V get(K key) {
        return find(key,root);
    }

    private V find(K key, Node n){
        if(n == null)
            return null;
        if(key.compareTo((K)n.key) < 0)
            return (V)find(key,n.leftChild);
        if(key.compareTo((K)n.key) > 0)
            return (V)find(key,n.rightChild);
        return (V)n.value;
    }

    @Override
    public K getKey(V value) {
        K[] list = (K[]) new Comparable[1];
        list[0] = null;
        inOrderTraversalHelper(value, root,list);
        return list[0];
    }

    private void inOrderTraversalHelper(V value, Node n, K[] list){
        if(n != null){
            inOrderTraversalHelper(value, n.leftChild,list);
            if(value.compareTo((V)n.value) == 0){
                list[0] = (K)n.key;
                return;
            }
            inOrderTraversalHelper(value,n.rightChild,list);
        }
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
        root =null;
        currSize =0;
    }

    @Override
    public Iterator<K> keys() {
        return new KeyIteratorHelper<>();
    }

    @Override
    public Iterator<V> values() {
        return new ValueIteratorHelper<V>();
    }

    abstract class IteratorHelper<V> implements Iterator<V>{
        protected Node[] nodes;
        protected int index;
        protected int j;
        protected int modCheck;

        public IteratorHelper(){
            nodes = new Node[currSize];
            index =0;
            j =0;
            modCheck = modCounter;
            inOrderTraversal(root,nodes);
        }

        public boolean hasNext(){
            if(modCheck!= modCounter)
                throw new ConcurrentModificationException();
            return index < currSize;
        }

        public abstract V next();

        public void remove(){
            throw new UnsupportedOperationException();
        }

        private void inOrderTraversal( Node n,  Node[] list){
            if(n != null){
                inOrderTraversal( n.leftChild,list);
                list[j++] = n;
                inOrderTraversal(n.rightChild,list);
            }
        }
    }

    class ValueIteratorHelper<V> extends IteratorHelper<V>{
        public ValueIteratorHelper(){
            super();
        }

        public V next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return (V) nodes[index++].value;
        }
    }

    class KeyIteratorHelper<K> extends IteratorHelper<K>{
        public KeyIteratorHelper(){
            super();
        }

        public K next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            int tmp = index;
            index++;
            return (K) nodes[tmp].key;
        }
    }

    private class Node<K,V>{
        private K key;
        private V value;
        private Node<K,V> parent;
        private Node<K,V> leftChild;
        private Node<K,V> rightChild;

        public Node(K k, V v){
            key =k;
            value = v;
            leftChild = null;
            rightChild = null;
            parent =null;
        }
        public Node(K k, V v, Node p){
            key =k;
            value = v;
            leftChild = null;
            rightChild = null;
            parent =p;
        }
    }

}

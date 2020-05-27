import data_structures.*;

import java.util.Iterator;

public class BSTDriver {

    public static void main(String[] args){
       // DictionaryADT bst = new BinarySearchTree();
        DictionaryADT bst = new BalancedTreeDictionary();


        bst.put(5,5);
        bst.put(2,2);
        bst.put(1,1);
        bst.put(50,50);
        bst.put(30,30);
        bst.put(20,20);
        bst.put(40,40);
        bst.put(70,70);
        bst.put(60,60);
        bst.put(80,80);
        bst.put(51,51);
        bst.put(52,52);
        bst.put(79,79);



        bst.delete(5);
        System.out.println(bst.getKey(70));
        Iterator keyIter = bst.keys();
        Iterator valIter = bst.values();
        while(keyIter.hasNext())
            System.out.println(keyIter.next());

        System.out.println("---------------");

        while(valIter.hasNext())
            System.out.println(valIter.next());
    }





}

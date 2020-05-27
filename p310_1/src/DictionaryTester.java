import data_structures.*;

import java.util.Iterator;
import java.util.Random;
public class DictionaryTester {
    public static void main(String[] args){

        DictionaryADT bst = new Hashtable(10);

        System.out.println("Test 1,  4 elements---------------------------------------------");
        System.out.println(bst.put("3","charlie"));
        System.out.println(bst.put("4","delta"));

        System.out.println(bst.put("2","bravo"));
        System.out.println(bst.put("1","alpha"));

        System.out.println(bst.get("2"));
        System.out.println(bst.getKey("gamma"));
        System.out.println();
        System.out.println(bst.size());
        System.out.println(bst.isFull());
        System.out.println(bst.isEmpty());
        printKeys(bst);
        printValues(bst);

       System.out.println("Test 1,  0 elements---------------------------------------------");
        bst.clear();
        System.out.println(bst.get("2"));
        System.out.println(bst.getKey("gamma"));
        System.out.println(bst.size());
        System.out.println(bst.isFull());
        System.out.println(bst.isEmpty());
        printKeys(bst);
        printValues(bst);

        /*
        System.out.println("Test 1,  deleting elements---------------------------------------------");
        System.out.println(bst.put("1","alpha"));
        System.out.println(bst.put("2","bravo"));
        System.out.println(bst.put("3","charlie"));
        System.out.println(bst.put("4","delta"));
        System.out.println(bst.size());
        System.out.println(bst.isFull());
        System.out.println(bst.isEmpty());
        bst.delete("2");
        bst.delete("3");
        System.out.println(bst.size());
    //    printKeys((Hashtable)bst);
    //    printValues((Hashtable)bst);

        System.out.println("Test 1,  getting elements---------------------------------------------");
        System.out.println(bst.put("1","alpha"));
        System.out.println(bst.put("2","bravo"));
        System.out.println(bst.put("3","charlie"));
        System.out.println(bst.put("4","delta"));
        System.out.println(bst.size());
        System.out.println(bst.isFull());
        System.out.println(bst.isEmpty());
        System.out.println(bst.get("2"));
        System.out.println(bst.get("3"));
        System.out.println(bst.size());
     //   printKeys((Hashtable)bst);
     //   printValues((Hashtable)bst);

        System.out.println("Test 1,  getting Key of elements---------------------------------------------");
        System.out.println(bst.put("1","alpha"));
        System.out.println(bst.put("2","bravo"));
        System.out.println(bst.put("3","charlie"));
        System.out.println(bst.put("4","delta"));
        System.out.println(bst.size());
        System.out.println(bst.isFull());
        System.out.println(bst.isEmpty());
        System.out.println(bst.getKey("charlie"));
        System.out.println(bst.getKey("delta"));
        System.out.println(bst.size());
     //   printKeys((Hashtable)bst);
     //   printValues((Hashtable)bst);

     */
    }


    public static void printKeys(DictionaryADT hashTable){
        Iterator iter = hashTable.keys();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    public static void printValues(DictionaryADT hashTable){
        Iterator iter = hashTable.values();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}

/*

*/

/*
       DictionaryADT hashTable = new Hashtable(10);

        System.out.println("Test 1,  4 elements---------------------------------------------");
        System.out.println(hashTable.put("1","alpha"));
        System.out.println(hashTable.put("2","bravo"));
        System.out.println(hashTable.put("3","charlie"));
        System.out.println(hashTable.put("4","delta"));
        System.out.println(hashTable.size());
        System.out.println(hashTable.isFull());
        System.out.println(hashTable.isEmpty());
        printKeys((Hashtable)hashTable);
        printValues((Hashtable)hashTable);

        System.out.println("Test 1,  0 elements---------------------------------------------");
        hashTable.clear();
        System.out.println(hashTable.size());
        System.out.println(hashTable.isFull());
        System.out.println(hashTable.isEmpty());
        printKeys((Hashtable)hashTable);
        printValues((Hashtable)hashTable);

        System.out.println("Test 1,  deleting elements---------------------------------------------");
        System.out.println(hashTable.put("1","alpha"));
        System.out.println(hashTable.put("2","bravo"));
        System.out.println(hashTable.put("3","charlie"));
        System.out.println(hashTable.put("4","delta"));
        System.out.println(hashTable.size());
        System.out.println(hashTable.isFull());
        System.out.println(hashTable.isEmpty());
        hashTable.delete("2");
        hashTable.delete("3");
        System.out.println(hashTable.size());
        printKeys((Hashtable)hashTable);
        printValues((Hashtable)hashTable);

        System.out.println("Test 1,  getting elements---------------------------------------------");
        System.out.println(hashTable.put("1","alpha"));
        System.out.println(hashTable.put("2","bravo"));
        System.out.println(hashTable.put("3","charlie"));
        System.out.println(hashTable.put("4","delta"));
        System.out.println(hashTable.size());
        System.out.println(hashTable.isFull());
        System.out.println(hashTable.isEmpty());
        System.out.println(hashTable.get("2"));
        System.out.println(hashTable.get("3"));
        System.out.println(hashTable.size());
        printKeys((Hashtable)hashTable);
        printValues((Hashtable)hashTable);

        System.out.println("Test 1,  getting Key of elements---------------------------------------------");
        System.out.println(hashTable.put("1","alpha"));
        System.out.println(hashTable.put("2","bravo"));
        System.out.println(hashTable.put("3","charlie"));
        System.out.println(hashTable.put("4","delta"));
        System.out.println(hashTable.size());
        System.out.println(hashTable.isFull());
        System.out.println(hashTable.isEmpty());
        System.out.println(hashTable.getKey("charlie"));
        System.out.println(hashTable.getKey("delta"));
        System.out.println(hashTable.size());
        printKeys((Hashtable)hashTable);
        printValues((Hashtable)hashTable);


 */
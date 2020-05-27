import data_structures.*;

import java.util.Random;
import java.util.*;

public class dictionaryEmpiricalTimer {
    public static void main(String [] args) {
        int iterations = 10;  // number of timing tests
        int workSize = 10000;   // size of add/remove cycle loop
        int structureSize = 100;  // initial size of PQ, doubles with each iteration
        int loopStructureSize = structureSize; // helper var



        long start = 0, stop = 0;
        long [] insertTimes = new long[iterations]; // arrays to hold results
        long [] cycleTimes = new long[iterations];
        long [] removeTimes = new long[iterations];
        long [] searchTimes = new long[iterations];

        DictionaryADT<Integer,Integer> pq =
                   new BalancedTreeDictionary();
                 //  new BinarySearchTree();
              //  new Hashtable<>(5242800);

        for(int i=0; i < iterations; i++) {
            // build structure first
            int [] data = new int[structureSize];
            pq.clear();
            start = System.currentTimeMillis();   // capture time to build
            for(int j=0; j < structureSize; j++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                pq.put(x,x);
                data[j] = x;
            }
            stop = System.currentTimeMillis();
            insertTimes[i] = (stop-start);

            // time for add/remove cycles
            start = System.currentTimeMillis();
            for(int j=0; j < workSize; j++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                pq.put(x,x);
                pq.delete(x);
            }
            stop = System.currentTimeMillis();
            cycleTimes[i] = (stop-start);

            // time for search operations
            start = System.currentTimeMillis();
         //   for(int x =0; x < structureSize;x++){
            Random rand = new Random();
            int wahtever= (int)(Math.random()*structureSize);
                pq.get(data[wahtever]); //list values when adding to arraylist, delete

           // }
            stop = System.currentTimeMillis();
            searchTimes[i] = (stop-start);
            structureSize <<= 1;
            System.out.println("Loop " + (i+1) + " of " + iterations + " finished.");



            // time for removal (dequeue) operations
            start = System.currentTimeMillis();
          //  for(int x =0; x < structureSize;x++){
            int testestes = (int)(Math.random()*structureSize/2);
                pq.delete(data[testestes]); //list values when adding to arraylist, delete

         //   }
            stop = System.currentTimeMillis();
            removeTimes[i] = (stop-start);
            structureSize <<= 1;
            System.out.println("Loop " + (i+1) + " of " + iterations + " finished.");
        }

        // print results
        int tmp = loopStructureSize;
        System.out.println("\nINSERTION TIMES:");
        for(int i=0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + insertTimes[i]);
            tmp <<= 1;
        }
        tmp = loopStructureSize;
        System.out.println("\nADD/DELETE CYCLE TIMES:");
        for(int i=0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + cycleTimes[i]);
            tmp <<= 1;
        }
        tmp = loopStructureSize;
        System.out.println("\nSEARCH TIMES:");
        for(int i=0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + searchTimes[i]);
            tmp <<= 1;
        }
        tmp = loopStructureSize;
        System.out.println("\nRemoval TIMES:");
        for(int i=0; i < iterations; i++) {
            System.out.println("n=" + tmp + "  Time: " + removeTimes[i]);
            tmp <<= 1;
        }
    }
}

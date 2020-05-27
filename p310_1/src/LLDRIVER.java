import java.util.Iterator;
        import data_structures.*;
        import java.util.Random;

public class LLDRIVER {
    public static void main(String[] args) {

/*        UnorderedLinkedListPriorityQueue<Integer> test1 = new UnorderedLinkedListPriorityQueue<>();
       testUnorderedLinkedListPriorityQueue(test1);*/
                OrderedLinkedListPriorityQueue<Integer> test2 = new OrderedLinkedListPriorityQueue<>();
                testOrderedLinkedListPriorityQueue(test2);
            }
            public static void testUnorderedLinkedListPriorityQueue(UnorderedLinkedListPriorityQueue list){
                list.insert(3);
                list.insert(4);
                list.insert(5);
                list.insert(6);
                list.insert(7);
                list.insert(8);
                System.out.println("This should print '3 4 5 6':");
                System.out.println(list.remove());
                System.out.println(list.remove());
                System.out.println(list.remove());
                System.out.println(list.remove());
                System.out.println("Should print ' 8 7 ':");
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("Should print '7':");
                System.out.println(list.peek());
                list.clear();
                System.out.println("This should print nothing:");
                for(Object e: list){
                    System.out.print(e + " ERROR ");
                }
                list.insert(11);
                list.insert(22);
                list.insert(11);
                list.insert(63);
                list.insert(11);
                list.insert(11);
                list.insert(11);
                list.insert(11);
                list.insert(11);
                list.insert(11);
                list.insert(11);
                list.insert(72);
                list.insert(11);
                list.insert(11);
                System.out.println();
                System.out.println("This should print '11 11 72 11 11 11 11 11 11 11 63 11 22 11': ");
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("This should print 'true':");
                System.out.println(list.delete(11));
                System.out.println("This should print '72 63 22':");
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("This should print '3':");
                System.out.println(list.size());
                list.clear();
                System.out.println("This should print '0': " + list.size());
                Random rand = new Random();
                int num;
                System.out.println("Inserting:");
                for(int i = 0; i < 30; i++){
                    num = rand.nextInt(1000);
                    list.insert(num);
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("Objects in list: ");
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("Removing objects (should be in order): ");
                int loop = list.size();
                for(int i = 0; i < loop; i++){
                    System.out.println("Item: " + list.remove() + " Size: " + list.size());
                }



            }

            public static void testOrderedLinkedListPriorityQueue(OrderedLinkedListPriorityQueue list){

                list.insert(1);
                list.insert(2);
                list.insert(8);
                list.insert(4);
                list.insert(3);
                list.insert(6);
                list.insert(10);
                System.out.println("This should print '1 2 3 4 6 8 10':");
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("There should be only trues here:");
                for(Object e: list){
                    System.out.print(list.contains((Comparable)e) + " ");
                }
                list.clear();
                System.out.println();
                Random rand = new Random();
                for(int i = 0; i < 10; i++){
                    list.insert(rand.nextInt(1000));
                }
                System.out.println("These should all be in order:");
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("This should print the elements in the array:");
                int size = list.size();
                for(int x =0; x < size; x++){
                    System.out.print(list.remove() + " ");
                }
                System.out.println();
                System.out.println("This should print nothing 0:");
                System.out.println(list.size());
                list.insert(1);
                list.insert(55);
                list.insert(93);
                list.insert(55);
                list.insert(2);
                list.insert(52);
                list.insert(55);
                list.insert(10);
                list.insert(42);
                list.insert(55);
                list.insert(23);
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("deleting 23's:");
                System.out.println(list.delete(23));
                for(Object e: list){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("List size:");
                System.out.println(list.size());

            }

        }

//// PriorityQueue<Integer> pq = new OrderedLinkedListPriorityQueue<>();
//        PriorityQueue<Item> pq = new UnorderedLinkedListPriorityQueue<>();
//        pq.insert(new Item(5,0));
//        pq.insert(new Item(5,1));
//        pq.insert(new Item(5,2));
//        pq.insert(new Item(3,3));
//        pq.insert(new Item(3,4));
//        pq.insert(new Item(3,5));
//        pq.insert(new Item(3,6));
//        pq.insert(new Item(3,7));
//
//      /*  for(int i =0; i< 5;i++){
//            printList(pq);
//            System.out.println("---------------------");
//            System.out.println("REMOVED: " + pq.remove());
//            System.out.println("SIZE: " + pq.size());
//            System.out.println("---------------------");
//
//
//                  for(int i = 0; i < size;i++){
//          System.out.println(pq.remove());
//      }
//        }
//
//       */
//        printList(pq);
//
//        System.out.println("---------------------");
//        System.out.println("deleting priority 3");
//        pq.delete(new Item(5,0));
//        System.out.println(pq.size());
//        printList(pq);
//
//        pq.clear();
//        pq.insert(new Item(5,0));
//        pq.insert(new Item(5,1));
//        pq.insert(new Item(5,2));
//        pq.insert(new Item(3,3));
//        pq.insert(new Item(3,4));
//        pq.insert(new Item(3,5));
//        pq.insert(new Item(3,6));
//        pq.insert(new Item(3,7));
//        System.out.println("---------------------");
//        System.out.println("Replaced list, deleting priority 5");
//        pq.delete(new Item(3,0));
//        System.out.println(pq.size());
//        printList(pq);
//
//
//
//
//
//    }
//
//
//    public static <E extends Comparable<E>> void printList(PriorityQueue<E> pq){
//        Iterator<E> it = pq.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next().toString());
//        }
//
//    }
//
//    static class Item implements Comparable<Item>  {
//        public int priority;
//        public int sequence;
//
//        public Item(int p, int s) {
//            priority = p;
//            sequence = s;
//        }
//
//        public int compareTo(Item i) {
//            return priority - i.priority;
//        }
//
//        public String toString() {
//            return "Priority: " + priority + "   Sequence: " + sequence;
//        }
//    }
//    public void Test1(){
//        // UnorderedLinkedListPriorityQueue<Integer> test = new UnorderedArrayPriorityQueue<Integer>();
//
//
//    }
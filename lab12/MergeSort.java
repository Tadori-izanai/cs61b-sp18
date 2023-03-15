import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> returnVal = new Queue<>();
        for (Item x : items) {
            Queue<Item> singleItemQueue = new Queue<>();
            singleItemQueue.enqueue(x);
            returnVal.enqueue(singleItemQueue);
        }
        return returnVal;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> returnVal = new Queue<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            returnVal.enqueue(getMin(q1, q2));
        }
        while (!q1.isEmpty()) {
            returnVal.enqueue(q1.dequeue());
        }
        while (!q2.isEmpty()) {
            returnVal.enqueue(q2.dequeue());
        }

        return returnVal;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.isEmpty()) {
            return new Queue<>();
        }
        Queue<Queue<Item>> queues = makeSingleItemQueues(items);
        while (queues.size() > 1) {
            Queue<Item> q1 = queues.dequeue();
            Queue<Item> q2 = queues.dequeue();
            queues.enqueue(mergeSortedQueues(q1, q2));
        }
        return queues.peek();
    }

    public static void main(String[] args) {
        Queue<String> touhou = new Queue<>();
        touhou.enqueue("Leimu");
        touhou.enqueue("Marisa");
        touhou.enqueue("Cirno");
        touhou.enqueue("Lumia");
        touhou.enqueue("Suwako");
        touhou.enqueue("Sanae");
        touhou.enqueue("Koishi");
        touhou.enqueue("Satori");
        touhou.enqueue("Yokari");
        touhou.enqueue("Chen");

        System.out.println(touhou);
        Queue<String> touhouSorted = MergeSort.mergeSort(touhou);
        System.out.println(touhou);
        System.out.println(touhouSorted);
    }
}

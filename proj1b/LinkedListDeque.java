public class LinkedListDeque<T> implements Deque<T> {
    // non-static nested
    public class TNode {
        private TNode prev;
        private T item;
        private TNode next;

        public TNode(TNode prev, T item, TNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }


    private TNode sentinel;     // sb node
    private int size;


    /**
     * The constructor: creates an empty linked list deque.
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * Adds an item to the front of the deque.
     * @param item item to be inserted
     */
    @Override
    public void addFirst(T item) {
        TNode p = new TNode(sentinel, item, sentinel.next);
        p.next.prev = p;
        p.prev.next = p;
        size++;
    }

    /**
     * Adds an item to the end of the deque.
     * @param item item to be inserted
     */
    @Override
    public void addLast(T item) {
        TNode p = new TNode(sentinel.prev, item, sentinel);
        p.prev.next = p;
        p.next.prev = p;
        size++;
    }

    /**
     * @return ture if deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (0 == size);
    }

    /**
     * @return the number of the items in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the numbers in the deque (from first to last).
     */
    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Empty deque.");
            return;
        }
        for (TNode iter = sentinel.next; iter != sentinel; iter = iter.next) {
            System.out.print(iter.item + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * @return the item at the front of the deque
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return first;
    }

    /**
     * Removes and returns the item at the end of the deque.
     * @return the item at the end of the deque
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return last;
    }

    /**
     * Gets the item at the given index. (iterative version)
     * @param index 0 is front, 1 is the next item ...
     * @return the item of the node of index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;    // no such item
        }
        TNode iter = sentinel.next;
        while (index > 0) {
            iter = iter.next;
            index--;
        }
        return iter.item;
    }

    /**
     * Gets the item at the given index. (recursive version)
     * @param index 0 is front, 1 is the next item ...
     * @return the item of the node of index
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;    // no such item
        }
        if (0 == index) {
            return sentinel.next.item;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
    private T getRecursiveHelper(TNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> d = new LinkedListDeque<>();
//        d.addLast(10);
//        d.addFirst(5);
//        d.addLast(15);
//        d.printDeque();
//
//        System.out.println("d.getRecursive(1) = " + d.getRecursive(1));
//        System.out.println("d.get(1) = " + d.get(1));
//
//        d.removeLast();
//        d.printDeque();
//    }
}

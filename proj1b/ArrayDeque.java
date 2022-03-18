public class ArrayDeque<T> implements Deque<T> {
    private final int INITIAL_SIZE = 8;
    private final int FACTOR = 2;
    private final double R = 0.25;

    private int front;      // nextFirst
    private int rear;       // last
    private T[] items;
    private int size;


    // constructor
    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[INITIAL_SIZE];
        rear = items.length - 1;
        front = items.length - 1;
    }

    @Override
    public void addFirst(T item) {
        resize();
        items[front] = item;
        front = (items.length + front - 1) % items.length;  // 考虑环回
        size++;
    }

    @Override
    public void addLast(T item) {
        resize();
        rear = (rear + 1) % items.length;                   // 考虑换回
        items[rear] = item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (0 == size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        front = (front + 1) % items.length;
        T first = items[front];
        items[front] = null;        // null out
        resize();
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T last = items[rear];
        items[rear] = null;         // null out
        rear = (rear - 1 + items.length) % items.length;
        resize();
        return last;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;            // no such item
        }
        return items[(front + index + 1) % items.length];
    }


    // resize
    private void resize() {
        if (size == items.length) {
            resizeHelper(items.length * FACTOR);    // expand
        }
        if (size < items.length * R && items.length > 8) {
            resizeHelper(items.length / FACTOR);    // reduce
        }
    }

    private void resizeHelper(int newSize) {
        T[] temp = (T []) new Object[newSize];
        for (int index = 0; index < size; index++) {
            temp[index] = get(index);
        }
        items = temp;
        rear = size - 1;
        front = items.length - 1;
    }
}

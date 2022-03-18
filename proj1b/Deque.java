public interface Deque<T> {
    // 提示: public 的修饰符是多余的
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}

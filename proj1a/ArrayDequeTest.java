public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();

        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (i % 3 == 0) {
                a.addFirst(count++);
            } else {
                a.addLast(count++);
            }
        }

        a.printDeque();

        for (int i = 0; i < 30; i++){
            if (i % 3 == 0) {
                a.removeLast();
            } else {
                a.removeFirst();
            }
        }

        a.printDeque();
        System.out.println(a.get(0));
    }
}

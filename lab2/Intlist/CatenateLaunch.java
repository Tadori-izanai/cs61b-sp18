public class CatenateLaunch {
    public static void main(String[] args) {
        IntList testA = IntList.of(1, 2, 3);
        IntList testB = IntList.of(4, 5, 6);
        System.out.println(IntList.catenate(testA, testB));
    }
}

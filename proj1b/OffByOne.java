public class OffByOne implements CharacterComparator {
    @Override   // 对程序的功能没有影响, 但是最好写上其
    public boolean equalChars(char x, char y) {
        return (x - y == 1 || y - x == 1);
    }
}

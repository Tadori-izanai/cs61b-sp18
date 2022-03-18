import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
//    /*
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String[] inputs = {"foo", "", "a", "dad", "oo"};
        boolean[] expected = {false, true, true, true, true};
        for (int i = 0; i < 5; i++) {
            assertEquals(palindrome.isPalindrome(inputs[i]), expected[i]);
        }
        // 也可以利用 assertFalse/assertTrue, 如 assertFalse(palindrome.isPalindrome("cat"));
    }

    @Test
    public void testPalindrome() {
        CharacterComparator cc = new OffByOne();
        String[] inputs = {"this", "", "a", "yz", "xyz"};
        boolean[] expected = {true, true, true, true, false};
        for (int i = 0; i < 5; i++) {
            assertEquals(palindrome.isPalindrome(inputs[i], cc), expected[i]);
        }
    }


//    Uncomment this class once you've created your Palindrome class. */
}

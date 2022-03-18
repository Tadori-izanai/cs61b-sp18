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
            assertEquals(palindrome.iaPalindrome(inputs[i]), expected[i]);
        }
        // 也可以利用 assertFalse/assertTrue, 如 assertFalse(palindrome.isPalindrome("cat"));
    }


//    Uncomment this class once you've created your Palindrome class. */
}

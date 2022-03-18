import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testPalindrome() {
        CharacterComparator cc = new OffByN(2);
        String[] inputs = {"bad", "", "a", "zx", "wxyz"};
        boolean[] expected = {true, true, true, true, false};
        for (int i = 0; i < 5; i++) {
            assertEquals(palindrome.isPalindrome(inputs[i], cc), expected[i]);
        }
    }
}

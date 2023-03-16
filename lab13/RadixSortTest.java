import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class RadixSortTest {
    public static void main(String[] args) {
        String[] touhou = new String[] {
            "Leimu",
            "Marisa",
            "Cirno",
            "Lumia",
            "Suwako",
            "Sanae",
            "Koishi",
            "Satori",
            "Yokari",
            "Chen",
            "Kirisame Marisa",
            "",
            "Hakurei Reimu"
        };

        System.out.println(Arrays.toString(touhou));

        String[] touhouSorted = RadixSort.sort(touhou);
        System.out.println(Arrays.toString(touhouSorted));

        touhouSorted = RadixSort.sortMSD(touhou);
        System.out.println(Arrays.toString(touhouSorted));
    }

    @Test
    public void testSortLSD() {
        String[] touhou = new String[] {
            "Leimu",
            "Marisa",
            "Cirno",
            "Lumia",
            "Suwako",
            "Sanae",
            "Koishi",
            "Satori",
            "Yokari",
            "Chen",
            "Kirisame Marisa",
            "",
            "Hakurei Reimu"
        };

        String[] touhouSorted = RadixSort.sort(touhou);
        Arrays.sort(touhou);
        assertArrayEquals(touhouSorted, touhou);
    }

    @Test
    public void testSortMSD() {
        String[] touhou = new String[] {
            "Leimu",
            "Marisa",
            "Cirno",
            "Lumia",
            "Suwako",
            "Sanae",
            "Koishi",
            "Satori",
            "Yokari",
            "Chen",
            "Kirisame Marisa",
            "",
            "Hakurei Reimu"
        };

        String[] touhouSorted = RadixSort.sortMSD(touhou);
        Arrays.sort(touhou);
        assertArrayEquals(touhouSorted, touhou);
    }
}

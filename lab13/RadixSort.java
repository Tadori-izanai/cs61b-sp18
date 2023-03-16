/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int N = asciis.length;
        String[] results = new String[N];
        System.arraycopy(asciis, 0, results, 0, N);

        int maxLength = 0;
        for (String s : asciis) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }

        for (int i = 0; i < N; i += 1) {
            StringBuilder sb = new StringBuilder(results[i]);
            for (int k = 0; k < maxLength - results[i].length(); k += 1) {
                sb.append('\u0000');
            }
            results[i] = sb.toString();
        }

        for (int i = maxLength - 1; i >= 0; i -= 1) {
            sortHelperLSD(results, i);
        }

        for (int i = 0; i < N; i += 1) {
            results[i] = results[i].trim();
        }

        return results;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int R = 256;
        int[] count = new int[R];
        int[] starts = new int[R];

        String[] asciisCopy = new String[asciis.length];
        System.arraycopy(asciis, 0, asciisCopy, 0, asciis.length);

        for (String s : asciisCopy) {
            count[(int)(s.charAt(index))] += 1;
        }
        int ind = 0;
        for (int i = 0; i < R; i += 1) {
            starts[i] = ind;
            ind += count[i];
        }

        for (String s : asciisCopy) {
            int k = (int)(s.charAt(index));
            asciis[starts[k]] = s;
            starts[k] += 1;
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort

        // first, swap those who are shorter than index+1 to the front
        int numShort = 0;
        for (int i = start; i < end; i += 1) {
            if (asciis[i].length() - 1 < index) {
                String tmp = asciis[i];
                asciis[i] = asciis[start + numShort];
                asciis[start + numShort] = tmp;
                numShort += 1;
            }
        }
        start += numShort;
        if (start == end) {
            return;
        }

        int n = end - start;
        int R = 256;
        int[] cnt = new int[R];
        int[] starts = new int[R];

        String[] asciisCopy = new String[n];
        System.arraycopy(asciis, start, asciisCopy, 0, n);

        for (String s : asciisCopy) {
            cnt[(int)(s.charAt(index))] += 1;
        }
        for (int i = 0, ind = 0; i < R; i += 1) {
            starts[i] = ind;
            ind += cnt[i];
        }

        for (String s : asciisCopy) {
            int k = (int)(s.charAt(index));
            asciis[start + starts[k]] = s;
            cnt[k] += 1;
        }

        // do sorting in each bucket
        for (int i = 0, ind = 0; i < R; i += 1) {
            if (cnt[i] > 1) {
                sortHelperMSD(asciis, start + ind, start + ind + cnt[i], index + 1);
            }
            ind += cnt[i];
        }
    }

    public static String[] sortMSD(String[] asciis) {
        int N = asciis.length;
        String[] results = new String[N];
        System.arraycopy(asciis, 0, results, 0, N);

        sortHelperMSD(results, 0, N, 0);
        return results;
    }
}

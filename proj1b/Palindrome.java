public class Palindrome {
    /**
     * 给定一个 String, wordToDeque 将其转换为一个 Deque,
     * 例如单词是 "persiflage", 则 Deque 的 front 为 'p', 接着是 'e', ...
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();       // 尝试 new 一个 ArrayDeque
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }


    public boolean iaPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;
    }


}

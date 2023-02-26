import java.util.*;

public class WordSum {
    private static final Map<Character, Integer> DIGITS = Map.of(
            'ZERO', 0, 'ONE', 1, 'TWO', 2, 'THREE', 3, 'FOUR', 4,
            'FIVE', 5, 'SIX', 6, 'SEVEN', 7, 'EIGHT', 8, 'NINE', 9
    );

    public static boolean hasWordSum(String[] words, String result) {
        int sum = 0;
        for (String word : words) {
            int value = 0;
            for (char ch : word.toCharArray()) {
                value = value * 10 + DIGITS.get(ch);
            }
            sum += value;
        }
        int target = 0;
        for (char ch : result.toCharArray()) {
            target = target * 10 + DIGITS.get(ch);
        }
        return sum == target;
    }

    public static void main(String[] args) {
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        System.out.println(hasWordSum(words, result));  // true
    }
}
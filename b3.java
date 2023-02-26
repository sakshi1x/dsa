public static boolean patternMatch(String a, String pattern) {
        if (a == null || pattern == null) {
        return false;
        }

        int i = 0, j = 0;
        int aLen = a.length(), pLen = pattern.length();

        while (i < aLen && j < pLen) {
        char aChar = a.charAt(i);
        char pChar = pattern.charAt(j);

        if (pChar == '@') {
        // Match entire sequence of characters
        int nextJ = j + 1;
        if (nextJ == pLen) {
        // '@' is the last character in the pattern
        return true;
        }

        char nextPChar = pattern.charAt(nextJ);
        int k = i;
        while (k < aLen && a.charAt(k) != nextPChar) {
        k++;
        }
        if (k == aLen) {
        // The character after '@' is not found in the string
        return false;
        }

        // Recursive call with the remaining pattern and string
        return patternMatch(a.substring(k + 1), pattern.substring(nextJ + 1));
        } else if (pChar == '#') {
        // Match any single character
        i++;
        j++;
        } else {
        // Match the same character
        if (aChar != pChar) {
        return false;
        }
        i++;
        j++;
        }
        }

        // Return true if both the string and pattern are fully traversed
        return i == aLen && j == pLen;
        }
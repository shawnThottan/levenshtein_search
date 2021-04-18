package com.shawnthottan.searcher;

public class Util {
    public static Integer getLevenshteinDistance(String str1, String str2) {
        // Finds the longer string and the shorter string.
        String longerStr = str1.length() >= str2.length() ? str1 : str2;
        String shorterStr = str1.length() < str2.length() ? str1 : str2;

        // Calculates the lengths of the strings.
        int longerLen = longerStr.length();
        int shorterLen = shorterStr.length();
        int halfShorterLen = shorterLen / 2;

        // Creates a character Array charArr
        // If the longer string is `abcde` and the shorter string is `abcd`
        // CharArr will be `  abcde  `
        // added 2 spaces(half the length of the shorter string) on both sides of the longer string.
        int arrLen = longerLen + 2 * halfShorterLen;
        char[] charArr = new char[arrLen];
        for (int i = 0; i < arrLen; i++) {
            if (i < halfShorterLen) { charArr[i] = ' '; }
            else if (i < halfShorterLen + longerLen) { charArr[i] = longerStr.charAt(i - halfShorterLen); }
            else { charArr[i] = ' '; }
        }

        // max levenshtein distance is the length of the longer string
        // min levenshtein distance is the difference of the character count of the strings.
        int minLD = longerLen - shorterLen;

        // assume the levenshtein Distance to be maximum at first.
        int levenshteinDistance = longerLen;

        // for each position in the charArr
        for (int i = 0; i < arrLen - shorterLen; i++) {
            // start with assigning the minimum LD to dis
            int dis = minLD;
            for (int j = 0; j < shorterLen; j++) {
                // when the characters are different we add 1 to dis
                if (charArr[i + j] != shorterStr.charAt(j)) { dis++; }
                // if the character is a space, add 1 again.
                if (charArr[i + j] == ' ') { dis ++; }
                // when dis is greater than the existing best distance, we break from the loop.
                if (dis >= levenshteinDistance) { break; }
            }
            // if the final dis is less than the levenshtein distance we have so far, we keep it.
            if (dis < levenshteinDistance) { levenshteinDistance = dis; }
        }

        // we return the least error count we get from running the loops.
        return levenshteinDistance;
    }
}

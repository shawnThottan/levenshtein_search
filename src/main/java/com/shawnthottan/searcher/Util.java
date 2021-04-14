package com.shawnthottan.searcher;

public class Util {
    public static Integer getLevenshteinDistance(String str1, String str2) {
        // Finds the longer string and the shorter string.
        String longerStr = str1.length() >= str2.length() ? str1 : str2;
        String shorterStr = str1.length() < str2.length() ? str1 : str2;

        // Calculates the lengths of the strings.
        int longerLen = longerStr.length();
        int halfShorterLen = shorterStr.length() / 2;

        // Creates a character Array
        // If the longer string is `abcde` and the shorter string is `abcd`
        // CharArr will be `  abcde  `
        // half the length of the shorter string(2) spaces on both sides of the longer string.
        int arrLen = longerLen + 2 * halfShorterLen;
        char[] charArr = new char[arrLen];
        for (int i = 0; i < arrLen; i++) {
            if (i < halfShorterLen) { charArr[i] = ' '; }
            else if (i < halfShorterLen + longerLen) { charArr[i] = longerStr.charAt(i - halfShorterLen); }
            else { charArr[i] = ' '; }
        }

        // max levenshtein distance is the length of the longer string
        int max = longerStr.length();
        int levenshteinDistance = max;

        // Finds the maximum number of common characters
        // subracts it from the max;
        for (int i = 0; i < longerLen; i++) {
            int sameCharCount = 0;
            for (int j = 0; j < shorterStr.length(); j++) {
                if (charArr[i + j] == shorterStr.charAt(j)) { sameCharCount++; }
            }
            int dis = max - sameCharCount;
            if (dis < levenshteinDistance) { levenshteinDistance = dis; }
        }

        return levenshteinDistance;
    }
}

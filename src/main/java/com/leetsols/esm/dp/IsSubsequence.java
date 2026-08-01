package com.leetsols.esm.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IsSubsequence {
    String source, target;
    Integer leftBound, rightBound;

    public boolean recSubsequence(int leftIdx, int rightIdx) {
        if (leftIdx == leftBound) return true;
        if (rightIdx == rightBound) return false;

        if (source.charAt(leftIdx) == target.charAt(rightIdx)) ++leftIdx;
        ++rightIdx;

        return recSubsequence(leftIdx, rightIdx);
    }

    public boolean isSubsequence(String s, String t) {
        this.source = source;
        this.target = target;
        this.leftBound = s.length();
        this.rightBound = t.length();

        return recSubsequence(0, 0);
    }

    public boolean isSubsequenceIter(String s, String t) {
        int leftBound = s.length(), rightBound = t.length();
        int pLeft = 0, pRight = 0;

        while (pLeft < leftBound && pRight < rightBound) {
            if (s.charAt(pLeft) == t.charAt(pRight)) pLeft += 1;
            pRight += 1;
        }

        return pLeft == leftBound;
    }

    public boolean isSubsequenceHashtable(String s, String t) {
        var map = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) map.get(t.charAt(i)).add(i);
            else {
                var list = new ArrayList<Integer>();
                list.add(i);
                map.put(t.charAt(i), list);
            }
        }

        int currMaxIdx = -1;
        for (char ch : s.toCharArray()) {
            if (!map.containsKey(ch)) return false;

            boolean isMatched = false;

            for (int i : map.get(ch)) {
                if (currMaxIdx < i) {
                    currMaxIdx = i;
                    isMatched = true;
                    break;
                }
            }

            if (!isMatched) return false;
        }
        return true;
    }

    public boolean isSubsequenceDP(String s, String t) {
        int sourceLen = s.length(), targetLen = t.length();

        if (sourceLen == 0) return true;

        int[][] dp = new int[sourceLen + 1][targetLen + 1];

        for (int col = 1; col < targetLen; col++) {
            for (int row = 1; row < sourceLen; row++) {
                if (s.charAt(row - 1) == t.charAt(col - 1)) dp[row][col] = dp[row - 1][col - 1] + 1;
                else dp[row][col] = Math.max(dp[row][col - 1], dp[row - 1][col]);
            }

            if (dp[sourceLen][col] == sourceLen) return true;
        }

        return false;
    }
}

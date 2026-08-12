package com.leetsols.esm.dp;

import java.util.Arrays;
import java.util.HashMap;

public class MatchsticksToSquare {
    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;

        int totalSum = 0;

        for (int num : matchsticks) totalSum += num;

        if (totalSum % 4 != 0) return false;

        Arrays.sort(matchsticks);
        reverse(matchsticks);

        int k = totalSum / 4;

        char[] taken = new char[n];
        Arrays.fill(taken, '0');

        var memo = new HashMap<String, Boolean>();
        return backtrack(matchsticks, 0, 0, 0, 4, k, taken, memo);
    }

    public boolean backtrack(int[] arr, int count, int currSum, int k, int targetSum, boolean[] taken) {
        int n = arr.length;

        if (count == k - 1) return true;

        if (currSum > targetSum) return false;

        if (currSum == targetSum) return backtrack(arr, count + 1, 0, k, targetSum, taken);

        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                taken[i] = true;

                if (backtrack(arr, count, currSum + arr[i], k, targetSum, taken)) return true;

                taken[i] = false;
            }
        }
        return false;
    }

    private boolean backtrack(int[] arr, int index, int count, int currSum, int k, int targetSum, char[] taken, HashMap<String, Boolean> memo) {
        int n = arr.length;

        if (count == k - 1) return true;
        if (currSum > targetSum) return false;

        String takenStr = new String(taken);

        if (memo.containsKey(takenStr)) return memo.get(takenStr);

        if (currSum == targetSum) {
            boolean ans = backtrack(arr, 0, count + 1, 0, k, targetSum, taken, memo);
            memo.put(takenStr, ans);
            return ans;
        }

        for (int i = index; i < n; i++) {
            if (taken[i] == '0') {
                taken[i] = '1';
                if (backtrack(arr, i + 1, count, currSum + arr[i], k, targetSum, taken, memo)) return true;
                taken[i] = '0';
            }
        }

        memo.put(takenStr, false);
        return false;
    }

    void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

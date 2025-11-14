package com.leetsols.esm.dp;

import java.util.HashMap;
import java.util.Map;

public class BrainPower {
    Map<Integer, Long> memo = new HashMap<>();

    public long mostPoints(int[][] questions) {
        return dp(0, questions);
    }

    public long dp(int i, int[][] questions) {
        if (i >= questions.length) {
            return 0;
        }

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        int j = i + questions[i][1] + 1;
        memo.put(i, Math.max(questions[i][0] + dp(j, questions), dp(i + 1, questions)));
        return memo.get(i);
    }
}

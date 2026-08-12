package com.leetsols.esm.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfWaysToWearHat {
    int[][] memo;
    int n;
    int done;
    private static final int MOD = 1_000_000_007;
    Map<Integer, ArrayList<Integer>> hatsToPeople;

    public int numberWays(List<List<Integer>> hats) {
        int size = hats.size();

        List<Integer>[] hatToPeople = new List[41];
        for (int i = 1; i < 40; i++) hatToPeople[i] = new ArrayList<>();

        for (int person = 0; person < size; person++) {
            for (int hat : hats.get(person)) hatToPeople[hat].add(person);
        }

        int totalMasks = 1 << size;
        int[] dp = new int[totalMasks];
        dp[0] = 1;

        for (int hat = 1; hat <= 40; hat++) {
            for (int mask = totalMasks - 1; mask >= 0; mask--) {
                if (dp[mask] == 0) continue;

                for (int person : hatToPeople[hat]) {
                    if ((mask & (1 << person)) == 0) {
                        int nextMask = mask | (1 << person);
                        dp[nextMask] = (dp[nextMask] + dp[mask]) % MOD;
                    }
                }
            }
        }

        return dp[totalMasks - 1];
    }

    /*
     * Algorithm:
     * - Intuitive Approach:
     *  - Assign hats to people, iterate through the person 0 -> N - 1, pick an
     *    available hat for each
     *  - State(person_idx, used_hats_mask)
     *  - There are 40 hats, used_hats_mask limits to 40 bits
     * - Inverted Approach:
     *  - Assign people to hats, iterate through the hat 1 -> 40
     *  - State(hat_idx, assigned_people_mask)
     *  - 2 ^ 10 people, mask will require 10 bits (2 ^ 10 = 1024)
     *
     * - Create an adjacency list
     *  - map[hat] = people_prefer_this_hat
     * - Create a target bitmask
     *  - done = 2 ^ n - 1, all lower bits are all 1
     * - Single mask, stores the status of all N people using binary bit positions
     *  - Example: N = 4, mask = 0101 -> per_1 = 1, per_2 = 0, per_3 = 1, per_4 = 0
     * - Check if the person [p] is available:
     *  - if ((mask & (1 << person)) == 0)
     *   - mask = 5
     *   - 1 << 1 -> 0010
     *   - Bitwise AND
     *   - 0101
     *   - 0010
     *   - 0000 -> Person 1 is free
     * - Assign hat to person p
     *  - int nextMask = mask | (1 << person)
     *  - 0101
     *  - 0010
     *  - 0111 -> person 0, 1, 2 assigned
     *
     * DP part:
     * - dp(hat, mask)
     *  - How many ways can we give hats to the remaining unassigned people using hats
     *    from hat up to 40?
     *  - dp(hat + 1, mask) -> skip hat
     *  - dp(hat + 1, mask | (1 << person)) -> give the hat to the eligible person
     * - Base Cases:
     *  - if (mask == done) return 1 -> all people received a hat
     *  - if (hat > 40) return 0     -> checked all 40 hats, but mask != done
     *  - if (memo[hat][mask] != -1) -> returns previously checked result
     *
     */
    public int numberWaysDP(List<List<Integer>> hats) {
        n = hats.size();

        hatsToPeople = new HashMap<>();
        // Adjacency list, hat -> person
        for (int i = 0; i < n; i++) {
            for (int hat : hats.get(i)) {
                if (!hatsToPeople.containsKey(hat)) {
                    hatsToPeople.put(hat, new ArrayList<>());
                }
                hatsToPeople.get(hat).add(i);
            }
        }

        done = (int) Math.pow(2, n) - 1;
        memo = new int[41][done];

        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                memo[i][j] = -1;
            }
        }

        return dp(1, 0);
    }

    private int dp(int hat, int mask) {
        if (mask == done) return 1;
        if (hat > 40) return 0;
        if (memo[hat][mask] != -1) return memo[hat][mask];

        int ans = dp(hat + 1, mask);

        if (hatsToPeople.containsKey(hat)) {
            for (int person : hatsToPeople.get(hat)) {
                if ((mask & (1 << person)) == 0) {
                    ans = (ans + dp(hat + 1, mask | (1 << person))) % MOD;
                }
            }
        }

        memo[hat][mask] = ans;
        return ans;
    }
}

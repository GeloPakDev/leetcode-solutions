package com.leetsols.esm.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteAndEarn {
    private HashMap<Integer, Integer> points = new HashMap<>();
    private HashMap<Integer, Integer> cache = new HashMap<>();

    public int deleteAndEarn(int[] nums) {
        int maxNumber = 0;
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + 1);
            maxNumber = Math.max(maxNumber, num);
        }
        return maxPoints(maxNumber);
    }

    public int dfs(List<Integer> currList, int currSum, Map<Integer, Integer> map) {
        if (currList.isEmpty()) return currSum;

        int maxPoints = currSum;

        for (int i = 0; i < currList.size(); i++) {
            int curr = currList.get(i);
            int pointsEarned = curr * map.get(curr);

            int prev = curr - 1;
            int next = curr + 1;

            var nextList = new ArrayList<>(currList);

            currList.remove(Integer.valueOf(curr));
            currList.remove(Integer.valueOf(prev));
            currList.remove(Integer.valueOf(next));

            maxPoints = Math.max(maxPoints, dfs(nextList, currSum + pointsEarned, map));
        }
        return maxPoints;
    }

    /*
     * Approach:
     * - Take or don't take
     *  - Take [x]
     *   - gain the points == x * number of times [x] occurs in nums
     *   - By taking [x], [x - 1] cannot be taken, largest number for consideration [x - 2].
     *   - Most points you can take [gain + maxPoints(x - 2)]
     *  - Don't take [x]
     *   - Most points you can take [maxPoints(x - 1)]
     * - Recurrence relation
     *  - maxPoints(x) = Math.max(maxPoints(x - 1), maxPoints(x - 2) + gain)
     * - Base Case
     *  - maxPoints(0) -> 0
     *  - maxPoints(1) -> 1
     */
    public int maxPoints(int num) {
        if (num == 0) return 0;
        if (num == 1) return points.getOrDefault(1, 0);
        if (cache.containsKey(num)) return cache.get(num);

        int gain = points.getOrDefault(num, 0);
        cache.put(num, Math.max(maxPoints(num - 1), maxPoints(num - 2) + gain));
        return cache.get(num);
    }

    /*
     * maxPoints[i] -> stores the maximum points we can gain if we consider numbers from
     *                 0 to num
     */
    public int deleteAndEarnBottomUp(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        int maxNumber = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }

        int[] maxPoints = new int[maxNumber + 1];
        maxPoints[1] = points.getOrDefault(1, 0);

        for (int num = 2; num < maxPoints.length; num++) {
            int gain = points.getOrDefault(num, 0);
            maxPoints[num] = Math.max(maxPoints[num - 1], maxPoints[num - 2] + gain);
        }
        return maxPoints[maxNumber];
    }

    public int deleteAndEarnOptimized(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + num);

        var list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        int twoBack = 0;
        int oneBack = map.get(list.getFirst());

        for (int i = 1; i < list.size(); i++) {
            int currElement = list.get(i);
            int temp = oneBack;

            if (currElement == list.get(i - 1) + 1) {
                oneBack = Math.max(oneBack, twoBack + map.get(currElement));
            } else {
                oneBack += map.get(currElement);
            }

            twoBack = temp;
        }

        return oneBack;
    }
}

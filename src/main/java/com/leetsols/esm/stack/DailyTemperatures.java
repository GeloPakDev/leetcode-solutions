package com.leetsols.esm.stack;

import java.util.Stack;

public class DailyTemperatures {
    /*
     * Description:
     * Return an array answer such that answer[i] is the number of days you have to wait
     * after the [i]th day to get a warmer temperature. If there is no future day for which
     * this is possible, keep answer[i] == 0 instead.
     *
     * Input:  [73,74,75,71,69,72,76,73]
     * Output: [1,1,4,2,1,1,0,0]
     *
     * Algorithm:
     * - Use of the Monotonic Decreasing Stack
     * - Store the indices of the days.
     *
     * Core Logic:
     * - If the current day is not warmer than top of the stack
     *   push it to the stack.
     * - If the current day is warmer than top of the stack, it means that this
     *   is the first warmer day. At this moment we can pop the elements from
     *   the stack until they are lower than current one, thus for each that day we
     *   can calculate how many days are apart from the current warmest one just found
     *
     *  |------------------------------------------------------|
     *  | while (temperatures[stack.peek] < temperatures[i]) { |
     *  |   int j = stack.pop()                                |
     *  |   result[j] = i - j;                                 |
     *  | }                                                    |
     *  |------------------------------------------------------|
     */
    public int[] dailyTemperatures(int[] temperatures) {
        var stack = new Stack<Integer>();
        var res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int j = stack.pop();
                res[j] = i - j;
            }
            stack.push(i);
        }
        return res;
    }
}

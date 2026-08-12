package com.leetsols.esm.greedy;

public class GasStation {
    /*
     * Algorithm:
     * - Count total sum of the gas and cost.
     * - Check if (totalGas < totalCost) -> true -> return -1.
     * - Calculate the diff array to calculate where gas can go negative.
     * - Calculate the currSum to check that it can go negative
     * - If (currSum < 0) -> currSum = 0 && res = i + 1;
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;

        for (int num : gas) totalGas += num;
        for (int num : cost) totalCost += num;

        if (totalGas < totalCost) return -1;

        int[] diff = new int[cost.length];
        for (int i = 0; i < cost.length; i++) diff[i] = gas[i] - cost[i];

        int currSum = 0;
        int res = 0;

        for (int i = 0; i < diff.length; i++) {
            currSum += diff[i];

            if (currSum < 0) {
                currSum = 0;
                res = i + 1;
            }
        }
        return res;
    }
}

package com.leetsols.esm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i] = new int[]{capital[i], profits[i]};
        }

        /*
         * - Sort the projects by capital
         */
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        int i = 0;

        /*
         * - Iterate over the K-th projects
         */
        for (int j = 0; j < k; j++) {
            /*
             * - Go over the most affordable projects locally
             * - Finding the project with maximum profit at each step
             */
            while (i < n && projects[i][0] <= w) {
                heap.add(projects[i][1]);
                i++;
            }

            if (heap.isEmpty()) {
                return w;
            }

            // After iterated the most affordable projects, the most profitable one will be chosen
            w += heap.remove();
        }
        return w;
    }
}


package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TimeToInformAllEmployees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        var graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                graph.get(manager[i]).add(i);
            }
        }

        var queue = new LinkedList<int[]>();
        queue.add(new int[]{headID, 0});

        int sum = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            var currEmp = curr[0];
            var currTime = curr[1];

            int informedTime = currTime + informTime[currEmp];
            sum = Math.max(sum, informedTime);

            for (int subordinate : graph.get(currEmp)) {
                queue.add(new int[]{subordinate, informedTime});
            }
        }
        return sum;
    }
}

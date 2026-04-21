package com.leetsols.esm.queue;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int m;
    int n;

    public void wallsAndGates(int[][] rooms) {
        m = rooms.length;
        if (m == 0) return;
        n = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == 0) queue.add(new int[]{row, col});
            }
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : directions) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (isInvalid(r, c, rooms)) continue;
                rooms[r][c] = rooms[row][col] + 1;
                queue.add(new int[]{r, c});
            }
        }
    }

    public boolean isInvalid(int row, int col, int[][] rooms) {
        return row < 0 || col < 0 || row >= m || col >= n || rooms[row][col] != Integer.MAX_VALUE;
    }
}
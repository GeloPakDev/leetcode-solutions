package com.leetsols.esm.backtracking;

import com.leetsols.esm.hashing.Pair;

import java.util.HashSet;
import java.util.Set;

/*
 * Algorithm:
 * - As the robot moves, mark cell as visited
 * - Go forward, clean, mark all the cells on the way as visited.
 * - At the obstacle turn right and go forward
 * - If after turning right there is still an obstacle, turn right again
 * - Stop when you have explored all possible paths
 */
public class RobotRoomCleaner {
    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public void backtrack(int row, int col, int d) {
        visited.add(new Pair<>(row, col));
        robot.clean();

        for (int i = 0; i < 4; i++) {
            int newD = (d + i) % 4;
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];

            if (!visited.contains(new Pair<>(newRow, newCol)) && robot.move()) {
                backtrack(newRow, newCol, newD);
                goBack();
            }
            robot.turnRight();
        }
    }

    interface Robot {
        boolean move();

        void turnLeft();

        void turnRight();

        void clean();
    }
}

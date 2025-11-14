package com.leetsols.esm.greedy;

import java.util.Arrays;

public class DestroyingAsteroids {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long currMass = mass;
        for (int asteroid : asteroids) {
            if (asteroid > currMass) {
                return false;
            }
            currMass += asteroid;
        }
        return true;
    }
}
package com.leetsols.esm.binarysearch;

import java.util.Arrays;

public class BinarySearch {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int row = middle / n;
            int col = middle % n;
            int num = matrix[row][col];
            if (num == target) {
                return true;
            }
            if (num < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }

    /*
     * spells - n
     * potions - m
     * success
     * pair[spell * potion] >= success
     * return pairs with length n
     *
     * Iterate over the spells
     * - For each spell count the quantity of numbers where spell * potion[i] >= success
     * - If spell * potion[i] >= success -> count += (right - left) / 2
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] res = new int[spells.length];

        int m = potions.length;
        int maxPotion = potions[m - 1];

        for (int i = 0; i < spells.length; i++) {
            int spell = spells[i];
            // Minimum potion strength required to make the spell successful
            long minPotion = (long) Math.ceil((1.0 * success) / spell);
            if (minPotion > maxPotion) {
                res[i] = 0;
                continue;
            }

            int index = lowerBound(potions, (int) minPotion);
            res[i] = m - index;
        }
        return res;
    }

    private int lowerBound(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int middle = (left + right) / 2;
            int hoursSpent = 0;
            /*
             * Iterate over the piles and calculate hourSpent
             * Increase the hourSpent by ceil(pile / middle)
             */
            for (int pile : piles) {
                hoursSpent += Math.ceil((double) pile / middle);
            }

            if (hoursSpent <= h) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return right;
    }
}

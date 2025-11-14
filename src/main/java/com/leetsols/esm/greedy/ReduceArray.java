package com.leetsols.esm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReduceArray {
    /*
     * - Choose the set of integers which occurs in array to remove -> they should be the most frequent one
     * - Return the min size of the set
     */
    public int minSetSize(int[] arr) {
        Arrays.sort(arr);

        List<Integer> counts = new ArrayList<>();
        int currentNum = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                currentNum += 1;
                continue;
            }
            counts.add(currentNum);
            currentNum = 1;
        }
        counts.add(currentNum);

        Collections.sort(counts);
        Collections.reverse(counts);

        int numberOfRemovedFromArr = 0;
        int size = 0;
        for (int count : counts) {
            numberOfRemovedFromArr += count;
            size += 1;
            if (numberOfRemovedFromArr >= arr.length / 2) {
                break;
            }
        }

        return size;
    }
}

package com.leetsols.esm.greedy;

import java.util.Arrays;

public class BoatsToSave {
    /*
     * - limit -> max weigh boat can carry
     * - boat can take 2 people at the time
     * - people = [3,2,2,1], limit = 3
     * - 3 , 2 1 , 2
     *
     * - Sort an array by ascending
     * - Iterate over the loop
     * - inner while loop for limit counting
     */
    public static int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        int start = 0;
        int last = people.length - 1;
        Arrays.sort(people);

        while (start <= last) {
            /*
             * - Heaviest person can sit within the lightest one
             * - We can iterate along the lightest
             */
            if (people[start] + people[last] <= limit) {
                start++;
            }
            /*
             * - Decrement last to make the next pair
             */
            last--;
            ans++;
        }
        return ans;
    }
}

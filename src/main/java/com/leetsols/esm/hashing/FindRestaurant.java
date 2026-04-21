package com.leetsols.esm.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindRestaurant {
    public String[] findRestaurant(String[] list1, String[] list2) {
        var map = new HashMap<String, Integer>();
        for (int i = 0; i < list1.length; i++) map.put(list1[i], i);

        List<String> result = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int sum = i + map.get(list2[i]);

                if (sum < minSum) {
                    minSum = sum;
                    result.clear();
                    result.add(list2[i]);
                } else if (sum == minSum) {
                    result.add(list2[i]);
                }
            }
        }
        return result.toArray(new String[0]);
    }
}

package com.leetsols.esm.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Problem type: Array, Hash Table, Two Pointers, Binary Search, Sorting
 * Number: 349 Intersection of Two Arrays, 350 Intersection of Two Arrays ||
 */
public class IntersectionOfArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) set.add(i);
        for (int j : nums2) set1.add(j);

        Set<Integer> out = new HashSet<>();
        for (int num : set) {
            if (set1.contains(num)) {
                out.add(num);
            }
        }
        return out.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersectionByTwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int N = nums1.length;
        int P = nums2.length;

        int p1 = 0;
        int p2 = 0;

        var set = new HashSet<Integer>();

        while (p1 < N && p2 < P) {
            if (nums1[p1] == nums2[p2]) {
                set.add(nums1[p1]);
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }

        int k = nums1.length;
        int[] res = new int[k];
        int curr = 0;
        for (int e : set) {
            res[curr++] = e;
        }
        return res;
    }

    public int[] intersectionSet(int[] nums1, int[] nums2) {
        var setOne = new HashSet<Integer>();
        var setTwo = new HashSet<Integer>();

        for (int j : nums1) setOne.add(j);
        for (int i : nums2) setTwo.add(i);

        setOne.retainAll(setTwo);

        int[] res = new int[setOne.size()];
        int idx = 0;
        for (int s : setOne) res[idx++] = s;
        return res;
    }

    public int[] intersectionTwoSets(int[] nums1, int[] nums2) {
        var setOne = new HashSet<Integer>();
        var setTwo = new HashSet<Integer>();

        for (int j : nums1) setOne.add(j);
        for (int i : nums2) setTwo.add(i);

        if (setOne.size() < setTwo.size()) {
            return setIntersection(setOne, setTwo);
        } else {
            return setIntersection(setTwo, setOne);
        }
    }

    public int[] setIntersection(Set<Integer> setOne, Set<Integer> setTwo) {
        int[] res = new int[setOne.size()];
        int curr = 0;
        for (int i : setOne) {
            if (setTwo.contains(i)) res[curr++] = i;
        }
        return Arrays.copyOf(res, curr);
    }

    public int[] intersectionHashtable(int[] nums1, int[] nums2) {
        var map = new HashMap<Integer, Integer>();
        var list = new ArrayList<Integer>();

        for (int i : nums1) map.put(i, 1);
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) == 1) {
                list.add(i);
                map.put(i, 0);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        var numsOne = new HashMap<Integer, Integer>();
        var numsTwo = new HashMap<Integer, Integer>();

        for (int j : nums1) numsOne.put(j, numsOne.getOrDefault(j, 0) + 1);
        for (int i : nums2) numsTwo.put(i, numsTwo.getOrDefault(i, 0) + 1);

        numsOne.keySet().retainAll(numsTwo.keySet());
        var list = new ArrayList<Integer>();
        for (int i : numsOne.keySet()) {
            var qOne = numsOne.get(i);
            var qTwo = numsTwo.get(i);
            if (!Objects.equals(qOne, qTwo)) {
                int diff = Math.min(qOne, qTwo);
                for (int j = 0; j < diff; j++) {
                    list.add(i);
                }
            } else {
                for (int j = 0; j < qOne; j++) list.add(i);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    public int[] intersectTwo(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersectTwo(nums2, nums1);
        }
        var map = new HashMap<Integer, Integer>();
        for (int j : nums1) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        var list = new ArrayList<Integer>();
        for (int ch : nums2) {
            int n = map.getOrDefault(ch, 0);
            if (n > 0) {
                list.add(ch);
                map.put(ch, n - 1);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}

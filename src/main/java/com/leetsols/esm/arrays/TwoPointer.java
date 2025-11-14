package com.leetsols.esm.arrays;

import java.util.ArrayList;
import java.util.List;

public class TwoPointer {
    public boolean checkForTarget(int[] array, int target) {
        if (array.length == 0) {
            return false;
        }

        int i = 0;
        int j = array.length - 1;

        while (i < j) {
            int curr = array[i] + array[j];

            if (curr == target) {
                return true;
            }

            if (curr > target) {
                j--;
            } else if (array[i] + array[j] < target) {
                i++;
            }
        }
        return false;
    }

    public List<Integer> combine(int[] array1, int[] array2) {
        var ans = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        /*
         * Compare elements and insert them in ArrayList
         */
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                ans.add(array1[i]);
                i++;
            } else {
                ans.add(array2[j]);
                j++;
            }
        }

        // One of the 2 arrays might bigger, so insert the left elements from one of them
        while (i < array1.length) {
            ans.add(array1[i]);
            i++;
        }

        while (j < array2.length) {
            ans.add(array2[j]);
            j++;
        }
        return ans;
    }

    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            var sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int pos = n - 1; // Start filling from the end

        while (left <= right) {
            int leftVal = nums[left];
            int rightVal = nums[right];
            if (Math.abs(leftVal) > Math.abs(rightVal)) {
                result[pos--] = leftVal * leftVal;
                left++;
            } else {
                result[pos--] = rightVal * rightVal;
                right--;
            }
        }

        return result;
    }

    public static String reverseWords(String s) {
        int lastSpaceIndex = -1;
        char[] chArray = s.toCharArray();
        int len = s.length();
        for (int strIndex = 0; strIndex <= len; strIndex++) {
            if (strIndex == len || chArray[strIndex] == ' ') {
                int startIndex = lastSpaceIndex + 1;
                int lastIndex = strIndex - 1;
                while (startIndex < lastIndex) {
                    char temp = chArray[startIndex];
                    chArray[startIndex] = chArray[lastIndex];
                    chArray[lastIndex] = temp;
                    startIndex++;
                    lastIndex--;
                }
                lastSpaceIndex = strIndex;
            }
        }
        return new String(chArray);
    }
}
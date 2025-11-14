package com.leetsols.esm.strings;

import java.util.Arrays;

public class RemoveElement {
    public static int[] removeElement(int[] array, int val) {
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != val) {
                array[k] = array[i];
                k++;
            }
        }
        return array;
    }

    static void main() {
        System.out.println(Arrays.toString(removeElement(new int[]{1, 1, 3, 3, 2, 4}, 3)));
    }
}

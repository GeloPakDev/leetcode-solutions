package com.leetsols.esm.strings;

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
}

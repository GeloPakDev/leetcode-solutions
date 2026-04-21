package com.leetsols.esm.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    /*
     * Rearrange the characters in [s] such any 2 adjacent characters are not the same.
     *
     * - Alternate positioning of the most frequent letters.
     *  - Criteria: Heapify the elements based on their frequency
     *  - Count the frequency of the elements in the hashtable
     *  - Preserve the Heap based on the Frequency (i.e. the most frequent characters at the top)
     *  - Form the string based on the alternating the characters
     *  - Sort hashmap based on the frequency
     *  - Keep alternating the string based on the available quantity for each letter.
     *    If one of them is finished, go for the next one available in the list.
     */
    public String reorganizeString(String s) {
        // Make a frequency map.
        var len = s.length();
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < len; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            if (map.get(s.charAt(i)) > (len + 1) / 2) return "";
        }

        // Sort Max-Heap based on the value.
        var heap = new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> b.getValue() - a.getValue());
        heap.addAll(map.entrySet());

        /*
         * Alternate characters:
         * - Get 2 characters from the heap.
         * - Keep alternating them until one of them is finished.
         * - When one of them is finished, put the left one back
         *   into the heap (to heapify), get others from the heap.
         * - If there will not exist enough letters, or there will leave only one, it means that we will
         *   not be able to alternate the characters, return "".
         */
        StringBuilder sb = new StringBuilder();
        Map.Entry<Character, Integer> prev = null;

        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> curr = heap.poll();

            // Use the current most frequent character
            sb.append(curr.getKey());
            curr.setValue(curr.getValue() - 1);

            // Re-add the previous character to the heap
            if (prev != null && prev.getValue() > 0) heap.offer(prev);

            // Keep the current heap for the next iteration.
            prev = curr;
        }
        return sb.length() == len ? sb.toString() : "";
    }

    /*
     *
     */
    public String reorganizeStringTwo(String s) {
        var freqMap = new int[26];
        for (char ch : s.toCharArray()) {
            freqMap[ch - 'a']++;
        }

        var heap = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            if (freqMap[i] > 0) heap.offer(new int[]{i + 'a', freqMap[i]});
        }

        var sb = new StringBuilder();
        while (!heap.isEmpty()) {
            var first = heap.poll();
            /*
             *
             * - If the current character is different from the last one in the string
             * append it to the answer.
             * - If the count > 0, decrement it and push it back to the heap
             *
             *
             * - If the first character is the same as the last one in the string, choose
             *   the next character from the heap. If the heap is empty, return empty string
             *   as it is impossible to re-arrange characters
             * - If the count > 0, decrement it and push it back to the heap
             * - Push the first taken letter back to the heap.
             */
            if (sb.isEmpty() || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) heap.offer(first);
            } else {
                if (heap.isEmpty()) return "";

                var second = heap.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) heap.offer(second);

                heap.offer(first);
            }
        }
        return sb.toString();
    }
}

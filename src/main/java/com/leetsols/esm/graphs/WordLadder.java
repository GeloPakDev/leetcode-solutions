package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {
    /*
     * Algorithm:
     * - To find the shortest transformation sequence we need to apply the BFS
     *   which results in finding the shortest path in a graph, for the purpose
     *   and fit in our algorithm, level variable will be applied to get the
     *   current level where out [endWord] has been found.
     *
     * Bidirectional BFS:
     * - As search space can be considerably large, it is important to start
     *   the BFS from 2 nodes (start, end), consequently 2 visited sets are
     *   needed.
     * - If we find the node which is in the visited of the parallel search
     *   here the search will be terminated
     * - Shortest transformation sequence is sum of levels of the meet point
     *   node from each end.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;

        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);

        int len = 1;
        while (!begin.isEmpty() && !end.isEmpty()) {
            // swap 2 sets
            if (begin.size() > end.size()) {
                Set<String> temp = begin;
                begin = end;
                end = temp;
            }

            var newBeginSet = new HashSet<String>();
            for (String word : begin) {
                var neighbors = neighbors(word);
                for (String neighbor : neighbors) {
                    if (end.contains(neighbor)) return len + 1;
                    if (words.contains(neighbor)) {
                        newBeginSet.add(neighbor);
                        words.remove(neighbor);
                    }
                }
            }
            begin = newBeginSet;
            len++;
        }
        return 0;
    }

    /*
     * Algorithm:
     * - On every iteration, we have to find the neighbors for a given word, one of the
     *   approach might be iteration through the input list and comparing the current
     *   word and others from the list to check the ones which differs by 1 letter, so
     *   they would be the neighbors, but it is inefficient as iterating over the list
     *   to check for neighbors on each node
     * - Efficient approach would be generating all possible neighbors for the given
     *   current node by replacing each letter to all existing letters from the alphabet
     *   hit -> [a]it, [b]it, [c]it, ..., [z]it
     *       -> h[a]t, h[b]t, h[c]t, ..., h[z]t
     *       -> hi[a], hi[b], hi[c], ..., hi[z]
     * - Each generated neighbor then compared to the existing in the list and will be
     *   enqueued into the queue.
     * - At the beginning of each iteration, we store the current letter which we will
     *   be swapping, to restore that state in the end
     *   char temp = chars[i] -> stores original letter
     *   chars[i] = temp      -> re-store that letter in the original position
     */
    public List<String> neighbors(String string) {
        char[] chars = string.toCharArray();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            /*
             * Each letter will be replaced with the one from Alphabet to generate a new word.
             */
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String neighbor = new String(chars);
                result.add(neighbor);
            }
            chars[i] = temp;
        }
        return result;
    }
}

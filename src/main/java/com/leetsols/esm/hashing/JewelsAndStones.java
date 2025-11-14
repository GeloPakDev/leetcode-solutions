package com.leetsols.esm.hashing;

import java.util.HashMap;

/*
 * Problem type: Hashtable, String
 * Number: 771 Jewels and Stones
 */
public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < stones.length(); i++) {
            var key = stones.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        var res = 0;
        for (int i = 0; i < jewels.length(); i++) {
            var key = jewels.charAt(i);
            if (map.containsKey(key)) {
                res += map.get(key);
            }
        }
        return res;
    }
}

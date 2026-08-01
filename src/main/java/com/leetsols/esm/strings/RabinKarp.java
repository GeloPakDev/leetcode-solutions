package com.leetsols.esm.strings;

public class RabinKarp {
    // Base number of characters in the input alphabet (ASCII)
    private static final int BASE = 256;
    // A large prime number to use as the modulo to avoid the integer overflow
    private static final int MODULO = 101;

    public static void search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        if (m > n) return;

        long patternHash = 0;// Hash value for pattern
        long textHash = 0;// Hash value for sliding window in text
        long h = 1;

        /*
         * On this step, it precomputes the BASE(m - 1) * (mod * MODULO)
         * , which is the mathematical weight of the leftmost character in the window
         * so it can be substructed
         */
        for (int i = 0; i < m - 1; i++) h = (h * BASE) % MODULO;

        // Calculate the initial hash value for pattern and first window of text
        for (int i = 0; i < m; i++) {
            patternHash = (BASE * patternHash + pattern.charAt(i)) % MODULO;
            textHash = (BASE * textHash + text.charAt(i)) % MODULO;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            // Check if hash values matches
            if (patternHash == textHash) {
                // Modulo hash can cause collisions, check the actual characters to guarantee the match
                int j;
                for (j = 0; j < m; j++) if (text.charAt(i + j) != pattern.charAt(j)) break;

                if (j == m) System.out.println("Pattern is found at idx: " + i);
            }

            // Calculate the hash value for the next window of text
            // Remove leading character, add trailing digit
            if (i < n - m) {
                textHash = (BASE * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % MODULO;
                // You might get the negative value from the substraction, convert to positive
                if (textHash < 0) textHash = (textHash + MODULO);
            }
        }
    }
}

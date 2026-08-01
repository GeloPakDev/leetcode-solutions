package com.leetsols.esm.graphs;

import java.util.Arrays;

public class CheapestFlightsWithKStops {
    /*
     * Description:
     * - flights[i] = [from[i], to[i], price[i]].
     * - source, destination, k.
     * - return the cheapest price from source to destination with at most k stops
     * - if there is no such route, provide the res as -1;
     *
     * Algorithm:
     * - By the Bellman-Ford property, we can state that it should run for the
     *   (V - 1) times, and the problem should find out at most [K] intermediate
     *   steps, which means we can use at most [K + 1] edges.
     * - Instead of running the loop for the (V - 1) times, you run exactly [K + 1] times
     *  - Iteration 1 gives you the cheapest price with 0 stops (1 edge).
     *  - Iteration 2 gives you the cheapest price with up to 1 stop (2 edges).
     *  - Iteration [K + 1] gives you the cheapest price with up to [K] stops (K + 1 edges).
     * - Use the snapshot array to consider the following case:
     *  - Node 0 -> Node 1 (cost: $10)
     *  - Node 1 -> Node 2 (cost: $20)
     *  You start at Node 0, and you are allowed [K = 0] stops. This means you can use at most 1 flight.
     *  Let's trace what happens during Iteration 1 if you don't use a snapshot array:
     *   - Start of Iteration 1: prices = [0, ∞, ∞]
     *   - Your code loops through the flights:
     *    - Flight 1 (0 -> 1): prices[0] is 0. 0 + 10 = 10. Since 10 < ∞, you update prices[1] = 10.
     *    Current prices array: [0, 10, ∞]
     *    - Flight 2 (1 -> 2): Now your loop looks at the next flight. It checks prices[1]. Because
     *      you just updated it a microsecond ago, it reads 10. 10 + 20 = 30. Since 30 < ∞, it updates
     *      prices[2] = 30.
     *    Current prices array: [0, 10, 30]
     * Why this is broken: In a single iteration, your code calculated that you can reach Node 2 with a cost of $30.
     * But to get to Node 2, you had to take two flights (0 -> 1 AND 1 -> 2). If your user constraint was K = 0
     * (0 stops, max 1 flight), your code just cheated. It allowed the signal to "teleport" across two flights in a
     * single step because Flight 2 read the updated value from Flight 1.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Track the minimum cost to reach each city
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        // Run the relaxation loop exactly K + 1 times
        for (int i = 0; i <= k; i++) {
            // Create a snapshot of the prices from the previous iteration
            int[] previousPrices = Arrays.copyOf(prices, n);

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];

                // If the source city of thus flight hasn't been reached yet, skip
                if (previousPrices[u] == Integer.MAX_VALUE) continue;

                // Relax the edge using the snapshot value
                if (previousPrices[u] + price < prices[v]) prices[v] = previousPrices[u] + price;
            }
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}

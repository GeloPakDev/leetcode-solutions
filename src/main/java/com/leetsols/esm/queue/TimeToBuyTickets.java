package com.leetsols.esm.queue;

public class TimeToBuyTickets {
    /*
     * Algorithm:
     * - The main idea is to count how many seconds will it take for the k[th]
     *   person to quit the queue.
     * - As removing from the queue at the front will decrement each element by one
     *   , there exists possibility to decrement each of the element through the
     *   iteration, it simulates the processing in the queue.
     * - As we need to determine how much seconds it will take for the k[th] element
     *   to pass, during each iteration check when k[th] element will reach 0.
     * - Each time the i[th] element in an array will reach 0, size of the array decreases
     *   by one (len - 1)
     * - if (tickets[i] > 0 && tickets[k] > 0)
     *  - tickets[k] checks the case when the tickets[k] already becomes 0, but the
     *    there are elements exists after it
     *    [0,1,3,1,3,1,181,9] k = 5
     *    [0,1,3,1,3,0,181,9] -> res will count additional 2 seconds after it, even though
     *    it has finished buying tickets
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        while (tickets[k] > 0) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] > 0 && tickets[k] > 0) {
                    tickets[i]--;
                    res++;
                }
            }
        }
        return res;
    }
}

package com.leetsols.esm.queue;

import java.util.LinkedList;

public class RecentCounter {
    private final LinkedList<Integer> requests;

    public RecentCounter() {
        this.requests = new LinkedList<>();
    }

    public int ping(int t) {
        this.requests.addLast(t);

        while (requests.getFirst() < t - 3000) {
            this.requests.removeFirst();
        }
        return requests.size();
    }
}

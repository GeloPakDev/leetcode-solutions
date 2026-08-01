package com.leetsols.esm.graphs.disjointset;

import com.leetsols.esm.hashing.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class EvaluateDivision {
    /*
     * Description:
     * - equations[i] = [A[i], B[i]] and values[i] represent the equation: A[i] / B[i] = values[i]
     * - queries[j] = [C[j], D[j]] represents the query where you must find the answer for C[j] / D[j]
     * - Return the answers to all queries, If a single answer is not available, return -1.0
     *
     * Approach:
     * - a/b = 2, b/c = 3 -> b/a = 0.5, c/b = 1/3
     * - a/c = a/b * b/c = 2 * 3 = 6
     * - Each division implies the reverse direction
     * - Chaining up the equations provides us the new equation
     * - Graph can be represented as follows: variable can be represented as a node in a graph
     *   division relationship between the nodes can be modeled as an edge with direction and
     *   an edge.
     *
     * Algorithm:
     * - Problem can be represented as a path searching problem in a graph
     *  - Given 2 nodes, if there is path between them, calculate their cumulative
     *    products along path as result.
     * Step 1: Build the graph
     * - Each equation corresponds to 2 edges in a graph
     *
     * Step 2: Evaluate each query one by one
     * - Evaluation is done by searching the path between 2 given variables
     * - Edge cases:
     *  - If either of the nodes doesn't exist in a graph, the variables didn't
     *    appear in any of the input equations, then we can assert that there
     *    is no path.
     *  - If the origin and destination is the same node, a/a, we can assume that
     *    the result is 1
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        var map = new HashMap<String, List<Pair<String, Double>>>();
        var res = new double[queries.size()];

        // Create a bidirectional map (graph)
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            var src = equations.get(i).get(0);
            var dst = equations.get(i).get(1);
            var weight = values[i];
            // source -> destination
            map.putIfAbsent(src, new ArrayList<>());
            map.get(src).add(new Pair<>(dst, weight));
            // destination -> source
            map.putIfAbsent(dst, new ArrayList<>());
            map.get(dst).add(new Pair<>(src, 1 / weight));
        }

        // Go over the queries
        for (int i = 0; i < queries.size(); i++) {
            var src = queries.get(i).get(0);
            var dst = queries.get(i).get(1);
            //If source node or destination node doesn't exist ce
            if (!map.containsKey(src) || !map.containsKey(dst)) res[i] = -1;
            else if (Objects.equals(src, dst)) res[i] = 1.0;
            else {
                var visited = new HashSet<String>();
                res[i] = dfs(src, dst, visited, map);
            }
        }
        return res;
    }

    public double dfs(String source, String destination, Set<String> visited, Map<String, List<Pair<String, Double>>> graph) {
        if (Objects.equals(source, destination)) return 1;

        visited.add(source);

        for (var node : graph.get(source)) {
            var neighbor = node.getKey();
            if (!visited.contains(neighbor)) {
                double res = dfs(neighbor, destination, visited, graph);
                if (res != -1) return node.getValue() * res;
            }
        }
        return -1;
    }

    public double[] calcEquationUnionFind(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Pair<String, Double>> graph = new HashMap<>();

        /*
         * Create a graph which represented as follows:
         * - key:   current node
         * - value: parent node, weight
         */
        for (int i = 0; i < equations.size(); i++) {
            var equation = equations.get(i);

            var dividend = equation.get(0);
            var divisor = equation.get(1);
            double quotient = values[i];
            union(graph, dividend, divisor, quotient);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            var query = queries.get(i);
            var dividend = query.get(0);
            var divisor = query.get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) res[i] = -1.0;
            else {
                Pair<String, Double> dividendEntry = find(graph, dividend);
                Pair<String, Double> divisorEntry = find(graph, divisor);

                String dividendGid = dividendEntry.getKey();
                String divisorGid = divisorEntry.getKey();
                Double dividendWeight = dividendEntry.getValue();
                Double divisorWeight = divisorEntry.getValue();

                if (!dividendGid.equals(divisorGid)) res[i] = -1.0;
                else res[i] = dividendWeight / divisorWeight;
            }
        }
        return res;
    }

    /*
     * Algorithm:
     * - Merging groups:
     * - When we process equations like: a / b = 2.0
     *  - It takes roots of them and links them together.
     *  - divisorWeight * value / dividendWeight
     *   - A(dividend) - A = root[A] × weight[A]
     *   - B(divisor)  - B = root[B] × weight[B]
     *  - A / B = value
     *   - root[A] × weight[A] /
     *   - root[B] × weight[B]
     *   = value
     *  - root[A] / root[B] = (value * weight[B]) / weight[A]
     */
    private void union(HashMap<String, Pair<String, Double>> graph, String dividend, String divisor, Double value) {
        var dividendEntry = find(graph, dividend);
        var divisorEntry = find(graph, divisor);

        String dividendGid = dividendEntry.getKey();
        String divisorGid = divisorEntry.getKey();

        Double dividendWeight = dividendEntry.getValue();
        Double divisorWeight = divisorEntry.getValue();

        if (!dividendGid.equals(divisorGid)) {
            graph.put(dividendGid, new Pair<>(divisorGid, value * divisorWeight / dividendWeight));
        }
    }

    private Pair<String, Double> find(HashMap<String, Pair<String, Double>> graph, String nodeId) {
        // If the node we are looking for, doesn't exist yet in the graph, put it in the map
        if (!graph.containsKey(nodeId)) graph.put(nodeId, new Pair<>(nodeId, 1.0));

        var entry = graph.get(nodeId);

        // If the parent node != targetNode, walk up the chain to find the root node of the targetNode
        if (!entry.getKey().equals(nodeId)) {
            var newEntry = find(graph, entry.getKey());
            // When the root of the node reached
            graph.put(nodeId, new Pair<>(newEntry.getKey(), entry.getValue() * newEntry.getValue()));
        }
        return graph.get(nodeId);
    }
}

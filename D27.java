// 3650. Minimum Cost Path with Edge Reversals
// You are given a directed, weighted graph with n nodes labeled from 0 to n - 1, and an array edges where edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with cost wi.
// Each node ui has a switch that can be used at most once: when you arrive at ui and have not yet used its switch, you may activate it on one of its incoming edges vi → ui reverse that edge to ui → vi and immediately traverse it.
// The reversal is only valid for that single move, and using a reversed edge costs 2 * wi.
// Return the minimum total cost to travel from node 0 to node n - 1. If it is not possible, return -1.
// Example 1:
// Input: n = 4, edges = [[0,1,3],[3,1,1],[2,3,4],[0,2,2]]
// Output: 5
// Explanation:
// Use the path 0 → 1 (cost 3).
// At node 1 reverse the original edge 3 → 1 into 1 → 3 and traverse it at cost 2 * 1 = 2.
// Total cost is 3 + 2 = 5.


import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> out = new ArrayList<>();
        List<List<int[]>> in = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            out.add(new ArrayList<>());
            in.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            out.get(e[0]).add(new int[]{e[1], e[2]});
            in.get(e[1]).add(new int[]{e[0], e[2]});
        }

        long INF = (long) 1e18;
        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<long[]> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[0][0] = 0;
        pq.add(new long[]{0, 0, 0}); // cost, node, used

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long cost = cur[0];
            int u = (int) cur[1];
            int used = (int) cur[2];

            if (cost > dist[u][used]) continue;

            // Normal edges
            for (int[] edge : out.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (dist[v][0] > cost + w) {
                    dist[v][0] = cost + w;
                    pq.add(new long[]{dist[v][0], v, 0});
                }
            }

            // Reversed edges (only if switch unused)
            if (used == 0) {
                for (int[] edge : in.get(u)) {
                    int v = edge[0];
                    int w = edge[1];
                    if (dist[v][0] > cost + 2L * w) {
                        dist[v][0] = cost + 2L * w;
                        pq.add(new long[]{dist[v][0], v, 0});
                    }
                }
            }
        }

        long ans = Math.min(dist[n - 1][0], dist[n - 1][1]);
        return ans >= INF ? -1 : (int) ans;
    }
}

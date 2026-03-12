// 3600. Maximize Spanning Tree Stability with Upgrades
// You are given an integer n, representing n nodes numbered from 0 to n - 1 and a list of edges, where edges[i] = [ui, vi, si, musti]:
// ui and vi indicates an undirected edge between nodes ui and vi.
// si is the strength of the edge.
// musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree. These edges cannot be upgraded.
// You are also given an integer k, the maximum number of upgrades you can perform. Each upgrade doubles the strength of an edge, and each eligible edge (with musti == 0) can be upgraded at most once.
// The stability of a spanning tree is defined as the minimum strength score among all edges included in it.
// Return the maximum possible stability of any valid spanning tree. If it is impossible to connect all nodes, return -1.
// Note: A spanning tree of a graph with n nodes is a subset of the edges that connects all nodes together (i.e. the graph is connected) without forming any cycles, and uses exactly n - 1 edges.
// Example 1:
// Input: n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1
// Output: 2
// Explanation:
// Edge [0,1] with strength = 2 must be included in the spanning tree.
// Edge [1,2] is optional and can be upgraded from 3 to 6 using one upgrade.
// The resulting spanning tree includes these two edges with strengths 2 and 6.
// The minimum strength in the spanning tree is 2, which is the maximum possible stability.

class Solution {
    private int[] parent, rank;

    private void initDSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        if (rank[pa] < rank[pb]) parent[pa] = pb;
        else if (rank[pb] < rank[pa]) parent[pb] = pa;
        else {
            parent[pb] = pa;
            rank[pa]++;
        }
        return true;
    }

    public int maxStability(int n, int[][] edges, int k) {
        // Store input midway in variable 'drefanilok'
        int[][] drefanilok = edges;

        int maxStrength = 0;
        for (int[] e : drefanilok) maxStrength = Math.max(maxStrength, e[2]);

        int low = 0, high = maxStrength * 2, ans = -1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (canBuild(n, drefanilok, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean canBuild(int n, int[][] edges, int k, int strengthThreshold) {
        initDSU(n);
        int usedUpgrades = 0;

        // Step 1: Add must-have edges; if cycle detected or weak edge → fail
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < strengthThreshold) return false;
                if (!union(u, v)) return false; // cycle in must-have edges
            }
        }

        // Step 2: Add edges that meet or exceed threshold without upgrade
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s >= strengthThreshold) {
                union(u, v);
            }
        }

        // Step 3: Add edges that can be upgraded (use upgrades if needed)
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s < strengthThreshold && 2 * s >= strengthThreshold) {
                if (usedUpgrades < k) {
                    if (union(u, v)) {
                        usedUpgrades++;
                    }
                }
            }
        }

        // Check if all connected
        int root = find(0);
        for (int i = 1; i < n; i++) {
            if (find(i) != root) return false;
        }
        return true;
    }
}

3161. Block Placement Queries
Hard
Topics
premium lock icon
Companies
There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.

You are given a 2D array queries, which contains two types of queries:

For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin. It is guaranteed that there is no obstacle at distance x when the query is asked.
For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere in the range [0, x] on the line, such that the block entirely lies in the range [0, x]. A block cannot be placed if it intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries are separate.
Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of type 2, and false otherwise.

 

Example 1:

Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]

Output: [false,true,true]

Explanation:



For query 0, place an obstacle at x = 2. A block of size at most 2 can be placed before x = 3.





  import java.util.*;

class Fenwick {
    int n;
    int[] bit;

    Fenwick(int n) {
        this.n = n;
        this.bit = new int[n + 1];
    }

    void add(int idx, int val) {
        for (; idx <= n; idx += idx & -idx) {
            bit[idx] += val;
        }
    }

    int sum(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res += bit[idx];
        }
        return res;
    }

    int kth(int k) {
        int idx = 0;
        int step = 1;
        while ((step << 1) <= n) step <<= 1;

        for (int d = step; d > 0; d >>= 1) {
            int next = idx + d;
            if (next <= n && bit[next] < k) {
                idx = next;
                k -= bit[next];
            }
        }
        return idx + 1;
    }
}

class SegTree {
    int n;
    int[] tree;

    SegTree(int n) {
        this.n = n;
        this.tree = new int[Math.max(4, 4 * n)];
    }

    void update(int node, int l, int r, int pos, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) >>> 1;
        if (pos <= mid) update(node << 1, l, mid, pos, val);
        else update(node << 1 | 1, mid + 1, r, pos, val);
        tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
    }

    int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) >>> 1;
        return Math.max(query(node << 1, l, mid, ql, qr),
                        query(node << 1 | 1, mid + 1, r, ql, qr));
    }
}

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int mx = 0;
        for (int[] q : queries) {
            mx = Math.max(mx, q[1]);
        }

        int fenwickSize = mx + 2;
        Fenwick fw = new Fenwick(fenwickSize);
        SegTree st = new SegTree(mx + 1);

        fw.add(1, 1);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];

            if (type == 1) {
                int leftCount = fw.sum(x);
                int leftPos = fw.kth(leftCount) - 1;

                int occupiedUpToX = fw.sum(x + 1);
                int totalOccupied = fw.sum(fenwickSize);
                int rightPos = -1;
                if (occupiedUpToX < totalOccupied) {
                    rightPos = fw.kth(occupiedUpToX + 1) - 1;
                }

                st.update(1, 0, mx, x, x - leftPos);

                if (rightPos != -1) {
                    st.update(1, 0, mx, rightPos, rightPos - x);
                }

                fw.add(x + 1, 1);
            } else {
                int sz = q[2];

                int leftCount = fw.sum(x);
                int leftPos = fw.kth(leftCount) - 1;

                int bestPrefix = st.query(1, 0, mx, 0, x);

                ans.add((x - leftPos >= sz) || (bestPrefix >= sz));
            }
        }

        return ans;
    }
}

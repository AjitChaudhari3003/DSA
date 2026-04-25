// 3464. Maximize the Distance Between Points on a Square
// You are given an integer side, representing the edge length of a square with corners at (0, 0), (0, side), (side, 0), and (side, side) on a Cartesian plane.
// You are also given a positive integer k and a 2D integer array points, where points[i] = [xi, yi] represents the coordinate of a point lying on the boundary of the square.
// You need to select k elements among points such that the minimum Manhattan distance between any two points is maximized.
// Return the maximum possible minimum Manhattan distance between the selected k points.
// The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.
// Example 1:
// Input: side = 2, points = [[0,2],[2,0],[2,2],[0,0]], k = 4
// Output: 2


import java.util.*;

public class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] pos = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            long p;
            if (y == 0)
                p = x;
            else if (x == side)
                p = (long) side + y;
            else if (y == side)
                p = 2L * side + (side - x);
            else // x == 0
                p = 3L * side + (side - y);
            pos[i] = p;
        }
        Arrays.sort(pos);
        long L = 4L * side;
        int total = n * 2;
        long[] ext = new long[total];
        for (int i = 0; i < n; i++){
            ext[i] = pos[i];
            ext[i+n] = pos[i] + L;
        }
        
        long low = 0, high = 2L * side;
        while (low < high) {
            long mid = (low + high + 1) / 2;
            if (canPlace(mid, k, n, ext, L))
                low = mid;
            else
                high = mid - 1;
        }
        return (int) low;
    }
    
    private boolean canPlace(long d, int k, int n, long[] ext, long L) {
        for (int start = 0; start < n; start++) {
            int cur = start;
            long last = ext[start];
            boolean valid = true;
            int limit = start + n;
            for (int step = 1; step < k; step++){
                long target = last + d;
                int lo = cur + 1, hi = limit;
                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    if (ext[mid] < target)
                        lo = mid + 1;
                    else
                        hi = mid;
                }
                if (lo == limit) {
                    valid = false;
                    break;
                }
                cur = lo;
                last = ext[cur];
            }
            if (valid && ext[start] + L - last >= d)
                return true;
        }
        return false;
    }
}

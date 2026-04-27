// 1391. Check if There is a Valid Path in a Grid
// You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:
// 1 which means a street connecting the left cell and the right cell.
// 2 which means a street connecting the upper cell and the lower cell.
// 3 which means a street connecting the left cell and the lower cell.
// 4 which means a street connecting the right cell and the lower cell.
// 5 which means a street connecting the left cell and the upper cell.
// 6 which means a street connecting the right cell and the upper cell.
// You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
// Notice that you are not allowed to change any street.
// Return true if there is a valid path in the grid or false otherwise


class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][][] dir = new int[7][][];

        dir[1] = new int[][]{{0,-1},{0,1}};
        dir[2] = new int[][]{{-1,0},{1,0}};
        dir[3] = new int[][]{{0,-1},{1,0}};
        dir[4] = new int[][]{{0,1},{1,0}};
        dir[5] = new int[][]{{0,-1},{-1,0}};
        dir[6] = new int[][]{{0,1},{-1,0}};

        boolean[][] vis = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0,0});
        vis[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if(r == m-1 && c == n-1)
                return true;

            for(int[] move : dir[grid[r][c]]){
                int nr = r + move[0];
                int nc = c + move[1];

                if(nr<0 || nc<0 || nr>=m || nc>=n || vis[nr][nc])
                    continue;

                for(int[] back : dir[grid[nr][nc]]){
                    if(nr + back[0] == r && nc + back[1] == c){
                        vis[nr][nc] = true;
                        q.offer(new int[]{nr,nc});
                    }
                }
            }
        }

        return false;
    }
}

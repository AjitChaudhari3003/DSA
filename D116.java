// 1559. Detect Cycles in 2D Grid
// Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
// A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
// Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
// Return true if any cycle of the same value exists in grid, otherwise, return false.
// Example 1:
// Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
// Output: true
// Explanation: There are two valid cycles shown in different colors in the image below:



class Solution {
    int n;
    int m;
    static int[]dr={-1,1,0,0};
    static int[]dc={0,0,-1,1};
    boolean[][]visit;
    class pair{
        int row,col,prow,pcol;
        public pair(int row,int col,int prow,int pcol){
            this.row=row;
            this.col=col;
            this.prow=prow;
            this.pcol=pcol;
        }
    }
    public boolean containsCycle(char[][] grid) {
        n=grid.length;
        m=grid[0].length;
        visit=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visit[i][j])continue;
                if(bfs(grid,i,j))return true;
            }
        }
        return false;
    }public boolean bfs(char[][] grid,int i,int j){
        Queue<pair>q=new LinkedList<>();
        q.offer(new pair(i,j,-1,-1));
        char ch=grid[i][j];
        while(!q.isEmpty()){
            pair curr=q.remove();
            int r=curr.row;
            int c=curr.col;
            int pr=curr.prow;
            int pc=curr.pcol;
            if(visit[r][c])return true;
            visit[r][c]=true;
            for(int k=0;k<4;k++){
                int nr=r+dr[k];
                int nc=c+dc[k];
                if(nr>=0&&nr<n&&nc>=0&&nc<m&&grid[nr][nc]==ch){
                    if(!(nr==pr&&nc==pc)){
                        q.offer(new pair(nr,nc,r,c));
                    }
                }

            }
        }
        return false;
    }
}




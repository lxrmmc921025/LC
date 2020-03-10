package dfs;

/**
 * Created by az on 3/9/2020.
 */
public class wordSearch {
    //LC 79 medium
    int[][] dirs = {{1, 0},{-1, 0},{0, 1},{0, -1}};

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0 || word.length() == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    //improve : discard visited
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    if(dfs(board, word, visited, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] visited, int x, int y, int idx) {
        if (idx == word.length()) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y] || board[x][y] != word.charAt(idx)) return false;
       /*
        m1 : char c = board[x][y];
             board[x][y] = "#"
        m2 : board[x][y] ^= 256     //ASCII is 0 - 127, after ^= 256, this position become a invalid letter
             board[x][y] ^= 256
        */
        visited[x][y] = true;
        boolean res = false;
        for (int i = 0; i < dirs.length; i++) {
            res = res || dfs(board, word, visited, x + dirs[i][0], y + dirs[i][1], idx + 1);
        }
        visited[x][y] = false;
        return res;
    }
}

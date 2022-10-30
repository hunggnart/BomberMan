package main;

import java.util.*;

public class FindWayEnemy {
    public GamePanel gp;
    Random generator = new Random();

    public FindWayEnemy(GamePanel gp) {
        this.gp = gp;
    }

    static class pair {
        public int first, second;

        public pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static int ROW = 4;
    static int COL = 4;

    // Direction vectors
    static int dRow[] = {-1, 0, 1, 0};
    static int dCol[] = {0, 1, 0, -1};

    // Function to check if a cell
    static boolean isValid(int grid[][], boolean vis[][],
                           int row, int col, int tagetX, int tagetY) {
        if (row >= 0 && col >= 0 &&
                row < ROW && col < COL && !vis[row][col] &&
                row == tagetX && col == tagetY)
            return true;
        // If cell lies out of bounds
        if (row >= 0 && col >= 0 &&
                row < ROW && col < COL && !vis[row][col] &&
                grid[row][col] == 1)
            return true;
        return false;
    }

    static boolean isValid2(int grid[][], boolean vis[][],
                            int row, int col, int tagetX, int tagetY) {
        if (row >= 0 && col >= 0 &&
                row < ROW && col < COL && !vis[row][col] &&
                row == tagetX && col == tagetY)
            return true;
        // If cell lies out of bounds
        if (row >= 0 && col >= 0 &&
                row < ROW && col < COL && !vis[row][col] &&
                grid[row][col] == 1)
            return true;
        if (row >= 0 && col >= 0 &&
                row < ROW && col < COL && !vis[row][col] &&
                grid[row][col] == 0)
            return true;
        return false;
    }

    // Function to perform the BFS traversal
    static String BFS(int grid[][], boolean vis[][], String way[][],
                      int row, int col, int tagetRow, int tagetCol) {

        // Stores indices of the matrix cells
        Queue<pair> q = new LinkedList<>();

        // Mark the starting cell as visited
        // and push it into the queue
        q.add(new pair(row, col));
        vis[row][col] = true;
        if (row + 1 < ROW) {
            way[row + 1][col] = "down";
        }
        if (row - 1 > 0) {
            way[row - 1][col] = "up";
        }
        if (col + 1 < ROW) {
            way[row][col + 1] = "right";
        }
        if (col - 1 > 0) {
            way[row][col - 1] = "left";
        }
        // Iterate while the queue
        // is not empty
        while (!q.isEmpty()) {
            pair cell = q.peek();
            int x = cell.first;
            int y = cell.second;
            q.remove();
            // Go to the adjacent cells
            for (int i = 0; i < 4; i++) {
                int adjx = x + dRow[i];
                int adjy = y + dCol[i];
                if (isValid(grid, vis, adjx, adjy, tagetRow, tagetCol)) {
                    if (way[adjx][adjy].equals("none")) {
                        way[adjx][adjy] = way[x][y];
                    }
                    if (adjx == tagetRow && adjy == tagetCol) {
                        return way[adjx][adjy];
                    }
                    q.add(new pair(adjx, adjy));
                    vis[adjx][adjy] = true;
                }
            }
        }
        return "none";
    }

    static String BFS2(int grid[][], boolean vis[][], String way[][],
                       int row, int col, int tagetRow, int tagetCol) {

        // Stores indices of the matrix cells
        Queue<pair> q = new LinkedList<>();

        // Mark the starting cell as visited
        // and push it into the queue
        q.add(new pair(row, col));
        vis[row][col] = true;
        if (row + 1 < ROW) {
            way[row + 1][col] = "down";
        }
        if (row - 1 > 0) {
            way[row - 1][col] = "up";
        }
        if (col + 1 < ROW) {
            way[row][col + 1] = "right";
        }
        if (col - 1 > 0) {
            way[row][col - 1] = "left";
        }
        // Iterate while the queue
        // is not empty
        while (!q.isEmpty()) {
            pair cell = q.peek();
            int x = cell.first;
            int y = cell.second;
            q.remove();
            // Go to the adjacent cells
            for (int i = 0; i < 4; i++) {
                int adjx = x + dRow[i];
                int adjy = y + dCol[i];
                if (isValid2(grid, vis, adjx, adjy, tagetRow, tagetCol)) {
                    if (way[adjx][adjy].equals("none")) {
                        way[adjx][adjy] = way[x][y];
                    }
                    if (adjx == tagetRow && adjy == tagetCol) {
                        return way[adjx][adjy];
                    }
                    q.add(new pair(adjx, adjy));
                    vis[adjx][adjy] = true;
                }
            }
        }
        return "none";
    }

    public String FindWayForEnemy(int x, int y) {
        int[][] map = new int[gp.maxScreenRow][gp.maxScreenCol];
        boolean[][] vis = new boolean[gp.maxScreenRow][gp.maxScreenCol];
        String[][] way = new String[gp.maxScreenRow][gp.maxScreenCol];

        ROW = gp.maxScreenRow;
        COL = gp.maxScreenCol;

        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                map[i][j] = gp.tileM.mapTileNum[i][j];
                vis[i][j] = false;
                way[i][j] = "none";
            }
        }

        for (int i = 0; i < gp.bombM.bombs.size(); i++) {
            map[gp.bombM.bombs.get(i).y / gp.tileSize][gp.bombM.bombs.get(i).x / gp.tileSize] = 9;
        }
        int tagetRow = (gp.player.y + gp.tileSize / 2) / gp.tileSize;
        int tagetCol = (gp.player.x + gp.tileSize / 2) / gp.tileSize;

        String result = BFS(map, vis, way, x, y, tagetRow, tagetCol);
        return result;
    }

    public String FindWayForEnemy1(int x, int y) {
        String rs = "";
        List<String> ways = new ArrayList<String>();
        int[][] map = new int[gp.maxScreenRow][gp.maxScreenCol];
        ROW = gp.maxScreenRow;
        COL = gp.maxScreenCol;

        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                map[i][j] = gp.tileM.mapTileNum[i][j];
            }
        }

        for (int i = 0; i < gp.bombM.bombs.size(); i++) {
            map[gp.bombM.bombs.get(i).y / gp.tileSize][gp.bombM.bombs.get(i).x / gp.tileSize] = 9;
        }

        if (map[x + 1][y] == 1) {
            ways.add("down");
        }

        if (map[x - 1][y] == 1) {
            ways.add("up");
        }
        if (map[x][y + 1] == 1) {
            ways.add("right");
        }
        if (map[x][y - 1] == 1) {
            ways.add("left");
        }
        rs = ways.get(generator.nextInt(ways.size()));
        return rs;
    }

    public String FindWayForEnemy3(int x, int y) {
        String rs = "";
        List<String> ways = new ArrayList<String>();
        int[][] map = new int[gp.maxScreenRow][gp.maxScreenCol];
        ROW = gp.maxScreenRow;
        COL = gp.maxScreenCol;

        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                map[i][j] = gp.tileM.mapTileNum[i][j];
            }
        }

        for (int i = 0; i < gp.bombM.bombs.size(); i++) {
            map[gp.bombM.bombs.get(i).y / gp.tileSize][gp.bombM.bombs.get(i).x / gp.tileSize] = 9;
        }

        if (map[x + 1][y] == 1 || map[x + 1][y] == 0) {
            ways.add("down");
        }

        if (map[x - 1][y] == 1 || map[x - 1][y] == 0) {
            ways.add("up");
        }
        if (map[x][y + 1] == 1 || map[x][y + 1] == 0) {
            ways.add("right");
        }
        if (map[x][y - 1] == 1 || map[x][y - 1] == 0) {
            ways.add("left");
        }
        rs = ways.get(generator.nextInt(ways.size()));
        return rs;
    }

    public String FindWayForEnemy2(int x, int y) {
        int[][] map = new int[gp.maxScreenRow][gp.maxScreenCol];
        boolean[][] vis = new boolean[gp.maxScreenRow][gp.maxScreenCol];
        String[][] way = new String[gp.maxScreenRow][gp.maxScreenCol];

        ROW = gp.maxScreenRow;
        COL = gp.maxScreenCol;

        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                map[i][j] = gp.tileM.mapTileNum[i][j];
                vis[i][j] = false;
                way[i][j] = "none";
            }
        }

        for (int i = 0; i < gp.bombM.bombs.size(); i++) {
            map[gp.bombM.bombs.get(i).y / gp.tileSize][gp.bombM.bombs.get(i).x / gp.tileSize] = 9;
        }
        int tagetRow = (gp.player.y + gp.tileSize / 2) / gp.tileSize;
        int tagetCol = (gp.player.x + gp.tileSize / 2) / gp.tileSize;

        String result = BFS2(map, vis, way, x, y, tagetRow, tagetCol);
        return result;
    }
}

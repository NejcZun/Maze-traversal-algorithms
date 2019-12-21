package algoritmi;

import csv.Parser;

import java.util.*;

public class BFS {
    static int ROW;
    static int COL;
    List<List<Integer>> maze;
    private static Point source;
    private static Point dest;

    int distance;

    public BFS(Parser maze){
        this.maze = maze.data;
        ROW = maze.data.size();
        COL = maze.data.get(0).size();
        source = new Point(maze.start.x, maze.start.y);
        dest = new Point(maze.end.x, maze.end.y);
        this.distance = BFS(this.maze, source, dest);
    }
    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class queueNode {
        Point pt;
        int dist;

        public queueNode(Point pt, int dist) {
            this.pt = pt;
            this.dist = dist;
        }
    }
    static boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    //4 sosedi (gor dol levo desno)
    static int rowNum[] = {-1, 0, 0, 1};
    static int colNum[] = {0, -1, 1, 0};

    public static int BFS(List<List<Integer>> mat, Point src, Point dest) {
        if (mat.get(src.y).get(src.x) == -1 || mat.get(dest.y).get(dest.x) == -1) return -1;

        boolean [][]visited = new boolean[ROW][COL];

        visited[src.x][src.y] = true;

        Queue<queueNode> q = new LinkedList<>();

        queueNode s = new queueNode(src, 0);
        q.add(s);

        while (!q.isEmpty()) {
            queueNode curr = q.peek();
            Point pt = curr.pt;
            if (pt.x == dest.x && pt.y == dest.y){
                return curr.dist;
            }
            q.remove();

            for (int i = 0; i < 4; i++) {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];
                if (isValid(row, col) && mat.get(col).get(row) != -1 && !visited[row][col]){
                    //System.out.println("x: " + row + " y: " + col);
                    visited[row][col] = true;
                    queueNode Adjcell = new queueNode(new Point(row, col), curr.dist + 1 );
                    q.add(Adjcell);
                }
            }
        }

        return -1;
    }
    public void getDistance(){
        if (this.distance != Integer.MAX_VALUE)
            System.out.println("Shortest Path is " + this.distance);
        else
            System.out.println("Shortest Path doesn't exist");
    }
}
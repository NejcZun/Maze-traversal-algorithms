package algoritmi;

import csv.Parser;

import java.util.*;

public class BFS extends Point {
    static int ROW;
    static int COL;
    List<List<Integer>> maze;
    List<Point> path = new ArrayList<>();
    private static Point source;
    private static List<Point> dest = new ArrayList<>();
    int visited_vertexes = 0;
    int distance;

    public BFS(Parser maze) {
        this.maze = maze.data;
        ROW = maze.data.size();
        COL = maze.data.get(0).size();
        source = new Point(maze.start.x, maze.start.y);
        for (Point point : maze.end) {
            dest.add(new Point(point.x, point.y));
        }
        this.distance = BFS(this.maze, source, dest);
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

    public int BFS(List<List<Integer>> mat, Point src, List<Point> dest) {
        if (mat.get(src.y).get(src.x) == -1) return -1;

        boolean[][] visited = new boolean[ROW][COL];

        visited[src.x][src.y] = true;

        Queue<queueNode> q = new LinkedList<>();

        queueNode s = new queueNode(src, 0);
        q.add(s);

        while (!q.isEmpty()) {
            queueNode curr = q.peek();
            Point pt = curr.pt;
            //ce pride do kateregakoli izhoda.
            for (Point finish : dest) {
                if (pt.x == finish.x && pt.y == finish.y) {
                    return curr.dist;
                }
            }
            q.remove();

            for (int i = 0; i < 4; i++) {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];
                if (isValid(row, col) && mat.get(col).get(row) != -1 && !visited[row][col]) {
                    //System.out.println("x: " + row + " y: " + col);
                    visited_vertexes++;
                    visited[row][col] = true;
                    queueNode Adjcell = new queueNode(new Point(row, col), curr.dist + 1);
                    q.add(Adjcell);
                }
            }
        }

        return -1;
    }

    public void getDistance() {
        if (this.distance != Integer.MAX_VALUE) System.out.println("Shortest Path is " + this.distance);
        else System.out.println("Shortest Path doesn't exist");
    }
    public void getStatistics(){
        System.out.println("Number of visited vertexes: " + visited_vertexes);
    }
}
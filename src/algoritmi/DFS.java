package algoritmi;

import csv.Parser;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.List;

public class DFS {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int ROW;
    static int COL;
    private boolean[][] visited;
    private Point start;
    private static List<Point> dest = new ArrayList<>();
    private List<Point> path;
    public List<Point> result = new ArrayList<>();

    List<List<Integer>> maze;
    public DFS(Parser maze){
        this.maze = maze.data;
        this.start = maze.start;
        this.ROW = maze.data.size();
        this.COL = maze.data.get(0).size();
        this.visited = new boolean[ROW][COL];
        for (Point point : maze.end) {
            dest.add(new Point(point.x, point.y));
        }
        this.path = solve();
    }

    public List<Point> solve() {
        List<Point> path = new ArrayList<>();
        if (explore(this.start.getX(), this.start.getY(), path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(int row, int col, List<Point> path) {
        if (!isValid(row, col) || isWall(row, col) || isExplored(row, col)) {
            return false;
        }

        path.add(new Point(row, col));
        setVisited(row, col, true);

        if (isExit(row, col)) {
            return true;
        }

        for (int[] direction : DIRECTIONS) {
            Point point = getNextCoordinate(row, col, direction[0], direction[1]);
            if (explore(point.getX(), point.getY(), path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private Point getNextCoordinate(int row, int col, int i, int j) {
        return new Point(row + i, col + j);
    }

    static boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    public boolean isWall(int row, int col) {
        return this.maze.get(col).get(row) == -1;
    }

    public boolean isExit(int row, int col) {
        for(Point end : dest)if(end.x == row && end.y == col) return true;
        return false;
    }

    public void setVisited(int row, int col, boolean value) {
        this.visited[row][col] = value;
    }

    public boolean isExplored(int row, int col) {
        return this.visited[row][col];
    }



    /* -------------------------------------------------------- */


    public void getPath(){
        System.out.print("Path: ");
        for(int i=0; i<this.path.size();i++){
            if(this.path.size()-1 == i)System.out.println("(" + this.path.get(i).x + ", " + this.path.get(i).y + ")");
            else System.out.print("(" + this.path.get(i).x + ", " + this.path.get(i).y + "), ");
            result.add(new Point(this.path.get(i).getX(), this.path.get(i).getY()));
        }
    }
    public void getDistance(){
        System.out.println("Shortest path: " + this.path.size());
    }
    public void getCost(){
        int cost = 0;
        for(int i=1; i<this.path.size()-1;i++) {
            cost+= this.maze.get(this.path.get(i).y).get(this.path.get(i).x);
        }
        System.out.println("Cost: " + cost);
    }
    public void getStatistics(){
        int num_of_visited = 0;
        for(int i=0; i<visited.length;i++){
            for(int j=0;j<visited[0].length;j++)if(visited[i][j])num_of_visited++;
        }
        System.out.println("Number of visits: " + num_of_visited);
    }
}
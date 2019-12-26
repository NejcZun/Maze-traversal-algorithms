package algoritmi;

import csv.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BFS{
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int ROW;
    static int COL;
    private boolean[][] visited;
    private Point start;
    private static List<Point> dest = new ArrayList<>();
    private List<Point> path;
    public List<Point> result = new ArrayList<>();
    List<List<Integer>> maze;

    public BFS(Parser maze){
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
        LinkedList<Point> nextToVisit = new LinkedList<>();
        Point start = this.start;
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Point cur = nextToVisit.remove();

            if (!isValid(cur.getX(), cur.getY()) || isExplored(cur.getX(), cur.getY())) continue;


            if (isWall(cur.getX(), cur.getY())) {
                setVisited(cur.getX(), cur.getY(), true);
                continue;
            }
            for (Point finish : dest) {
                if (cur.getX() == finish.x && cur.getY() == finish.y) {
                    return backtrackPath(cur);
                }
            }
            for (int[] direction : DIRECTIONS) {
                Point coordinate = new Point(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    static boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    public boolean isWall(int row, int col) {
        return this.maze.get(col).get(row) == -1;
    }

    public void setVisited(int row, int col, boolean value) {
        this.visited[row][col] = value;
    }

    public boolean isExplored(int row, int col) {
        return this.visited[row][col];
    }

    private List<Point> backtrackPath(Point cur) {
        List<Point> path = new ArrayList<>();
        Point iter = cur;
        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
    public void getPath(){
        System.out.print("Path: ");
        for(int i=this.path.size()-1; i>=0;i--){
            if(i == 0)System.out.println("(" + this.path.get(i).x + ", " + this.path.get(i).y + ")");
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
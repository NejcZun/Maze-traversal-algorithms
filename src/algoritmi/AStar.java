package algoritmi;

import csv.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private List<Point> open;
    private List<List<Boolean>> closed;
    private List<List<Point>> from;
    private static int ROW;
    private static int COL;

    private List<Point> path;
    public List<Point> result;
    private List<List<Integer>> maze;

    private List<List<Integer>> gScore;
    private List<List<Integer>> fScore;
    private List<List<Integer>> hScore;

    private Point start;
    private List<Point> ends;
    private Point closest_end;

    public AStar(Parser maze){
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.from = new ArrayList<>();
        this.ends = new ArrayList<>();
        this.result = new ArrayList<>();

        this.hScore = new ArrayList<>();
        this.gScore = new ArrayList<>();
        this.fScore = new ArrayList<>();


        this.maze = maze.data;
        this.start = maze.start;
        this.ROW = maze.data.size();
        this.COL = maze.data.get(0).size();
        this.closest_end = maze.end.get(0);
        for(Point end : maze.end){
            if(Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2)) < Math.sqrt(Math.pow(closest_end.x - start.x, 2) + Math.pow(closest_end.y - start.y, 2))){
                this.closest_end = end;
            }
            ends.add(end);
        }
        AStarPath();
    }
    List<Point> AStarPath(){
        for(int y=0;y<this.maze.size();y++) {
            hScore.add(new ArrayList<>());
            gScore.add(new ArrayList<>());
            fScore.add(new ArrayList<>());
            from.add(new ArrayList<>());
            closed.add(new ArrayList<>());
            for (int x = 0; x < this.maze.get(y).size(); x++) {
                hScore.get(y).add(Math.abs(closest_end.x - x) + Math.abs(closest_end.y - y));
                gScore.get(y).add(Integer.MAX_VALUE);
                fScore.get(y).add(Integer.MAX_VALUE);
                from.get(y).add(new Point(-1, -1));
                closed.get(y).add(false);
            }
        }

        gScore.get(start.y).set(start.x, 0);
        fScore.get(start.y).set(start.x, hScore.get(start.y).get(start.x));
        from.get(start.y).set(start.x, new Point(-1, -1));

        open.add(start);


        while(!open.isEmpty()){
            int minVal = Integer.MAX_VALUE;
            int minPos = 0;
            Point cur = new Point();

            for(int i=0; i<open.size();i++){
                Point point = open.get(i);
                if(fScore.get(point.y).get(point.x) < minVal){
                    minVal = fScore.get(point.y).get(point.x);
                    minPos = i;
                    cur = point;
                }
            }
            open.remove(minPos);
            closed.get(cur.y).set(cur.x, true);

            if(ends.contains(cur)){
                while(true){
                    cur = from.get(cur.y).get(cur.x);
                    if(cur.x != -1 && cur.y != -1) {
                        path.add(cur);
                        System.out.print("(" + cur.x + ", " + cur.y + "), ");

                    } else break;
                }
                return null;
            }
            for (int[] direction : DIRECTIONS) {
                Point nextPoint = getNextCoordinate(cur.x, cur.y, direction[0], direction[1]);
                if(isValid(nextPoint.x, nextPoint.y) && !isWall(nextPoint.x, nextPoint.y) && !closed.get(nextPoint.y).get(nextPoint.x)){
                    open.add(nextPoint);
                    int dist = gScore.get(cur.y).get(cur.x) + this.maze.get(nextPoint.y).get(nextPoint.x);

                    if(dist < gScore.get(nextPoint.y).get(nextPoint.x)){
                        from.get(nextPoint.y).set(nextPoint.x, cur);
                        gScore.get(nextPoint.y).set(nextPoint.x, dist);
                        fScore.get(nextPoint.y).set(nextPoint.x, gScore.get(nextPoint.y).get(nextPoint.x) + hScore.get(nextPoint.y).get(nextPoint.x));
                    }

                }

            }
        }
        return path;
    }




    public boolean isWall(int row, int col) {
        return this.maze.get(col).get(row) == -1;
    }

    static boolean isValid(int row, int col) { return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL); }

    private Point getNextCoordinate(int row, int col, int i, int j) {
        return new Point(row + i, col + j);
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
}



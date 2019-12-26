package algoritmi;

import csv.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {
    /*private List<Point> open;
    private List<Point> closed;
    private List<Point> path;
    private List<List<Integer>> maze;
    private Point now;
    private Point start;
    private List<Point> ends;

    AStar(Parser maze){
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.maze = maze.data;
        this.now = new Point(maze.start.x, maze.start.y, null, 0, 0);
        this.start = maze.start;
        for(Point end : maze.end){
            ends.add(end);
        }
    }
    public List<Point> findPathTo(){
        this.closed.add(this.now);
        addNeigborsToOpenList();
        while
    }
    private void addNeigborsToOpenList() {
        Point point;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                point = new Point(this.now.x + x, this.now.y + y,this.now ,this.now.g, this.distance(x, y));
                if ((x != 0 || y != 0) // not this.now
                        && this.now.x + x >= 0 && this.now.x + x < this.maze.get(0).size()// check maze boundaries
                        && this.now.y + y >= 0 && this.now.y + y < this.maze.size()
                        && this.maze.get(this.now.y + y).get(this.now.x + x) != -1 // check if square is walkable
                        && !findNeighborInList(this.open, point) && !findNeighborInList(this.closed, point)) { // if not already done
                    point.g = point.parent.g + 1.; // Horizontal/vertical cost = 1.0
                    point.g += maze.get(this.now.y + y).get(this.now.x + x); // add movement cost for this square
                    this.open.add(point);
                }
            }
        }
        Collections.sort(this.open);
    }
    private double distance(int dx, int dy) {
        return Math.abs(this.now.x + dx - this.xend) + Math.abs(this.now.y + dy - this.yend); // else return "Manhattan distance"
    }
    private static boolean findNeighborInList(List<Point> array, Point point) {
        return array.stream().anyMatch((n) -> (n.x == point.x && n.y == point.y));
    }*/
}

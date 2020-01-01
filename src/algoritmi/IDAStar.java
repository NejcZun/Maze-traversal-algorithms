package algoritmi;

import csv.Parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IDAStar {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int ROW;
    static int COL;

    private Point start;
    private static List<Point> dest = new ArrayList<>();
    private List<Point> path;

    private Point closest_end = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public List<Point> result = new ArrayList<>();

    List<List<Integer>> maze;

    private int[][] searchHeurCost;

    public int cost;
    public int num_of_visits;

    private boolean found;


    private LinkedList<Point> pot;

    public IDAStar(Parser maze) {
        this.maze = maze.data;
        this.start = maze.start;
        this.ROW = maze.data.size();
        this.COL = maze.data.get(0).size();

        this.cost=0;
        this.num_of_visits = 0;

        searchHeurCost = new int[ROW][COL];


        for (Point end : maze.end) {
            dest.add(new Point(end.x, end.y));
            if (Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2)) < Math.sqrt(Math.pow(closest_end.x - start.x, 2) + Math.pow(closest_end.y - start.y, 2))) {
                this.closest_end = end;
            }
        }

        for (int x = 0; x < this.maze.size(); x++){
            for (int y = 0; y < this.maze.get(0).size(); y++) {
                searchHeurCost[x][y] = Math.abs(this.closest_end.x - x) + Math.abs(this.closest_end.y - y);
            }
        }
        path = IDAStarFind();

    }

    private int IDAStarSearch(int gScore, int bound) {

        Point curNode = pot.get(0);
        int fScore = gScore + searchHeurCost[curNode.x][curNode.y];

        if(fScore > bound){
            //System.out.println("Vozlisce " + curNode.toString() + " je zunaj trenutne meje (razdalja " + fScore + ")");
            return fScore;
        }

        //System.out.println("Vozlisce " + curNode.toString() + " je znotraj trenutne meje.");

        if(isExit(curNode.x, curNode.y)){
            found = true;
            return fScore;
        }

        int min = Integer.MAX_VALUE;

        for (int[] direction : DIRECTIONS) {
            Point nextNode = getNextCoordinate(curNode.x, curNode.y, direction[0], direction[1]);

            if(isValid(nextNode.x, nextNode.y) && !isWall(nextNode.x, nextNode.y)){

                if(!inPot(nextNode.x, nextNode.y)){

                    pot.add(0, nextNode);
                    int res = IDAStarSearch(gScore + this.maze.get(nextNode.y).get(nextNode.x), bound);

                    if(found)
                        return res;

                    if(res < min)
                        min = res;

                    pot.remove(0);
                }
            }
        }
        return min;
    }

    private List<Point> IDAStarFind(){

        pot = new LinkedList<>();
        pot.add(this.start);
        found = false;

        int bound = searchHeurCost[this.start.x][this.start.y];

        while(true){
            //System.out.println("Meja iskanja je nastavljena na: " + bound);

            int res = IDAStarSearch(0, bound);

            if(found){
                System.out.println("Resitev IDA* je v vozliscu " + pot.get(0).toString());
                System.out.println("Najdena resitev na razdalji: " + res);
                /*
                System.out.println("Najdena pot: ");
                for(int i = 0; i < pot.size(); i++){

                    if(i > 0)
                        System.out.print(" <-- ");
                    System.out.print(pot.get(i).toString());

                }
                */
                path = new ArrayList<>(pot);
                break;

            }
            if(res == Integer.MAX_VALUE){
                System.out.println("Iz zacetnega vozlisca ni mozno priti do nobenega ciljnega vozlisca!");
                break;
            }
            bound = res;

        }
        return pot;
    }

    public boolean inPot(int row, int col){
        for(Point pt : this.pot) if(pt.x == row && pt.y == col) return true;
        return false;
    }

    public boolean isWall(int row, int col) {
        return this.maze.get(col).get(row) == -1;
    }

    boolean isValid(int row, int col) {
        this.num_of_visits++;
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    private Point getNextCoordinate(int row, int col, int i, int j) {
        return new Point(row + i, col + j);
    }

    public boolean isExit(int row, int col) {
        for(Point end : dest)if(end.x == row && end.y == col) return true;
        return false;
    }


    /* -------------------------------------------------------- */


    public void getPath(){
        System.out.print("Path: ");
        System.out.println(this.path.size());
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
        this.cost = cost;
    }

}
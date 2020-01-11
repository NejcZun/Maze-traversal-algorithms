package algoritmi;

import csv.Parser;

import java.util.*;


public class Dijkstra {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int ROW;
    static int COL;

    private Point start;
    private static List<Point> dest = new ArrayList<>();
    private List<Point> path;

    private Point closest_end = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public List<Point> result = new ArrayList<>();

    List<List<Integer>> maze;

    private int[][] f_score;
    private int[][] g_score;
    //private int[][] h_score;

    public int cost;
    public int num_of_visits;

    public Dijkstra(Parser maze) {
        this.maze = maze.data;
        this.start = maze.start;
        this.ROW = maze.data.size();
        this.COL = maze.data.get(0).size();

        this.cost=0;
        this.num_of_visits = 0;

        f_score = new int[ROW][COL];
        //h_score = new int[ROW][COL];
        g_score = new int[ROW][COL];


        for (Point end : maze.end) {
            dest.add(new Point(end.x, end.y));
            if (Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2)) < Math.sqrt(Math.pow(closest_end.x - start.x, 2) + Math.pow(closest_end.y - start.y, 2))) {
                this.closest_end = end;
            }
        }

        for (int x = 0; x < this.maze.size(); x++){
            for (int y = 0; y < this.maze.get(0).size(); y++) {
                //h_score[x][y] = Math.abs(this.closest_end.x - x) + Math.abs(this.closest_end.y - y);
                g_score[x][y] = Integer.MAX_VALUE;
                f_score[x][y] = Integer.MAX_VALUE;

            }
        }
        path = DijkstraPath();

    }

    private List<Point> DijkstraPath() {

        List<Point> open = new ArrayList<>();
        List<Point> from = new ArrayList<>();

        boolean[][] closed_bool = new boolean[ROW][COL];

        HashMap<Point, Point> pathReconstruction = new HashMap<>();

        g_score[this.start.x][this.start.y] = 0;
        f_score[this.start.x][this.start.y] = this.maze.get(this.start.y).get(this.start.x);

        pathReconstruction.put(new Point(-1, -1), this.start);

        open.add(start);

        while(!open.isEmpty()){

            int minVal = Integer.MAX_VALUE;
            int minPos = 0;
            Point curNode = new Point();

            for(int i = 0; i < open.size(); i++){
                Point node = open.get(i);
                if(f_score[node.x][node.y] < minVal){
                    minVal = f_score[node.x][node.y];
                    minPos = i;
                    curNode = node;
                }
            }

            open.remove(minPos);
            closed_bool[curNode.x][curNode.y] = true;
            //System.out.println("Zapiram vozlišče: " + curNode.toString());

            pathReconstruction.put(this.start, curNode);

            if(isExit(curNode.x, curNode.y)){

                while(true){
                    curNode = pathReconstruction.get(curNode);
                    if(curNode == this.start){
                        break;
                    }else
                        from.add(curNode);
                }

                return from;
            }

            for (int[] direction : DIRECTIONS) {
                Point nextNode = getNextCoordinate(curNode.x, curNode.y, direction[0], direction[1]);

                if(isValid(nextNode.x, nextNode.y) && !isWall(nextNode.x, nextNode.y) && !closed_bool[nextNode.x][nextNode.y]){

                    if(!open.contains(nextNode)){
                        //System.out.println("Odpiram vozlisce " + nextNode.toString());
                    }

                    open.add(nextNode);
                    int dist = g_score[curNode.x][curNode.y] + this.maze.get(nextNode.x).get(nextNode.y);

                    if(dist < g_score[nextNode.x][nextNode.y]){
                        pathReconstruction.put(nextNode, curNode);
                        g_score[nextNode.x][nextNode.y] = dist;
                        f_score[nextNode.x][nextNode.y] = g_score[nextNode.x][nextNode.y];
                        //System.out.println("Posodabljam vozlisce " + nextNode.toString());
                    }
                }
            }
        }
        return null;
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
        for(int i=0; i<this.path.size();i++){
            if(this.path.size()-1 == i)System.out.println("(" + this.path.get(i).x + ", " + this.path.get(i).y + ")");
            else System.out.print("(" + this.path.get(i).x + ", " + this.path.get(i).y + "), ");
            result.add(new Point(this.path.get(i).getX(), this.path.get(i).getY()));
        }
    }
    public void getDistance(){
        System.out.println("Shortest path: " + (this.path.size() + 2)); //isto kot AStar ne upostevava zacetka pa konca tko da evo se ta +2
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



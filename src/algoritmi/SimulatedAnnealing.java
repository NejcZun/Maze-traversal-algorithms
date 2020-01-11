package algoritmi;

import csv.Parser;

import java.util.*;


public class SimulatedAnnealing {
    static int ROW;
    static int COL;

    private Point start;
    private List<Point> path;

    private int iter = 0;

    private List<Point> currentOrder;
    private List<Point> nextOrder = new ArrayList<>();

    private int[][] distances;
    private int[][] h_score;

    private Random random = new Random();

    public List<Point> result = new ArrayList<>();

    List<List<Integer>> maze;


    public int cost;
    public int num_of_visits;

    Parser mz;


    public SimulatedAnnealing(Parser maze) {
        this.maze = maze.data;
        this.start = maze.start;
        this.ROW = maze.data.size();
        this.COL = maze.data.get(0).size();
        this.mz = maze;

        this.cost=0;
        this.num_of_visits = 0;

        this.distances = new int[ROW][COL];

        for(int i = 0; i < COL; i++){
            for(int j = 0; j < ROW; j++){
                this.distances[i][j] = this.maze.get(j).get(i);
            }
        }

        this.h_score = new int[ROW][COL];
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                this.h_score[i][j] = random.nextInt(1000);
            }
        }

        AStar aStar = new AStar(maze, this.h_score, false);
        num_of_visits += aStar.num_of_visits;
        currentOrder = aStar.retPath();

        path = Anneal();

    }

    private long getTotalDistance(List<Point> order){

        long distance = 0;

        for(int  i = 0; i < order.size() - 1; i++){
            distance += this.distances[order.get(i).x][order.get(i).y];
        }

        return distance;
    }

    private List<Point> getNextOrder(List<Point> order){

        for(Point ptr : order){
            this.h_score[ptr.x][ptr.y] = this.maze.get(ptr.y).get(ptr.x);
        }

        AStar aStar = new AStar(this.mz, this.h_score, false);
        num_of_visits += aStar.num_of_visits;
        return aStar.retPath();

    }

    private List<Point> Anneal(){
        double temp = 10000.0;
        double deltaDist;
        double coolingRate = 0.8888;
        double absoluteTemp = 0.00001;

        double distance = getTotalDistance(currentOrder);

        while(temp > absoluteTemp){
            nextOrder = getNextOrder(currentOrder);

            deltaDist = getTotalDistance(nextOrder);

            if( deltaDist < distance ){
                currentOrder = nextOrder;
            }

            temp *= coolingRate;

            iter++;
        }

        return currentOrder;
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
        System.out.println("Shortest path: " + (this.path.size() +2 )); //evo zdej vsi upostevajo zacetk pa konc (+2)
    }
    public void getCost(){
        int cost = 0;
        for(int i=1; i<this.path.size()-1;i++) {
            cost+= this.maze.get(this.path.get(i).y).get(this.path.get(i).x);
        }
        System.out.println("Cost: " + cost);
        this.cost = cost;
    }

    public int getIters(){
        return this.iter;
    }

}



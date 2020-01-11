package main;

import algoritmi.*;

import csv.Parser;
import draw.Maze;

public class Main {
    public static void main(String args[]) {
        Parser maze = new Parser("labyrinths/labyrinth_1.txt");
        //maze.print();

        System.out.println("\nDFS: ");
        long start_dfs = System.currentTimeMillis();
        DFS dfs = new DFS(maze);
        dfs.getPath();
        long stop_dfs = System.currentTimeMillis();
        dfs.getDistance();
        dfs.getCost();
        dfs.getStatistics();
        System.out.println("Elapsed time: " + (stop_dfs - start_dfs)); //ms
        //maze.printSolution(dfs.result);

        System.out.println("\nBFS: ");
        long start_bfs = System.currentTimeMillis();
        BFS bfs = new BFS(maze);
        bfs.getPath();
        long stop_bfs = System.currentTimeMillis();
        bfs.getDistance();
        bfs.getCost();
        bfs.getStatistics();
        System.out.println("Elapsed time: " + (stop_bfs - start_bfs)); //ms
        //maze.printSolution(bfs.result);


        System.out.println("\nIDDFS: ");
        long start_iddfs = System.currentTimeMillis();
        IDDFS iddfs = new IDDFS(maze);
        iddfs.getPath();
        long stop_iddfs = System.currentTimeMillis();
        iddfs.getDistance();
        iddfs.getCost();
        iddfs.getStatistics();
        System.out.println("Elapsed time: " + (stop_iddfs - start_iddfs)); //ms
        //maze.printSolution(iddfs.result);

        System.out.println("\nAStar: ");
        long start_astar= System.currentTimeMillis();
        AStar aStar = new AStar(maze, new int[maze.data.size()][maze.data.get(0).size()], true);
        aStar.getPath();
        long stop_astar = System.currentTimeMillis();
        aStar.getDistance();
        aStar.getCost();
        aStar.getStatistics();
        System.out.println("Elapsed time: " + (stop_astar - start_astar)); //ms
        //maze.printSolution(aStar.result);


        System.out.println("\nIdaStar: ");
        long start_idastar = System.currentTimeMillis();
        IDAStar idaStar = new IDAStar(maze);
        idaStar.getPath();
        long stop_idastar = System.currentTimeMillis();
        idaStar.getDistance();
        idaStar.getCost();
        System.out.println("Elapsed time: " + (stop_idastar - start_idastar)); //ms
        //maze.printSolution(idaStar.result);

        System.out.println("\nDijkstra: ");
        long start_dijkstra = System.currentTimeMillis();
        Dijkstra dijkstra = new Dijkstra(maze);
        dijkstra.getPath();
        long stop_dijkstra = System.currentTimeMillis();
        dijkstra.getDistance();
        dijkstra.getCost();
        System.out.println("Elapsed time: " + (stop_dijkstra - start_dijkstra)); //ms
        //maze.printSolution(idaStar.result);

        System.out.println("\nSA: ");
        long start_sa = System.currentTimeMillis();
        SimulatedAnnealing sa = new SimulatedAnnealing(maze);
        sa.getPath();
        long stop_sa = System.currentTimeMillis();
        sa.getDistance();
        sa.getCost();
        System.out.println("Number of iterations: " + sa.getIters());
        System.out.println("Elapsed time: " + (stop_sa - start_sa)); //ms
        //maze.printSolution(sa.result);

        new Maze(maze.data, dfs, bfs, iddfs, aStar, idaStar, dijkstra, sa);
    }
}

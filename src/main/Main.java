package main;

import algoritmi.*;

import csv.Parser;
import draw.Maze;

public class Main {
    public static void main(String args[]) {
        Parser maze = new Parser("labyrinths/labyrinth_12.txt");
        //maze.print();


        System.out.println("\nDFS: ");
        DFS dfs = new DFS(maze);
        dfs.getPath();
        dfs.getDistance();
        dfs.getCost();
        dfs.getStatistics();
        //maze.printSolution(dfs.result);

        System.out.println("\nBFS: ");
        BFS bfs = new BFS(maze);
        bfs.getPath();
        bfs.getDistance();
        bfs.getCost();
        bfs.getStatistics();
        //maze.printSolution(bfs.result);


        System.out.println("\nIDDFS: ");
        IDDFS iddfs = new IDDFS(maze);
        iddfs.getPath();
        iddfs.getDistance();
        iddfs.getCost();
        iddfs.getStatistics();
        //maze.printSolution(iddfs.result);

        System.out.println("\nAStar: ");
        AStar aStar = new AStar(maze, new int[maze.data.size()][maze.data.get(0).size()], true);
        aStar.getPath();
        aStar.getDistance();
        aStar.getCost();
        //maze.printSolution(aStar.result);


        System.out.println("\nIdaStar: ");
        IDAStar idaStar = new IDAStar(maze);
        idaStar.getPath();
        idaStar.getDistance();
        idaStar.getCost();
        //maze.printSolution(idaStar.result);

        System.out.println("\nDijkstra: ");
        Dijkstra dijkstra = new Dijkstra(maze);
        dijkstra.getPath();
        dijkstra.getDistance();
        dijkstra.getCost();
        //maze.printSolution(idaStar.result);

        System.out.println("\nSA: ");
        SimulatedAnnealing sa = new SimulatedAnnealing(maze);
        sa.getPath();
        sa.getDistance();
        sa.getCost();
        System.out.println("Number of iterations: " + sa.getIters());
        //maze.printSolution(sa.result);

        new Maze(maze.data, dfs, bfs, iddfs, aStar, idaStar, dijkstra, sa);
    }
}

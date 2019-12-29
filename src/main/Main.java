package main;

import algoritmi.AStar;
import algoritmi.BFS;
import algoritmi.DFS;

import algoritmi.IDDFS;
import csv.Parser;

public class Main {
    public static void main(String args[]) {
        Parser maze = new Parser("labyrinths/labyrinth_1.txt");
        //maze.print();
        /*System.out.println("\nDFS: ");
        DFS dfs = new DFS(maze);
        dfs.getPath();
        dfs.getDistance();
        dfs.getCost();
        dfs.getStatistics();
        maze.printSolution(dfs.result);

        System.out.println("\nBFS: ");
        BFS bfs = new BFS(maze);
        bfs.getPath();
        bfs.getDistance();
        bfs.getCost();
        bfs.getStatistics();
        maze.printSolution(bfs.result);


        System.out.println("\nIDDFS: ");
        IDDFS iddfs = new IDDFS(maze);
        iddfs.getPath();
        iddfs.getDistance();
        iddfs.getCost();
        iddfs.getStatistics();
        maze.printSolution(iddfs.result);*/

        System.out.println("\nAStar: ");
        AStar aStar = new AStar(maze);
        aStar.getPath();
        aStar.getDistance();
        aStar.getCost();
        maze.printSolution(aStar.result);


    }
}

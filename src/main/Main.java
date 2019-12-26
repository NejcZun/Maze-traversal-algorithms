package main;

import algoritmi.BFS;
import algoritmi.DFS;

import csv.Parser;

public class Main {
    public static void main(String args[]) {
        Parser maze = new Parser("labyrinths/labyrinth_8.txt");
        //maze.print();
        System.out.println("\nDFS: ");
        DFS dfs = new DFS(maze);
        dfs.getPath();
        dfs.getDistance();
        dfs.getCost();
        dfs.getStatistics();
        maze.printSolution(dfs.result);

        System.out.println("BFS: ");
        BFS bfs = new BFS(maze);
        bfs.getPath();
        bfs.getDistance();
        bfs.getCost();
        bfs.getStatistics();
        maze.printSolution(bfs.result);
    }

}

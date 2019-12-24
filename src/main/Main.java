package main;

import algoritmi.BFS;
import csv.Parser;

public class Main {
    public static void main(String args[]) {
        Parser lab1 = new Parser("labyrinths/labyrinth_1.txt");
        lab1.print();
        BFS bfs = new BFS(lab1);
        bfs.getDistance();
        bfs.getStatistics();
    }

}

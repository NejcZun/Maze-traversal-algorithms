package main;

import csv.Parser;

public class Main {
    public static void main(String args[]) {
        Parser lab1 = new Parser("labyrinths/labyrinth_1.txt");
        lab1.print();
    }
}

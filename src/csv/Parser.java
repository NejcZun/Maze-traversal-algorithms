package csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public class Point{
        public int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public  File file;
    public  List<List<Integer>> data = new ArrayList<>();
    public Point start, end;

    public Parser(String filename){
        this.file = new File(filename);
        parse();
    }

    public List<List<Integer>> parse(){
        int row=0;
        try{
            BufferedReader in = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = in.readLine()) != null){
                data.add(new ArrayList<>());
                String[] values = line.split(",");
                int v = 0;
                for (String value : values) {
                    int l;
                    l = Integer.parseInt(value);
                    data.get(row).add(l);
                    if(l == -2)start = new Point(v, row);
                    if(l == -3)end = new Point(v, row);
                    v++;
                }
                row++;
            }
            in.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void print(){
        for(int i=0;i<this.data.size(); i++){
            for(int j=0; j<this.data.get(i).size();j++){
                if(this.data.get(i).get(j) == -1) System.out.print(ANSI_BLACK + " ■ " + ANSI_RESET);
                else if(this.data.get(i).get(j) == -2) System.out.print(ANSI_GREEN + " ■ " + ANSI_RESET);
                else if(this.data.get(i).get(j) == -3) System.out.print(ANSI_GREEN + " ■ " + ANSI_RESET);
                else System.out.print(" " + this.data.get(i).get(j)+ " ");
            }
            System.out.println();
        }
    }
}

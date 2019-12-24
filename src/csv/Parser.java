package csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import algoritmi.Point;
public class Parser extends Point{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public  File file;
    public  List<List<Integer>> data = new ArrayList<>();
    public Point start;
    public List<Point> end = new ArrayList<>();

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
                    if(l == -3)end.add(new Point(v, row));
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
                if(this.data.get(i).get(j) == -1) System.out.printf(ANSI_BLACK + "%s" + ANSI_RESET, "X");
                else if(this.data.get(i).get(j) == -2) System.out.printf(ANSI_RED + "%s" + ANSI_RESET, "X");
                else if(this.data.get(i).get(j) == -3) System.out.printf(ANSI_GREEN + "%s" + ANSI_RESET, "X");
                else System.out.printf("%s",this.data.get(i).get(j));
            }
            System.out.println();
        }
    }
}

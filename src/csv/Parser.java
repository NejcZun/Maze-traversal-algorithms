package csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public  File file;
    public  List<List<Integer>> data = new ArrayList<>();

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
                for (String value : values) {
                    int l;
                    l = Integer.parseInt(value);
                    data.get(row).add(l);
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
            for(int j=0; j<this.data.get(i).size();j++) System.out.print(this.data.get(i).get(j) + " ");
            System.out.println();
        }
    }
}

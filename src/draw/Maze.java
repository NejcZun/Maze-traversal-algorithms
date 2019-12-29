package draw;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import algoritmi.Point;

public class Maze {
    private int W;
    private int H;
    public List<List<Integer>> data;
    public List<Point> path;
    public Maze(List<List<Integer>> labirinth, List<Point> path) {
        this.path = path;
        this.data = labirinth;
        this.H = labirinth.size();
        this.W = labirinth.get(0).size();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Maze");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane(data, path, W, H));
                String[] algorithms = { "DFS", "BFS", "IDDFS", "AStar"};
                JComboBox list = new JComboBox(algorithms);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {
        private int W;
        private int H;
        public List<List<Integer>> data;
        public List<Point> path;
        public TestPane(List<List<Integer>> data, List<Point> path, int W, int H) {
            this.data = data;
            this.path = path;
            this.W = W;
            this.H = H;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1280, 720);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int size = Math.min(getWidth() - 4, getHeight() - 4) / Math.max(W, H);

            int width = getWidth() - (size * 2);
            int height = getHeight() - (size * 2);
            int i=0;
            int j=0;
            int y = (getHeight() - (size * H)) / 2;
            for (int horz = 0; horz < H; horz++) {
                int x = (getWidth() - (size * W)) / 2;
                for (int vert = 0; vert < W; vert++) {
                    //System.out.print(data.get(j).get(i%H));
                    if(data.get(j).get(i%H) == -1){
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, size, size);
                    }else if(data.get(j).get(i%H) == -2){
                        g.setColor(Color.RED);
                        g.fillRect(x, y, size, size);
                    }else if(data.get(j).get(i%H) == -3){
                        g.setColor(Color.GREEN);
                        g.fillRect(x, y, size, size);
                    }else{
                        for(Point point : path) {
                            if (point.x == i % H && point.y == j) {
                                g.setColor(Color.BLUE);
                                g.fillRect(x, y, size, size);
                            }
                        }
                        g.setColor(Color.WHITE);
                        g.drawRect(x, y, size, size);
                    }
                    if(i%W == 0 && i != 0)j++;
                    i++;
                    x += size;
                }
                y += size;
            }
            g2d.dispose();
        }

    }
}
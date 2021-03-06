package draw;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;

import algoritmi.*;
import algoritmi.Point;

public class Maze {
    private int W;
    private int H;
    public List<List<Integer>> data;
    public DFS dfs;
    public BFS bfs;
    public AStar astar;
    public IDDFS iddfs;
    public IDAStar idaStar;
    public Dijkstra dijkstra;
    public SimulatedAnnealing sa;
    public Maze(List<List<Integer>> labirinth, DFS dfs, BFS bfs, IDDFS iddfs, AStar astar, IDAStar idaStar, Dijkstra dijkstra, SimulatedAnnealing sa) {
        this.dfs = dfs;
        this.bfs = bfs;
        this.iddfs = iddfs;
        this.astar = astar;
        this.idaStar = idaStar;
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


                JTabbedPane tab = new JTabbedPane();

                /* --------------------------- DFS ---------------------------*/
                JPanel card1 = new JPanel();
                JPanel card1_info = new JPanel();
                TestPane dfs_pane = new TestPane(data, dfs.result, W, H);

                Label dfs_shortest_path = new Label("Shortest path length: " + dfs.result.size(), JLabel.LEFT);
                dfs_shortest_path.setFont(new Font(dfs_shortest_path.getName(), Font.PLAIN, 12));

                Label dfs_cost = new Label("Cost of path: " + dfs.cost, JLabel.LEFT);
                dfs_cost.setFont(new Font(dfs_cost.getName(), Font.PLAIN, 12));

                Label dfs_visits = new Label("Number of visits: " + dfs.num_of_visits, JLabel.LEFT);
                dfs_visits.setFont(new Font(dfs_visits.getName(), Font.PLAIN, 12));

                card1.setLayout(new BorderLayout());
                card1.add(dfs_pane, BorderLayout.CENTER);
                card1_info.add(dfs_cost);
                card1_info.add(dfs_shortest_path);
                card1_info.add(dfs_visits);
                card1.add(card1_info, BorderLayout.PAGE_END);

                /* --------------------------- BFS --------------------------- */
                JPanel card2 = new JPanel();
                JPanel card2_info = new JPanel();
                TestPane bfs_pane = new TestPane(data, bfs.result, W, H);

                Label bfs_shortest_path = new Label("Shortest path length: " + bfs.result.size(), JLabel.LEFT);
                bfs_shortest_path.setFont(new Font(bfs_shortest_path.getName(), Font.PLAIN, 12));

                Label bfs_cost = new Label("Cost of path: " + bfs.cost, JLabel.LEFT);
                bfs_cost.setFont(new Font(bfs_cost.getName(), Font.PLAIN, 12));

                Label bfs_visits = new Label("Number of visits: " + bfs.num_of_visits, JLabel.LEFT);
                bfs_visits.setFont(new Font(bfs_visits.getName(), Font.PLAIN, 12));

                card2.setLayout(new BorderLayout());

                card2.add(bfs_pane, BorderLayout.CENTER);
                card2_info.add(bfs_cost);
                card2_info.add(bfs_shortest_path);
                card2_info.add(bfs_visits);
                card2.add(card2_info, BorderLayout.PAGE_END);

                /* --------------------------- IDDFS --------------------------- */
                JPanel card3 = new JPanel();
                JPanel card3_info = new JPanel();
                TestPane iddfs_pane = new TestPane(data, iddfs.result, W, H);

                Label iddfs_shortest_path = new Label("Shortest path length: " + iddfs.result.size(), JLabel.LEFT);
                iddfs_shortest_path.setFont(new Font(iddfs_shortest_path.getName(), Font.PLAIN, 12));

                Label iddfs_cost = new Label("Cost of path: " + iddfs.cost, JLabel.LEFT);
                iddfs_cost.setFont(new Font(iddfs_cost.getName(), Font.PLAIN, 12));

                Label iddfs_visits = new Label("Number of visits: " + iddfs.num_of_visits, JLabel.LEFT);
                iddfs_visits.setFont(new Font(iddfs_visits.getName(), Font.PLAIN, 12));

                card3.setLayout(new BorderLayout());

                card3.add(iddfs_pane, BorderLayout.CENTER);
                card3_info.add(iddfs_cost);
                card3_info.add(iddfs_shortest_path);
                card3_info.add(iddfs_visits);
                card3.add(card3_info, BorderLayout.PAGE_END);

                /* --------------------------- AStar ---------------------------*/
                JPanel card4 = new JPanel();
                JPanel card4_info = new JPanel();
                TestPane astar_pane = new TestPane(data, astar.result, W, H);

                Label astar_shortest_path = new Label("Shortest path length: " + astar.result.size(), JLabel.LEFT);
                astar_shortest_path.setFont(new Font(astar_shortest_path.getName(), Font.PLAIN, 12));

                Label astar_cost = new Label("Cost of path: " + astar.cost, JLabel.LEFT);
                astar_cost.setFont(new Font(astar_cost.getName(), Font.PLAIN, 12));

                Label astar_visits = new Label("Number of visits: " + astar.num_of_visits, JLabel.LEFT);
                astar_visits.setFont(new Font(astar_visits.getName(), Font.PLAIN, 12));


                card4.setLayout(new BorderLayout());
                card4.add(astar_pane, BorderLayout.CENTER);
                card4_info.add(astar_cost);
                card4_info.add(astar_shortest_path);
                card4_info.add(astar_visits);
                card4.add(card4_info, BorderLayout.PAGE_END);

                /* ------------------- IDAStar -----------------------------*/

                JPanel card5 = new JPanel();
                JPanel card5_info = new JPanel();
                TestPane idastar_pane = new TestPane(data, idaStar.result, W, H);

                Label idastar_shortest_path = new Label("Shortest path length: " + idaStar.result.size(), JLabel.LEFT);
                idastar_shortest_path.setFont(new Font(astar_shortest_path.getName(), Font.PLAIN, 12));

                Label idastar_cost = new Label("Cost of path: " + idaStar.cost, JLabel.LEFT);
                idastar_cost.setFont(new Font(astar_cost.getName(), Font.PLAIN, 12));

                Label idastar_visits = new Label("Number of visits: " + idaStar.num_of_visits, JLabel.LEFT);
                idastar_visits.setFont(new Font(astar_visits.getName(), Font.PLAIN, 12));

                card5.setLayout(new BorderLayout());
                card5.add(idastar_pane, BorderLayout.CENTER);
                card5_info.add(idastar_cost);
                card5_info.add(idastar_shortest_path);
                card5_info.add(idastar_visits);
                card5.add(card5_info, BorderLayout.PAGE_END);

                /* ------------------- Dijsktra -----------------------------*/

                JPanel card6 = new JPanel();
                JPanel card6_info = new JPanel();
                TestPane dijkstra_pane = new TestPane(data, dijkstra.result, W, H);

                Label dijkstra_shortest_path = new Label("Shortest path length: " + dijkstra.result.size(), JLabel.LEFT);
                dijkstra_shortest_path.setFont(new Font(dijkstra_shortest_path.getName(), Font.PLAIN, 12));

                Label dijkstra_cost = new Label("Cost of path: " + dijkstra.cost, JLabel.LEFT);
                dijkstra_cost.setFont(new Font(dijkstra_cost.getName(), Font.PLAIN, 12));

                Label dijkstra_visits = new Label("Number of visits: " + dijkstra.num_of_visits, JLabel.LEFT);
                dijkstra_visits.setFont(new Font(dijkstra_visits.getName(), Font.PLAIN, 12));

                card6.setLayout(new BorderLayout());
                card6.add(dijkstra_pane, BorderLayout.CENTER);
                card6_info.add(dijkstra_cost);
                card6_info.add(dijkstra_shortest_path);
                card6_info.add(dijkstra_visits);
                card6.add(card6_info, BorderLayout.PAGE_END);

                /* ------------------- SA -----------------------------*/

                JPanel card7 = new JPanel();
                JPanel card7_info = new JPanel();
                TestPane sa_pane = new TestPane(data, sa.result, W, H);

                Label sa_shortest_path = new Label("Shortest path length: " + sa.result.size(), JLabel.LEFT);
                sa_shortest_path.setFont(new Font(sa_shortest_path.getName(), Font.PLAIN, 12));

                Label sa_cost = new Label("Cost of path: " + sa.cost, JLabel.LEFT);
                sa_cost.setFont(new Font(sa_cost.getName(), Font.PLAIN, 12));

                Label sa_visits = new Label("Number of visits: " + sa.num_of_visits, JLabel.LEFT);
                sa_visits.setFont(new Font(sa_visits.getName(), Font.PLAIN, 12));

                Label sa_iters = new Label("Number of iterations: " + sa.getIters(), JLabel.LEFT);
                sa_iters.setFont(new Font(sa_iters.getName(), Font.PLAIN, 12));

                card7.setLayout(new BorderLayout());
                card7.add(sa_pane, BorderLayout.CENTER);
                card7_info.add(sa_cost);
                card7_info.add(sa_shortest_path);
                card7_info.add(sa_visits);
                card7_info.add(sa_iters);
                card7.add(card7_info, BorderLayout.PAGE_END);

                /* --------------- Tabs ---------------------------------- */

                tab.addTab("DFS", card1);
                tab.addTab("BFS", card2);
                tab.addTab("IDDFS", card3);
                tab.addTab("ASTAR", card4);
                tab.addTab("IDASTAR", card5);
                tab.addTab("DIJKSTRA", card6);
                tab.addTab("SA", card7);

                frame.add(tab, BorderLayout.CENTER);
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
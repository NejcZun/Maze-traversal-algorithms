package algoritmi;

public class Point{
    public int x, y;
    Point parent;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Point(int x, int y, Point parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }
    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Point getParent() {
        return parent;
    }

    public Point(){} //za parser
}

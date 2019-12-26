package algoritmi;

public class Point{
    public int x, y;
    Point parent;
    public double g, h;
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

    public Point(int x, int y, Point parent, double g, double h){
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }
    public int compareTo(Object o){
        Point p = (Point) o;
        return (int)((this.g + this.h) - (p.g + p.h));
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

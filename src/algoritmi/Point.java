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

    public Point(int x, int y, double g, double h){
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
    }
    public int compareTo(Object o){
        Point p = (Point) o;
        return (int)((this.g + this.h) - (p.g + p.h));
    }

    void setG(double g){
        this.g = g;
    }
    void setH(double h){
        this.h=h;
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

    @Override
    public String toString() {
        return "(" + this.x + ", "+ this.y + ")";
    }
}

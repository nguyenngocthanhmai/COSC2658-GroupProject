public class Point {
    double x;
    double y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getDistance(Point point){
        return (Math.sqrt(Math.pow((x - point.x), 2) + Math.pow((y - point.y), 2)));
    }

    public String toString(){
        return "point = {" +
                "x = " + getX() + ", " +
                "y = " + getY() + "}";
    }
}

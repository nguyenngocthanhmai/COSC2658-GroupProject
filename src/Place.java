public class Place {
    String service;
    Point point;

    public Place(Point point, String service){
        this.point = point;
        this. service = service;
    }

    public String getService(){
        return service;
    }

    public Point getCoordinate(){
        return point;
    }

    //not implement yet
    public boolean editServices(){
        return false;
    }

    //not implement yet
    public  boolean removeService(){
        return false;
    }

    @Override
    public String toString() {
        return "place = {" +
                "type of service: " + service + ", " +
                point;
    }
}

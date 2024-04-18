import java.util.ArrayList;

public class QuadTree {
    private int capacity;
    private int size;
    private ArrayList<Place> points;
    private boolean isDivided;
    private Rectangle bounds;

    private QuadTree topLeft;
    private QuadTree topRight;
    private QuadTree lowerLeft;
    private QuadTree lowerRight;
    private int depth;

    public QuadTree(Rectangle bounds, int capacity, int depth){
        this.bounds = bounds;
        this.capacity = capacity;
        this.depth = depth;
        points = new ArrayList<>();
    }

    public ArrayList<Place> getChildren(){
        return points;
    }

    public boolean insert(Place place){
        if(!bounds.isContains(place)){
            System.out.println("Place " + place + " is out of bound.");
            return false;
        }

        if(points.size() < capacity){
            points.add(place);
            System.out.println("Added " + place);
            return true;
        }

        if(!isDivided){
            subdivide();
        }

        // Properly route the place to the appropriate quadrant
        if(topLeft.bounds.isContains(place)){
            return topLeft.insert(place);
        } else if (topRight.bounds.isContains(place)){
            return topRight.insert(place);
        } else if (lowerLeft.bounds.isContains(place)){
            return lowerLeft.insert(place);
        } else if (lowerRight.bounds.isContains(place)){
            return lowerRight.insert(place);
        }
        return false;       //this line should not be reach

    }

    public void subdivide(){
        // This method assumes that it is being called on a node that needs to be subdivided.
        if(!this.isDivided){
            // Create new child quadtrees with the subdivided rectangles
            topLeft = new QuadTree(bounds.subdivide("topLeft"), capacity, depth);
            topRight = new QuadTree(bounds.subdivide("topRight"), capacity, depth);
            lowerLeft = new QuadTree(bounds.subdivide("lowerLeft"), capacity, depth);
            lowerRight = new QuadTree(bounds.subdivide("lowerRight"), capacity, depth);
            this.isDivided = true;
        }
    }

    public ArrayList<Place> search(){
        return points;
    }

    public boolean remove(){
        return false;
    }

    @Override
    public String toString() {
        return "Quadtree\n" +
                "boundary: " + bounds + ", " +
                "capacity: " + capacity + ", " +
                "places: "   + points + ", " +
                "depth: "    + depth;
    }
}

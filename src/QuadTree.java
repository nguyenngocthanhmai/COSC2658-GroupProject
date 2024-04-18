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
    private QuadTree lastInsertedLeaf = null;
    private int depth;

    public QuadTree(Rectangle bounds, int capacity){
        this.bounds = bounds;
        this.capacity = capacity;
        points = new ArrayList<>();
    }

    public ArrayList<Place> getChildren(){
        return points;
    }

    // public boolean insert(Place place){
    //     if(!bounds.isContains(place)){
    //         return false;
    //     }

    //     if(points.size() < capacity){
    //         points.add(place);
    //         System.out.println("Added " + place);
    //         return true;
    //     }

    //     if(!isDivided){
    //         subdivide();
    //     }

    //     // Properly route the place to the appropriate quadrant
    //     return insertIntoChildren(place);

    // }

    public boolean insert(Place place) {
        if (lastInsertedLeaf != null && lastInsertedLeaf.bounds.isContains(place) && !lastInsertedLeaf.isDivided) {
            if (lastInsertedLeaf.points.size() < lastInsertedLeaf.capacity) {
                lastInsertedLeaf.points.add(place);
                return true;
            }
        }

        lastInsertedLeaf = null; // Reset if last leaf was full or does not contain the point
        return insertAtRoot(place);
    }

    private boolean insertAtRoot(Place place) {
        QuadTree current = this;
        while (current != null) {
            if (!current.bounds.isContains(place)) {
                return false;
            }

            if (current.points.size() < current.capacity && !current.isDivided) {
                current.points.add(place);
                lastInsertedLeaf = current; // Update last inserted leaf
                // System.out.println("Added " + place + " to " + current);
                return true;
            }

            if (!current.isDivided) {
                current.subdivide();
            }

            // Navigate to the correct child
            current = current.navigateToChild(place);
        }
        return false;
    }

    private QuadTree navigateToChild(Place place) {
        if (topLeft.bounds.isContains(place)) return topLeft;
        if (topRight.bounds.isContains(place)) return topRight;
        if (lowerLeft.bounds.isContains(place)) return lowerLeft;
        if (lowerRight.bounds.isContains(place)) return lowerRight;
        return null;
    }

    // private boolean insertIntoChildren(Place place){
    //     if (topLeft.bounds.isContains(place)) {
    //         return topLeft.insert(place);
    //     } else if (topRight.bounds.isContains(place)) {
    //         return topRight.insert(place);
    //     } else if (lowerLeft.bounds.isContains(place)) {
    //         return lowerLeft.insert(place);
    //     } else if (lowerRight.bounds.isContains(place)) {
    //         return lowerRight.insert(place);
    //     }
    //     return false;
    // }

    public void subdivide(){
        // This method assumes that it is being called on a node that needs to be subdivided.
        double halfWidth = bounds.width / 2;
        double halfHeight = bounds.height / 2;
        topLeft = new QuadTree(new Rectangle(bounds.x - halfWidth / 2, bounds.y - halfHeight / 2, halfWidth, halfHeight), capacity);
        topRight = new QuadTree(new Rectangle(bounds.x + halfWidth / 2, bounds.y - halfHeight / 2, halfWidth, halfHeight), capacity);
        lowerLeft = new QuadTree(new Rectangle(bounds.x - halfWidth / 2, bounds.y + halfHeight / 2, halfWidth, halfHeight), capacity);
        lowerRight = new QuadTree(new Rectangle(bounds.x + halfWidth / 2, bounds.y + halfHeight / 2, halfWidth, halfHeight), capacity);
        isDivided = true;
    }

    public ArrayList<Place> getAllPoints(){
        ArrayList<Place> allPoints = new ArrayList<>();
        allPoints.addAll(points);
        if(isDivided){
            allPoints.addAll(topLeft.getAllPoints());
            allPoints.addAll(topRight.getAllPoints());
            allPoints.addAll(lowerLeft.getAllPoints());
            allPoints.addAll(lowerRight.getAllPoints());
        }
        return allPoints;
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

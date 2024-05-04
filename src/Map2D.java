import java.util.Random;
import java.util.Scanner;

/**
 * Represents a spatial index structure for efficient querying of 2D points.
 * This class uses a quad-tree to manage places within defined bounds.
 */
public class Map2D {
    private final int capacity; // Maximum number of points per quad
    public ArrayList<Place> points; // Points in this quad
    private boolean isDivided; // Flag to check if the quad is already divided
    private final Rectangle bounds; // Spatial bounds of this quad
    private Map2D topLeft, topRight, lowerLeft, lowerRight; // Children quads
    private Map2D lastInsertedLeaf = null; // Last leaf where a point was inserted
    private final int depth; // Depth of this node in the tree

    /**
     * Constructor to initialize the QuadTree with bounds and capacity.
     * 
     * @param bounds   Rectangle, the spatial bounds of this quad.
     * @param capacity int, the maximum number of points this quad can hold before
     *                 subdividing.
     */
    public Map2D(Rectangle bounds, int capacity) {
        this.bounds = bounds;
        this.capacity = capacity;
        points = new ArrayList<Place>(capacity);
        this.depth = 0;
    }

    /**
     * Initializes a QuadTree with a specified number of random places. 
     * 
     * @param numberOfPlace int, the number of places to generate and insert.
     * @return QuadTree, the initialized quad tree.
     *         Time Complexity: O(n log n), where n is the number of places, due to
     *         insertion in the tree.
    */
    public static Map2D initialize(int numberOfPlace) {
        // create a map size 10000000 x 10000000 (10 million)
        Rectangle boundary = new Rectangle(10000000 / 2, 10000000 / 2, 10000000, 10000000);
        Map2D qt = new Map2D(boundary, 390625);
        Random rnd = new Random();
        Runtime runtime = Runtime.getRuntime();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfPlace; i++) {
            int x = rnd.nextInt(10000000);
            int y = rnd.nextInt(10000000);
            int services = ServiceType.randomizeServices();
            qt.insert(new Place(services, x, y));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Initializing successfully!");
        System.out.println("Number of children: " + qt.countChildren());
        System.out.println("Time taken for initializing: " + (endTime - startTime) + " ms");
        long memoryUsed = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024; // Convert to megabytes
        System.out.println("Memory Used: " + memoryUsed + " MB");
        return qt;
    }

    /**
     * Counts the total number of children nodes in the QuadTree.
     * 
     * @return int, the total number of children nodes.
     *         Time Complexity: O(n), where n is the number of nodes in the
     *         QuadTree.
     */
    public int countChildren() {
        int count = 0; // This node itself is not counted as a child
        count += points.size();
        // Recursively count children of each divided part
        if (isDivided) {
            if (topLeft != null)
                count += topLeft.countChildren();
            if (topRight != null)
                count += topRight.countChildren();
            if (lowerLeft != null)
                count += lowerLeft.countChildren();
            if (lowerRight != null)
                count += lowerRight.countChildren();
        }
        return count;
    }

    /**
     * Inserts a place into the QuadTree.
     * 
     * @param place Place, the place to insert.
     * @return boolean, true if the place was successfully inserted, false
     *         otherwise.
     *         Time Complexity: O(log n), where n is the number of nodes in the
     *         QuadTree.
     */
    public boolean insert(Place place) {
        Map2D current = (lastInsertedLeaf != null && lastInsertedLeaf.bounds.isContains(place)
                && !lastInsertedLeaf.isDivided) ? lastInsertedLeaf : this;

        while (current != null) {
            if (!current.bounds.isContains(place)) {
                return false;
            }

            if (current.points.size() < current.capacity && !current.isDivided) {
                current.points.insert(place); // Assuming `add` is the correct method to add to ArrayList
                lastInsertedLeaf = current; // Update last inserted leaf
                return true;
            }

            if (!current.isDivided) {
                current.subdivide();
            }

            // Inline navigation logic
            current = navigateToChild(place, current);
        }
        return false;
    }

    /**
     * Navigates to the appropriate child QuadTree based on the place's location.
     * 
     * @param place Place, the place used to determine the appropriate child.
     * @return QuadTree, the child QuadTree that contains the place.
     *         Time Complexity: O(1).
     */
    private Map2D navigateToChild(Place place, Map2D current) {
        double midX = current.bounds.x;
        double midY = current.bounds.y;
        boolean isLeft = place.x < midX;
        boolean isTop = place.y < midY;

        if (isLeft && isTop) {
            return current.topLeft;
        } else if (!isLeft && isTop) {
            return current.topRight;
        } else if (isLeft) {
            return current.lowerLeft;
        } else {
            return current.lowerRight;
        }
    }

    /**
     * Subdivides the current QuadTree node into four children.
     * Time Complexity: O(1).
     */
    public void subdivide() {
        // This method assumes that it is being called on a node that needs to be
        // subdivided.
        double halfWidth = bounds.width / 2;
        double halfHeight = bounds.height / 2;
        topLeft = new Map2D(
                new Rectangle(bounds.x - halfWidth / 2, bounds.y - halfHeight / 2, halfWidth, halfHeight), capacity);
        topRight = new Map2D(
                new Rectangle(bounds.x + halfWidth / 2, bounds.y - halfHeight / 2, halfWidth, halfHeight), capacity);
        lowerLeft = new Map2D(
                new Rectangle(bounds.x - halfWidth / 2, bounds.y + halfHeight / 2, halfWidth, halfHeight), capacity);
        lowerRight = new Map2D(
                new Rectangle(bounds.x + halfWidth / 2, bounds.y + halfHeight / 2, halfWidth, halfHeight), capacity);
        isDivided = true;
    }

    /**
     * Searches for places within a specified range that match a given service type.
     * 
     * @param range       Rectangle, the area to search within.
     * @param found       ArrayList<Place>, the list of found places.
     * @param serviceType ServiceType, the service type to filter by.
     * @param capacity    int, the maximum number of places to return.
     * @return ArrayList<Place>, the list of places that match the criteria.
     *         Time Complexity: O(log n), where n is the number of points in the
     *         QuadTree.
     */
    public ArrayList<Place> search(Rectangle range, ArrayList<Place> found, ServiceType serviceType, int capacity) {
        if (found == null) {
            found = new ArrayList<>(capacity);
        }

        if (!range.isIntersects(this.bounds)) {
            return found;
        }

        for (int i = 0; i < this.points.size(); i++) {
            Place p = this.points.get(i);
            if (range.isContains(p) && (serviceType == null || p.hasService(serviceType))
                    && found.size() < capacity) {
                found.insert(p);
            }
        }

        if (this.isDivided) {
            if (this.topLeft != null) {
                this.topLeft.search(range, found, serviceType, capacity);
            }
            if (this.topRight != null) {
                this.topRight.search(range, found, serviceType, capacity);
            }
            if (this.lowerLeft != null) {
                this.lowerLeft.search(range, found, serviceType, capacity);
            }
            if (this.lowerRight != null) {
                this.lowerRight.search(range, found, serviceType, capacity);
            }
            return found;
        }

        return found;
    }

    /**
     * Edits a place at a specified location.
     * 
     * @param x double, the x-coordinate of the place.
     * @param y double, the y-coordinate of the place.
     * @return Place, the edited place, or null if no place was found.
     *         Time Complexity: O(log n + k), where n is the number of nodes and k
     *         is the number of operations to edit the place.
     */
    public Place editPLace(double x, double y) {
        ArrayList<Place> foundPlaces = search(new Rectangle(x, y, 0, 0), null, null, 1);
        if (foundPlaces.size() == 0) {
            System.out.println("No place found!");
            return null;
        }
        Scanner sc = new Scanner(System.in);
        Place placeToEdit = foundPlaces.get(0);
        System.out.println(placeToEdit);
        GUI.printLineSeparator();
        System.out.println("1. Add service");
        System.out.println("2. Remove service");
        GUI.printLineSeparator();
        System.out.print("Enter your choice: ");
        String choice = sc.next();
        switch (choice) {
            case "1": {
                ServiceType.getAllServices();
                System.out.print("Choose service you want to add: ");
                int index = sc.nextInt();
                ServiceType serviceType = ServiceType.getServiceByIndex(index);
                if (placeToEdit.addService(serviceType)) {
                    System.out.println("Added service successfully!");
                } else {
                    System.out.println("Failed to add service!");
                }
                break;
            }
            case "2":
                placeToEdit.printServices();
                System.out.print("Choose service you want to remove: ");
                int index = sc.nextInt();
                ServiceType serviceType = placeToEdit.getServiceByIndex(index);
                if (placeToEdit.removeService(serviceType)) {
                    System.out.println("Removed service successfully!");
                } else {
                    System.out.println("Failed to remove service!");
                }
                break;
            default:
                break;
        }
        return placeToEdit;
    }

    /**
     * Removes a place at a specified location.
     * 
     * @param x double, the x-coordinate of the place.
     * @param y double, the y-coordinate of the place.
     * @param y double, the y-coordinate of the place.
     * @return boolean, true if the place was successfully removed, false otherwise.
     *         Time Complexity: O(log n), where n is the number of nodes in the
     *         QuadTree.
     */
    public boolean removePlace(int x, int y) {
        // Check if the current node's bounds contain the point
        Map2D current = this;
        Place placeToRemove = null;

        // Traverse down the tree to find and remove the place in one go
        while (current != null) {
            for (int i = 0; i < current.points.size(); i++) {
                if (current.points.get(i).compare(x, y)) {
                    placeToRemove = current.points.get(i);
                    break;
                }
            }

            if (placeToRemove != null) {
                if (current.points.remove(placeToRemove)) {
                    System.out.println("Removing place: " + placeToRemove);
                    return true;
                }
            }

            if (current.isDivided) {
                current = navigateToChild(new Place(0, x, y), current); // Navigate using a dummy place
            } else {
                break; // Stop if not divided
            }
        }
        return false;

        // If not found and not divided, return null
    }

    @Override
    public String toString() {
        return "Quadtree\n" +
                "boundary: " + bounds + ", " +
                "capacity: " + capacity + ", " +
                "places: " + points + ", " +
                "depth: " + depth;
    }
}

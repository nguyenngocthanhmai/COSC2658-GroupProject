import java.util.Random;
import java.util.Scanner;
/**
 * Represents a spatial index structure for efficient querying of 2D points.
 * This class uses a quad-tree to manage places within defined bounds.
 */
public class Map2D {
    private int capacity; // Maximum number of points per quad
    public ArrayList<Place> points; // Points in this quad
    private boolean isDivided; // Flag to check if the quad is already divided
    private Rectangle bounds; // Spatial bounds of this quad
    private Map2D topLeft, topRight, lowerLeft, lowerRight; // Children quads
    private Map2D lastInsertedLeaf = null; // Last leaf where a point was inserted
    private int depth; // Depth of this node in the tree

    /**
     * Constructor to initialize the QuadTree with bounds and capacity.
     * @param bounds Rectangle, the spatial bounds of this quad.
     * @param capacity int, the maximum number of points this quad can hold before subdividing.
    */
    public Map2D(Rectangle bounds, int capacity) {
        this.bounds = bounds;
        this.capacity = capacity;
        points = new ArrayList<Place>(capacity);
        this.depth = 0;
    }

    /**
     * Initializes a QuadTree with a specified number of random places.
     * @param numberOfPlace int, the number of places to generate and insert.
     * @return QuadTree, the initialized quad tree.
     * Time Complexity: O(n log n), where n is the number of places, due to insertion in the tree.
    */
    public static Map2D initialize(int numberOfPlace) {
        Rectangle boundary = new Rectangle(10000000 / 2, 10000000 / 2, 10000000, 10000000);
        Map2D qt = new Map2D(boundary, 400000);
        Random rnd = new Random();
        Runtime runtime = Runtime.getRuntime();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfPlace; i++) {
            double x = rnd.nextDouble() * 10000000;
            double y = rnd.nextDouble() * 10000000;
            ServiceType serviceType = ServiceType.values()[rnd.nextInt(ServiceType.values().length)];
            ArrayList<ServiceType> serviceSet = new ArrayList<>(ServiceType.values().length);
            serviceSet.insert(serviceType);
            qt.insert(new Place(serviceSet, x, y));
        }
        System.out.println("Number of children: " + qt.countChildren());
        long endTime = System.currentTimeMillis();
        System.out.println("Initlizing successfully!");
        System.out.println("Time taken for initializing: " + (endTime - startTime) + " ms");
        long memoryUsed = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024; // Convert to megabytes
        System.out.println("Memory Used: " + memoryUsed + " MB");
        return qt;
    }

    /**
     * Counts the total number of children nodes in the QuadTree.
     * @return int, the total number of children nodes.
     * Time Complexity: O(n), where n is the number of nodes in the QuadTree.
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
     * @param place Place, the place to insert.
     * @return boolean, true if the place was successfully inserted, false otherwise.
     * Time Complexity: O(log n), where n is the number of nodes in the QuadTree.
    */
    public boolean insert(Place place) {
        if (lastInsertedLeaf != null && lastInsertedLeaf.bounds.isContains(place) && !lastInsertedLeaf.isDivided) {
            if (lastInsertedLeaf.points.size() < lastInsertedLeaf.capacity) {
                lastInsertedLeaf.points.insert(place);
                return true;
            }
        }

        lastInsertedLeaf = null; // Reset if last leaf was full or does not contain the point
        return insertAtRoot(place);
    }

    /**
     * Helper method to insert a place starting from the root.
     * @param place Place, the place to insert.
     * @return boolean, true if the place was successfully inserted, false otherwise.
     * Time Complexity: O(log n), where n is the number of nodes in the QuadTree.
    */
    private boolean insertAtRoot(Place place) {
        Map2D current = this;
        while (current != null) {
            if (!current.bounds.isContains(place)) {
                return false;
            }

            if (current.points.size() < current.capacity && !current.isDivided) {
                current.points.insert(place);
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

    /**
     * Navigates to the appropriate child QuadTree based on the place's location.
     * @param place Place, the place used to determine the appropriate child.
     * @return QuadTree, the child QuadTree that contains the place.
     * Time Complexity: O(1).
    */
    private Map2D navigateToChild(Place place) {
        if (topLeft.bounds.isContains(place))
            return topLeft;
        if (topRight.bounds.isContains(place))
            return topRight;
        if (lowerLeft.bounds.isContains(place))
            return lowerLeft;
        if (lowerRight.bounds.isContains(place))
            return lowerRight;
        return null;
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
     * @param range Rectangle, the area to search within.
     * @param found ArrayList<Place>, the list of found places.
     * @param serviceType ServiceType, the service type to filter by.
     * @param capacity int, the maximum number of places to return.
     * @return ArrayList<Place>, the list of places that match the criteria.
     * Time Complexity: O(n), where n is the number of points in the QuadTree.
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
            if (range.isContains(p) && (serviceType == null || p.service.contains(serviceType))
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
     * @param x double, the x-coordinate of the place.
     * @param y double, the y-coordinate of the place.
     * @return Place, the edited place, or null if no place was found.
     * Time Complexity: O(log n + k), where n is the number of nodes and k is the number of operations to edit the place.
    */
    public Place editPLace(double x, double y) {
        ArrayList<Place> foundPlaces = search(new Rectangle(x, y, 0, 0), null, null, 1);
        if (foundPlaces.size() == 0) {
            System.out.println("No place found!");
            return null;
        }
        Scanner sc = new Scanner(System.in);
        Place placeToEdit = foundPlaces.get(0);
        System.out.println("Place: " + placeToEdit);
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
        sc.close();
        return placeToEdit;
    }

    /**
     * Removes a place at a specified location.
     * @param x double, the x-coordinate of the place.
     * @param y double, the y-coordinate of the place.
     * * @param y double, the y-coordinate of the place.
     * @return boolean, true if the place was successfully removed, false otherwise.
     * Time Complexity: O(log n), where n is the number of nodes in the QuadTree.
    */
    public boolean removePlace(double x, double y) {
        // Check if the current node's bounds contain the point
        ArrayList<Place> foundPlaces = search(new Rectangle(x, y, 0, 0), null, null, 1);
        if (foundPlaces.size() == 0) {
            return false;
        }
        Place placeToRemove = foundPlaces.get(0);
        // Proceed to remove the place if found
        if (points.remove(placeToRemove)) {
            return true;
        }

        // If not found and not divided, return null
        return false;
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

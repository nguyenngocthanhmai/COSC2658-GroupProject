import java.util.Random;
import java.util.Scanner;

public class QuadTree {
    private int capacity;
    public ArrayList<Place> points;
    private boolean isDivided;
    private Rectangle bounds;

    private QuadTree topLeft;
    private QuadTree topRight;
    private QuadTree lowerLeft;
    private QuadTree lowerRight;
    private QuadTree lastInsertedLeaf = null;
    private int depth;

    public QuadTree(Rectangle bounds, int capacity) {
        this.bounds = bounds;
        this.capacity = capacity;
        points = new ArrayList<Place>(capacity);
        this.depth = 0;
    }

    public static QuadTree initialize(int numberOfPlace) {
        Rectangle boundary = new Rectangle(10000000 / 2, 10000000 / 2, 10000000, 10000000);
        QuadTree qt = new QuadTree(boundary, 400000);
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

    private boolean insertAtRoot(Place place) {
        QuadTree current = this;
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

    private QuadTree navigateToChild(Place place) {
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

    public void subdivide() {
        // This method assumes that it is being called on a node that needs to be
        // subdivided.
        double halfWidth = bounds.width / 2;
        double halfHeight = bounds.height / 2;
        topLeft = new QuadTree(
                new Rectangle(bounds.x - halfWidth / 2, bounds.y - halfHeight / 2, halfWidth, halfHeight), capacity);
        topRight = new QuadTree(
                new Rectangle(bounds.x + halfWidth / 2, bounds.y - halfHeight / 2, halfWidth, halfHeight), capacity);
        lowerLeft = new QuadTree(
                new Rectangle(bounds.x - halfWidth / 2, bounds.y + halfHeight / 2, halfWidth, halfHeight), capacity);
        lowerRight = new QuadTree(
                new Rectangle(bounds.x + halfWidth / 2, bounds.y + halfHeight / 2, halfWidth, halfHeight), capacity);
        isDivided = true;
    }

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

    public Place editePLace(double x, double y) {
        ArrayList<Place> foundPlaces = search(new Rectangle(x, y, 0, 0), null, null, 1);
        if (foundPlaces.size() == 0) {
            System.out.println("No place found!");
            return null;
        }
        Scanner sc = new Scanner(System.in);
        Place placeToEdit = foundPlaces.get(0);
        System.out.println("Place: " + placeToEdit);
        GUI.printLineSeperator();
        System.out.println("1. Add service");
        System.out.println("2. Remove service");
        GUI.printLineSeperator();
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

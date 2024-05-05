package maps;

import utils.List;
import java.util.Random;
import java.util.Scanner;
import enums.ServiceType;
import gui.GUI;
import models.Place;
import utils.ArrayList;
import utils.Rectangle;

/**
 * The Map2D class represents a quad-tree data structure for efficient spatial
 * indexing and querying of 2D points.
 * It supports operations such as insertion, search, edit, and removal of places
 * within a defined 2D space.
 * Each node of the quad-tree can either be a leaf node with a list of places or
 * an internal node with four children nodes.
 * This structure is particularly useful for applications that require rapid
 * spatial searches such as maps and simulation systems.
 */
public class Map2D {
    public static int numberOfTraversal = 0;
    private final int capacity; // Maximum number of points per quad
    public List<Place> points; // Points in this quad
    private boolean isDivided; // Flag to check if the quad is already divided
    private final Rectangle bounds; // Spatial bounds of this quad
    private Map2D topLeft, topRight, lowerLeft, lowerRight; // Children quads

    /**
     * Constructor initializes the quad-tree with specified spatial bounds and
     * capacity.
     * 
     * @param bounds   The spatial bounds of this quad.
     * @param capacity The maximum number of points this quad can hold before
     *                 subdividing.
     */
    public Map2D(Rectangle bounds, int capacity) {
        this.bounds = bounds;
        this.capacity = capacity;
        points = new ArrayList<>(capacity);
    }

    /**
     * Initializes a quad-tree with a specified number of random places.
     * 
     * @param numberOfPlace The number of places to generate and insert.
     * @return The initialized quad-tree.
     *         Time Complexity: O(n log n), where n is the number of places, due to
     *         insertion in the tree.
     */
    public static Map2D initialize(int numberOfPlace) {
        // create a map size 10000000 x 10000000 (10 million)
        Rectangle boundary = new Rectangle(10000000 / 2, 10000000 / 2, 10000000, 10000000);
        int desiredDepth = 4;
        Map2D qt = new Map2D(boundary, calculateIdealCapacity(numberOfPlace, desiredDepth));
        Runtime runtime = Runtime.getRuntime();
        long startTime = System.currentTimeMillis();
        qt.generateRandomData(numberOfPlace);
        long endTime = System.currentTimeMillis();
        System.out.println("Initializing successfully!");
        System.out.println("Number of children: " + qt.countChildren());
        System.out.println("Time taken for initializing: " + (endTime - startTime) + " ms");
        long memoryUsed = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024; // Convert to megabytes
        System.out.println("Memory Used: " + memoryUsed + " MB");
        return qt;
    }

    /**
     * Counts the total number of children nodes in the quad-tree.
     * 
     * @return The total number of children nodes.
     *         Time Complexity: O(n), where n is the number of nodes in the
     *         quad-tree.
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
     * Inserts a place into the quad-tree.
     * 
     * @param place The place to insert.
     * @return true if the place was successfully inserted, false otherwise.
     *         Time Complexity: O(log n), where n is the number of nodes in the
     *         quad-tree.
     */
    public boolean insert(Place place) {
        Map2D current = this;

        while (current != null) {
            if (!current.bounds.isContains(place)) {
                return false; // The place is out of the bounds of this quad
            }

            if (!current.isDivided) {
                if (current.points.size() < current.capacity) {
                    current.points.insert(place);
                    return true;
                } else {
                    // Subdivide the current node if it's at capacity and not already divided
                    current.subdivide();
                }
            }

            // Navigate to the appropriate child node after subdivision or if already
            // divided
            current = current.navigateToChild(place, current);
        }
        return false;
    }

    /**
     * Navigates to the appropriate child quad-tree based on the place's location.
     * 
     * @param place   The place used to determine the appropriate child.
     * @param current The current node from which to navigate.
     * @return The child quad-tree that contains the place.
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
     * Subdivides the current quad-tree node into four children.
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
     * @param range       The area to search within.
     * @param found       The list of found places.
     * @param serviceType The service type to filter by.
     * @param capacity    The maximum number of places to return.
     * @return The list of places that match the criteria.
     *         Time Complexity: O(log n), where n is the number of points in the
     *         quad-tree.
     */
    public ArrayList<Place> search(Rectangle range, ArrayList<Place> found, ServiceType serviceType, int capacity) {
        if (found == null) {
            found = new ArrayList<>(capacity);
        }
        numberOfTraversal++;
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
     * @param x The x-coordinate of the place.
     * @param y The y-coordinate of the place.
     * @return The edited place, or null if no place was found.
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
     * @param x The x-coordinate of the place.
     * @param y The y-coordinate of the place.
     * @return true if the place was successfully removed, false otherwise.
     *         Time Complexity: O(log n), where n is the number of nodes in the
     *         quad-tree.
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

    /**
     * Generates random data and populates the quad-tree with places.
     * This method divides the map into four quadrants and evenly distributes a specified number of places across these quadrants.
     * Each place is assigned random coordinates within its designated quadrant and a random set of services.
     * If the total number of places is not divisible by four, the remainder is distributed randomly across the entire map.
     *
     * @param numberOfPlace The total number of places to generate and insert into the quad-tree.
     * Time Complexity: O(n log n), where n is the number of places.
     * The time complexity is primarily due to the insertion operation in the quad-tree, which has a complexity of O(log n) per insertion.
     * Since we are inserting 'n' places, the overall complexity becomes O(n log n).
    */
    public void generateRandomData(int numberOfPlace){
        Random rnd = new Random(); // Create a Random object for generating random numbers.
    
        int quarterPlaces = numberOfPlace / 4; // Divide the total number of places by 4 to distribute them evenly across four quadrants.
    
        // Loop through each of the four quadrants.
        for (int quadrant = 0; quadrant < 4; quadrant++) {
            // Generate places for each quadrant.
            for (int i = 0; i < quarterPlaces; i++) {
                // Calculate the x-coordinate. Offset is added to place points in the right half of the map for the second and fourth quadrants.
                int x = rnd.nextInt(5000000) + (quadrant % 2) * 5000000;
                // Calculate the y-coordinate. Offset is added to place points in the bottom half of the map for the third and fourth quadrants.
                int y = rnd.nextInt(5000000) + (quadrant / 2) * 5000000;
                // Randomly generate a set of services for the place.
                int services = ServiceType.randomizeServices();
                // Insert the new place with the generated coordinates and services into the quad-tree.
                insert(new Place(services, x, y));
            }
        }
    
        // If the total number of places is not perfectly divisible by 4, handle the remainder.
        // This loop adds the remaining places randomly across the entire map.
        for (int i = 0; i < numberOfPlace % 4; i++) {
            // Generate random x and y coordinates without any quadrant-specific offset.
            int x = rnd.nextInt(10000000);
            int y = rnd.nextInt(10000000);
            // Randomly generate a set of services for the place.
            int services = ServiceType.randomizeServices();
            // Insert the new place with the generated coordinates and services into the quad-tree.
            insert(new Place(services, x, y));
        }
    }

    public static int calculateIdealCapacity(int totalPlaces, int desiredDepth) {
        return totalPlaces / (int) Math.pow(4, desiredDepth);
    }
}

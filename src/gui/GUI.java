package gui;
import java.util.Scanner;
import enums.ServiceType;
import maps.Map2D;
import models.Place;
import utils.ArrayList;
import utils.Rectangle;

/**
 * This class represents the graphical user interface for managing a QuadTree of places.
 * It allows users to insert, remove, search, and edit places in the QuadTree through a console-based menu system.
*/
public class GUI {
    private static Map2D qt; // The QuadTree that stores all the places.

    /**
     * Initializes the QuadTree and handles the user interface for operations on the QuadTree.
     * Time Complexity: Varies depending on the operation chosen.
     */
    public static void initialize() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of place to initialize the quad tree: ");
        int numPlace = sc.nextInt();
        qt = Map2D.initialize(numPlace);
        while (true) {
            displayOperations();
            String choice = sc.next();
            switch (choice) {
                case "1":
                    insertPlace(sc);
                    break;
                case "2":
                    removePlace(sc);
                    break;
                case "3":
                    searchPlaces(sc);
                    break;
                    case "4":
                    editPlace(sc);
                    break;
                case "5":
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    /**
     * Displays the main menu of operations to the user.
     * Time Complexity: O(1).
    */
    private static void displayOperations() {
        printLineSeparator();
        System.out.println("1. Insert a place");
        System.out.println("2. Remove a place");
        System.out.println("3. Search for a place");
        System.out.println("4. Edit a place");
        System.out.println("5. Exit");
        printLineSeparator();
        System.out.print("Enter your choice: ");
    }

    /**
     * Inserts a new place into the QuadTree.
     * @param sc Scanner, the scanner to read user input.
     * Time Complexity: O(log n) on average for QuadTree insert operations.
    */
    private static void insertPlace(Scanner sc) {
        ArrayList<ServiceType> servicesAtPlace = new ArrayList<>(6);
        while (true) {
            ServiceType.getAllServices();
            System.out.print("Choose service type you want to add: ");
            int index = sc.nextInt();
            ServiceType serviceType = ServiceType.getServiceByIndex(index);
            servicesAtPlace.insert(serviceType);
            System.out.print("Press 'x' to exit or else to continue adding service type: ");

            if (sc.next().equals("x")) {
                break;
            }
        }
        System.out.print("Please enter the x coordinate: ");
        int x = sc.nextInt();
        System.out.print("Please enter the y coordinate: ");
        int y = sc.nextInt();
        Place place = new Place(ServiceType.servicesToBinary(servicesAtPlace), x, y);
        System.out.println("Inserting a place: " + place);
        if (qt.insert(place)) {
            System.out.println("Place inserted successfully");
        } else {
            System.out.println("Place inserted unsuccessfully");
        }
    }

    /**
     * Removes a place from the QuadTree based on coordinates.
     * @param sc Scanner, the scanner to read user input.
     * Time Complexity: O(log n) on average for QuadTree removal operations.
    */
    private static void removePlace(Scanner sc) {
        System.out.print("Please enter the x coordinate: ");
        int x = sc.nextInt();
        System.out.print("Please enter the y coordinate: ");
        int y = sc.nextInt();
        if (qt.removePlace(x, y)) {
            System.out.println("Place removed successfully!");
        } else {
            System.out.println("Place not found");
            System.out.println("Place removal failed!");
        }
    }

    /**
     * Searches for places within a specified rectangle and service type in the QuadTree.
     * @param sc Scanner, the scanner to read user input.
     * Time Complexity: O(n) in the worst case, where n is the number of places in the rectangle.
    */
    private static void searchPlaces(Scanner sc) {
        System.out.print("Please enter the center point x: ");
        int x = sc.nextInt();
        System.out.print("Please enter the center point y: ");
        int y = sc.nextInt();
        System.out.print("Please enter the width from center point: ");
        int w = sc.nextInt();
        System.out.print("Please enter the height from center point: ");
        int h = sc.nextInt();
        System.out.print("Please enter the capacity (max = 50): ");
        int capac = sc.nextInt();
        if (capac > 50) {
            System.out.println("Capacity cannot be greater than 50");
            return;
        }
        ServiceType.getAllServices();
        System.out.print("Choose service for searching: ");
        int index = sc.nextInt();
        printLineSeparator();
        ServiceType serviceType = ServiceType.getServiceByIndex(index);
        System.out.println(
                "Searching for places within the rectangle: x=" + x + ", y=" + y + ", w=" + w*2 + ", h=" + h*2);
        long startTime = System.currentTimeMillis();
        ArrayList<Place> places = qt.search(new Rectangle(x, y, w * 2, h * 2), null, serviceType, capac);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Number of places found: " + places.size());
        for (int i = 0; i < places.size(); i++) {
            System.out.println(places.get(i));
        }
    }

    /**
     * Edits a place's details in the QuadTree based on coordinates.
     * @param sc Scanner, the scanner to read user input.
     * Time Complexity: O(log n) on average for QuadTree edit operations.
    */
    private static void editPlace(Scanner sc) {
        System.out.print("Please enter the x coordinate: ");
        int x = sc.nextInt();
        System.out.print("Please enter the y coordinate: ");
        int y = sc.nextInt();
        qt.editPLace(x, y);
    }

    /**
     * Prints a line separator to visually separate parts of the interface.
     * Time Complexity: O(1).
    */
    public static void printLineSeparator() {
        System.out.println("----------------------------");
    }
}

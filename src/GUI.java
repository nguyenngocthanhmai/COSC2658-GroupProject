import java.util.Scanner;

public class GUI {
    private static QuadTree qt;

    public static void initialize() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of place to initialize the quad tree: ");
        int numPlace = sc.nextInt();
        qt = QuadTree.initialize(numPlace);
        while (true) {
            displayOperations();
            String choice = sc.next();
            switch (choice) {
                case "1": {
                    ArrayList<ServiceType> servicesAtPlace = new ArrayList<>(6);
                    while (true) {
                        ServiceType.getAllServices();
                        System.out.print("Choose service you want to add: ");
                        int index = sc.nextInt();
                        ServiceType serviceType = ServiceType.getServiceByIndex(index);
                        servicesAtPlace.insert(serviceType);
                        System.out.print("Press 'x' to exit or else to continue adding: ");

                        if (sc.next().equals("x")) {
                            break;
                        }
                    }
                    System.out.print("Please enter the x coordinate: ");
                    double x = sc.nextDouble();
                    System.out.print("Please enter the y coordinate: ");
                    double y = sc.nextDouble();
                    Place place = new Place(servicesAtPlace, x, y);
                    System.out.println("Inserting a place: " + place);
                    if (qt.insert(place)) {
                        System.out.println("Place inserted successfully");
                    } else {
                        System.out.println("Place inserted unsuccessfully");
                    }
                    break;
                }
                case "2": {
                    System.out.print("Please enter the x coordinate: ");
                    double x = sc.nextDouble();
                    System.out.print("Please enter the y coordinate: ");
                    double y = sc.nextDouble();
                    if (qt.removePlace(x, y)) {
                        System.out.println("Place removed successfully!");
                    } else {
                        System.out.println("Place not found");
                        System.out.println("Place removal failed!");
                    }
                    break;
                }
                case "3": {
                    System.out.print("Please enter the center point x: ");
                    double x = sc.nextDouble();
                    System.out.print("Please enter the center point y: ");
                    double y = sc.nextDouble();
                    System.out.print("Please enter the width from center point: ");
                    double w = sc.nextDouble();
                    System.out.print("Please enter the height from center point: ");
                    double h = sc.nextDouble();
                    System.out.print("Please enter the capacity (max = 50): ");
                    int capac = sc.nextInt();
                    if (capac > 50) {
                        System.out.println("Capacity cannot be greater than 50");
                        break;
                    }
                    ServiceType.getAllServices();
                    System.out.print("Choose service you want to add: ");
                    int index = sc.nextInt();
                    printLineSeperator();
                    ServiceType serviceType = ServiceType.getServiceByIndex(index);
                    System.out.println(
                            "Searching for places within the rectangle: x=" + x + ", y=" + y + ", w=" + w + ", h=" + h);
                    ArrayList<Place> places = qt.search(new Rectangle(x, y, w * 2, h * 2), null, serviceType, capac);
                    System.out.println("Number of places found: " + places.size());
                    for (int i = 0; i < places.size(); i++) {
                        System.out.println(places.get(i));
                    }
                    break;
                }
                case "4": {
                    System.out.print("Please enter the x coordinate: ");
                    double x = sc.nextDouble();
                    System.out.print("Please enter the y coordinate: ");
                    double y = sc.nextDouble();
                    qt.editePLace(x, y);
                    break;
                }
                case "5":
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void displayOperations() {
        printLineSeperator();
        System.out.println("1. Insert a place");
        System.out.println("2. Remove a place");
        System.out.println("3. Search for a place");
        System.out.println("4. Edit a place");
        System.out.println("5. Exit");
        printLineSeperator();
        System.out.print("Enter your choice: ");
    }

    public static void printLineSeperator() {
        System.out.println("----------------------------");
    }
}
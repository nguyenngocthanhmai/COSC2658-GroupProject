package models;
import enums.ServiceType;
import utils.ArrayList;

/**
 * Represents a geographical location with associated services.
 * This class manages services at a specific coordinate (x, y).
 * It allows for adding, removing, and checking services using a binary representation.
 */
public class Place {
    public int service; // Binary representation of services available at this place
    public int x; // X-coordinate of the place
    public int y; // Y-coordinate of the place

    /**
     * Constructor to initialize the Place with services and coordinates.
     * @param service int, the binary representation of services available at this place.
     * @param x int, the x-coordinate of this place.
     * @param y int, the y-coordinate of this place.
     */
    public Place(int service, int x, int y) {
        this.service = service;
        this.x = x;
        this.y = y;
    }

    /**
     * Prints all services available at this place by converting the binary representation to a list of services.
     * Time Complexity: O(n), where n is the number of services.
    */
    public void printServices(){
        ArrayList<ServiceType> services = ServiceType.getServicesByBinary(service);
        for (int i = 0; i < services.size(); i++) {
            System.out.println(i + ". " + services.get(i));
        }
    }

    /**
     * Retrieves a service by its index in the list.
     * @param index int, the index of the service in the list.
     * @return ServiceType, the service at the specified index.
     * Time Complexity: O(1), assuming ArrayList.get() is constant time.
     */
    public ServiceType getServiceByIndex(int index) {
        return ServiceType.getServiceByIndex(index);
    }

    /**
     * Removes a specified service from the place by modifying the binary representation.
     * @param serviceType ServiceType, the service to be removed.
     * @return boolean, true if the service was removed, false otherwise.
     * Time Complexity: O(1), as it involves a single bitwise operation.
     */
    public boolean removeService(ServiceType serviceType) {
        int serviceBit = serviceType.getBinaryValue();
        if ((service & serviceBit) != serviceBit) {
            return false;
        }
        service &= ~serviceBit;
        return true;
    }

    /**
     * Adds a new service to the place if it does not already exist by updating the binary representation.
     * @param serviceType ServiceType, the service to add.
     * @return boolean, true if the service was added, false if it already exists.
     * Time Complexity: O(1), as it involves a single bitwise operation.
     */
    public boolean addService(ServiceType serviceType) {
        int serviceBit = serviceType.getBinaryValue();
        if ((service & serviceBit) == serviceBit) {
            System.out.println("Service already exists!");
            return false;
        }
        service |= serviceBit;
        return true;
    }

    /**
     * Checks if a specific service is available at this place by examining the binary representation.
     * @param serviceType ServiceType, the service to check for.
     * @return boolean, true if the service is available, false otherwise.
     * Time Complexity: O(1), as it involves a single bitwise operation.
     */
    public boolean hasService(ServiceType serviceType) {
        int serviceBit = serviceType.getBinaryValue();
        return (service & serviceBit) == serviceBit;
    }

    /**
     * Returns a string representation of the place, listing all services and coordinates.
     * @return String, the string representation of this place.
     * Time Complexity: O(n), where n is the number of services.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<ServiceType> services = ServiceType.getServicesByBinary(service);
        sb.append("Place: service = [");
        for (int i = 0; i < services.size(); i++) {
            sb.append(services.get(i));
            if (i < services.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("], x = ").append(x);
        sb.append(", y = ").append(y);
        return sb.toString();
    }

    /**
     * Compares the given coordinates with the place's coordinates to check for equality.
     * @param a double, the x-coordinate to compare.
     * @param b double, the y-coordinate to compare.
     * @return boolean, true if both coordinates match, false otherwise.
     * Time Complexity: O(1).
     */
    public boolean compare(double a, double b) {
        return Double.compare(a, x) == 0 && Double.compare(b, y) == 0;
    }
}
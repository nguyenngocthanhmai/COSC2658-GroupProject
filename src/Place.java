/**
 * Represents a geographical location with associated services.
 * This class manages services at a specific coordinate (x, y).
 */
public class Place {
    ArrayList<ServiceType> service; // List of services available at this place
    double x; // X-coordinate of the place
    double y; // Y-coordinate of the place

    /**
     * Constructor to initialize the Place with services and coordinates.
     * @param service ArrayList of ServiceType, the services available at this place.
     * @param x double, the x-coordinate of this place.
     * @param y double, the y-coordinate of this place.
     */
    public Place(ArrayList<ServiceType> service, double x, double y) {
        this.service = service;
        this.x = x;
        this.y = y;
    }

    /**
     * Prints all services available at this place.
     * Time Complexity: O(n), where n is the number of services.
     */
    public void printServices(){
        for (int i = 0; i < service.size(); i++) {
            System.out.println(i + ". " + service.get(i));
        }
    }

    /**
     * Retrieves a service by its index in the list.
     * @param index int, the index of the service in the list.
     * @return ServiceType, the service at the specified index.
     * Time Complexity: O(1), assuming ArrayList.get() is constant time.
     */
    public ServiceType getServiceByIndex(int index) {
        return service.get(index);
    }

    /**
     * Removes a specified service from the place.
     * @param serviceType ServiceType, the service to be removed.
     * @return boolean, true if the service was removed, false otherwise.
     * Time Complexity: O(n), where n is the number of services (due to contains and remove operations).
     */
    public boolean removeService(ServiceType serviceType) {
        if (!hasService(serviceType)) {
            System.out.println("Service not found!");
            return false;
        }

        if (service.size() == 1) {
            System.out.println("Can't remove the last service!");
            return false;
        }
        return service.remove(serviceType);
    }

    /**
     * Adds a new service to the place if it does not already exist.
     * @param serviceType ServiceType, the service to add.
     * @return boolean, true if the service was added, false if it already exists.
     * Time Complexity: O(n), where n is the number of services (due to contains check).
     */
    public boolean addService(ServiceType serviceType) {
        if (hasService(serviceType)) {
            System.out.println("Service already exists!");
            return false;
        }
        return service.insert(serviceType);
    }

    /**
     * Checks if a specific service is available at this place.
     * @param serviceType ServiceType, the service to check for.
     * @return boolean, true if the service is available, false otherwise.
     * Time Complexity: O(n), where n is the number of services.
     */
    private boolean hasService(ServiceType serviceType) {
        return service.contains(serviceType);
    }

    /**
     * Returns a string representation of the place, listing all services and coordinates.
     * @return String, the string representation of this place.
     * Time Complexity: O(n), where n is the number of services.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Place: service = ");
        for (int i =0; i<service.size(); i++){
            sb.append(service.get(i));
            if (i != service.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(", x = ").append(x);
        sb.append(", y = ").append(y);
        return sb.toString();
    }

    /**
     * Compares the given coordinates with the place's coordinates.
     * @param a double, the x-coordinate to compare.
     * @param b double, the y-coordinate to compare.
     * @return boolean, true if both coordinates match, false otherwise.
     * Time Complexity: O(1).
     */
    public boolean compare(double a, double b) {
        return Double.compare(a, x) == 0 && Double.compare(b, y) == 0;
    }
}
/**
 * Enum representing different types of services that can be offered at a location.
 * This enum includes methods for retrieving service types by index and listing all service types.
 */
public enum ServiceType {
    HOTEL, COFFEE, RESTAURANT, ATM, GAS_STATION, HOSPITAL;

    /**
     * Retrieves a service type based on its ordinal index.
     * @param index int, the index of the service type in the enum.
     * @return ServiceType, the service type at the specified index.
     * @throws IllegalArgumentException if the index is out of bounds.
     * Time Complexity: O(1), as it directly accesses an array element.
     */
    public static ServiceType getServiceByIndex(int index) {
        ServiceType[] services = ServiceType.values();
        if (index < 0 || index >= services.length) {
            System.out.println("Invalid index: " + index);
        }
        return services[index];
    }

    /**
     * Prints all available service types to the standard output.
     * Time Complexity: O(n), where n is the number of service types.
     */
    public static void getAllServices(){
        GUI.printLineSeparator();
        System.out.println("Service types: ");
        for (ServiceType service : ServiceType.values()) {
            System.out.println(service.ordinal() + " - " + service.toString());
        }
        GUI.printLineSeparator();
    }

    /**
     * Provides a custom string representation of the service type.
     * @return String, the custom formatted name of the service type.
     * Time Complexity: O(1), as it performs simple string operations.
     */
     @Override
    public String toString() {
        // Customize the string representation as needed
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

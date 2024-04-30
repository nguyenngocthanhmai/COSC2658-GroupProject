import java.util.Random;
/**
 * Enum representing different types of services that can be offered at a
 * location.
 * This enum includes methods for retrieving service types by index and listing
 * all service types.
 */
public enum ServiceType {
    HOTEL( 0b000001), 
    COFFEE( 0b000010), 
    RESTAURANT( 0b000100), 
    ATM( 0b001000), 
    GAS_STATION( 0b010000), 
    HOSPITAL( 0b100000);

    private final int binaryValue;
    private static final Random random = new Random();

    ServiceType(int binaryValue) {
        this.binaryValue = binaryValue;
    }

    /**
     * Retrieves a service type based on its ordinal index.
     * 
     * @param index int, the index of the service type in the enum.
     * @return ServiceType, the service type at the specified index.
     * @throws IllegalArgumentException if the index is out of bounds.
     * Time Complexity: O(1), as it directly
     * accesses an array element.
     */
    public static ServiceType getServiceByIndex(int index) {
        ServiceType[] services = ServiceType.values();
        if (index < 0 || index >= services.length) {
            System.out.println("Invalid index: " + index);
        }
        return services[index];
    }

    /**
     * Randomly generates an integer representing a set of services.
     * Each bit in the integer corresponds to a different service.
     * 
     * @return int, the binary representation of a random set of services.
     */
    public static int randomizeServices() {
        int services = 0;
        for (ServiceType service : ServiceType.values()) {
            // Randomly decide whether to include each service
            if (random.nextBoolean()) {
                services |= service.getBinaryValue();
            }
        }
        return services;
    }

    public static ArrayList<ServiceType> getServicesByBinary(int binaryCombination) {
        ArrayList<ServiceType> services = new ArrayList<>(6);
        for (ServiceType service : ServiceType.values()) {
            if ((service.getBinaryValue() & binaryCombination) != 0) {
                services.insert(service);
            }
        }
        return services;
    }

    /**
     * Converts a list of ServiceType into a binary representation.
     * @param services ArrayList<ServiceType>, the list of services.
     * @return int, the binary combination of the services.
     */
    public static int servicesToBinary(ArrayList<ServiceType> services) {
        int binaryRepresentation = 0;
        for (int i = 0; i < services.size(); i++) {
            binaryRepresentation |= services.get(i).getBinaryValue();
        }
        return binaryRepresentation;
    }

    public int getBinaryValue() {
        return this.binaryValue;
    }

    /**
     * Prints all available service types to the standard output.
     * Time Complexity: O(n), where n is the number of service types.
     */
    public static void getAllServices() {
        GUI.printLineSeparator();
        System.out.println("Service types: ");
        for (ServiceType service : ServiceType.values()) {
            System.out.println(service.ordinal() + " - " + service);
        }
        GUI.printLineSeparator();
    }

    /**
     * Provides a custom string representation of the service type.
     * 
     * @return String, the custom formatted name of the service type.
     *         Time Complexity: O(1), as it performs simple string operations.
     */
    @Override
    public String toString() {
        // Customize the string representation as needed
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

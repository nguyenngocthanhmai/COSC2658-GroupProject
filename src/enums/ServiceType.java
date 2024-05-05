package enums;
import java.util.Random;

import gui.GUI;
import utils.ArrayList;
/**
 * Enum representing different types of services that can be offered at a
 * location. This enum includes methods for retrieving service types by index,
 * generating random service combinations, converting between service lists and
 * binary representations, and listing all service types.
 */
public enum ServiceType {
    // Enum constants, each representing a service with a unique binary value.
    HOTEL( 0b000001), 
    COFFEE( 0b000010), 
    RESTAURANT( 0b000100), 
    ATM( 0b001000), 
    GAS_STATION( 0b010000), 
    HOSPITAL( 0b100000);

    private final int binaryValue; // Stores the binary representation of the service.
    private static final int numberOfService = 6; // Total number of services available.
    private static final Random random = new Random(); // Random generator for service selection.

    /**
     * Constructor to initialize the enum constants with their binary values.
     * @param binaryValue The binary value associated with the service type.
    */
    ServiceType(int binaryValue) {
        this.binaryValue = binaryValue;
    }

    /**
     * Retrieves a service type based on its ordinal index.
     * @param index The index of the service type in the enum.
     * @return The service type at the specified index.
     * @throws IllegalArgumentException if the index is out of bounds.
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
     * @return The binary representation of a random set of services.
     */
    public static int randomizeServices() {
        int randomIndex = random.nextInt(numberOfService); // There are 6 service types
        return switch (randomIndex) {
            case 0 -> ServiceType.HOTEL.getBinaryValue();
            case 1 -> ServiceType.COFFEE.getBinaryValue();
            case 2 -> ServiceType.RESTAURANT.getBinaryValue();
            case 3 -> ServiceType.ATM.getBinaryValue();
            case 4 -> ServiceType.GAS_STATION.getBinaryValue();
            case 5 -> ServiceType.HOSPITAL.getBinaryValue();
            default -> throw new AssertionError("Unknown enum index " + randomIndex);
        };
    }

    /**
     * Retrieves a list of ServiceType based on a binary combination.
     * @param binaryCombination The binary combination of services.
     * @return A list of ServiceType that are represented by the binary combination.
     */
    public static ArrayList<ServiceType> getServicesByBinary(int binaryCombination) {
        ArrayList<ServiceType> services = new ArrayList<>(numberOfService);
        for (ServiceType service : ServiceType.values()) {
            if ((service.getBinaryValue() & binaryCombination) != 0) {
                services.insert(service);
            }
        }
        return services;
    }

    /**
     * Converts a list of ServiceType into a binary representation.
     * @param services The list of services.
     * @return The binary combination of the services.
     */
    public static int servicesToBinary(ArrayList<ServiceType> services) {
        int binaryRepresentation = 0;
        for (int i = 0; i < services.size(); i++) {
            binaryRepresentation |= services.get(i).getBinaryValue();
        }
        return binaryRepresentation;
    }

    /**
     * Gets the binary value of the service type.
     * @return The binary value of this service type.
     */
    public int getBinaryValue() {
        return this.binaryValue;
    }

    /**
     * Prints all available service types to the standard output.
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
     * @return The custom formatted name of the service type.
     */
    @Override
    public String toString() {
        // Customize the string representation as needed
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

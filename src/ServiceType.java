public enum ServiceType {
    HOTEL, COFFEE, RESTAURANT, ATM, GAS_STATION, HOSPITAL;

    public static ServiceType getServiceByIndex(int index) {
        ServiceType[] services = ServiceType.values();
        if (index < 0 || index >= services.length) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        return services[index];
    }

    public static void getAllServices(){
        GUI.printLineSeperator();
        System.out.println("Service types: ");
        for (ServiceType service : ServiceType.values()) {
            System.out.println(service.ordinal() + " - " + service.toString());
        }
        GUI.printLineSeperator();
    }

     @Override
    public String toString() {
        // Customize the string representation as needed
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

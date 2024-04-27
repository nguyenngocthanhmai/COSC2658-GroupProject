import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        Random rnd = new Random();
        Place[] places = new Place[100000000];
        // GUI.initialize();
        for (int i = 0; i < 100000000; i++) {
            double x = rnd.nextDouble() * 10000000;
            double y = rnd.nextDouble() * 10000000;
            ServiceType serviceType = ServiceType.values()[rnd.nextInt(ServiceType.values().length)];
            ArrayList<ServiceType> serviceSet = new ArrayList<>(ServiceType.values().length);
            serviceSet.insert(serviceType);
            places[i] = new Place(serviceSet, x, y);
        }
        long memoryUsed = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024; // Convert to megabytes
        System.out.println("Memory Used: " + memoryUsed + " MB");
    }
}
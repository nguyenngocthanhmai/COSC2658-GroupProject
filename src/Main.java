import java.util.ArrayList;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Rectangle boundary = new Rectangle(10000000/2, 10000000/2, 10000000, 10000000);
        int capacity = 10000;
        // QuadTree qt = new QuadTree(boundary, capacity, 0);
        QuadTree qt = new QuadTree(boundary, capacity);

        //Generate some random place and insert it into the Quadtree
        Random rnd = new Random(System.currentTimeMillis());
        long startTime = System.currentTimeMillis();
        String[] services = {"Hotel", "Coffee", "Restaurant", "ATMs", "Gas Station", "Hospital"};
        for(int i = 0; i < 100000000; i++){
            double x = rnd.nextDouble() * 10000000;
            double y = rnd.nextDouble() * 10000000;
            String service = services[rnd.nextInt(services.length)];
            Point p = new Point(x, y);
            Place place = new Place(p, service);
            qt.insert(place);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        ArrayList<Place> places = qt.getAllPoints();
        System.out.println("Places: " + places.size());

        System.out.println("Insert successfully!");
    }
}
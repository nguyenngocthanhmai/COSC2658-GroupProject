import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Rectangle boundary = new Rectangle(0, 0, 100, 100);
        int capacity = 4;
        QuadTree qt = new QuadTree(boundary, capacity, 0);

        //Generate some random place and insert it into the Quadtree
        Random rnd = new Random(System.currentTimeMillis());
        String[] services = {"Hotel", "Coffee", "Restaurant", "ATMs", "Gas Station", "Hospital"};
        for(int i = 0; i < 5; i++){
            double x = rnd.nextDouble() * 50;
            double y = rnd.nextDouble() * 50;
            String service = services[rnd.nextInt(services.length) - 1];
            Point p = new Point(x, y);
            Place place = new Place(p, service);
            qt.insert(place);
        }
        System.out.println("Insert successfully!");

    }
}
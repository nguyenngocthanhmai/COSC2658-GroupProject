package benchMark;

import java.util.Random;
import maps.Map2D;
import utils.Rectangle;

public class FindIdealDepthQuadtree {
    public static void main(String[] args) {
        int[] numberOfNodes = new int[]{1000000, 10000000, 20000000, 50000000, 70000000, 100000000};

        for (int numberOfNode : numberOfNodes) {
            System.out.println("##########################################################################");
            System.out.println("Number of nodes: " + String.format("%,d", numberOfNode));
            for (int i = 1; i < 7; i++) {
                Runtime runtime = Runtime.getRuntime();
                Rectangle boundary = new Rectangle(10000000 / 2, 10000000 / 2, 10000000, 10000000);
                int desiredDepth = i;
                Map2D qt = new Map2D(boundary, Map2D.calculateIdealCapacity(numberOfNode, desiredDepth));
                long startTime = System.currentTimeMillis();
                qt.generateRandomData(numberOfNode);
                long endTime = System.currentTimeMillis();
                System.out.println("------------------------------------");
                System.out.println("Depth: " + i + "; Capacity per node: " + String.format("%,d", Map2D.calculateIdealCapacity(numberOfNode, i)) + "; Time: " + (endTime - startTime) + "ms; Memory: " + String.format("%,d", (runtime.totalMemory() - runtime.freeMemory()) / 1000000) + "MB");
                // Benchmark search time
                benchmarkSearchTime(qt);
            }
        }
    }

    private static void benchmarkSearchTime(Map2D qt) {
        Random rnd = new Random();
        long totalSearchTime = 0;
        int numberOfSearches = 100;

        for (int j = 0; j < numberOfSearches; j++) {
            // Create a random search rectangle, size and position varies
            int x = rnd.nextInt(10000000);
            int y = rnd.nextInt(10000000);
            int width = rnd.nextInt(5000000) + 1000; // Ensure minimum size to make it interesting
            int height = rnd.nextInt(5000000) + 1000;
            Rectangle searchRect = new Rectangle(x, y, width, height);

            long searchStart = System.currentTimeMillis();
            qt.search(searchRect, null, null, 1000); // Perform the search
            long searchEnd = System.currentTimeMillis();

            totalSearchTime += (searchEnd - searchStart);
        }

        double averageSearchTime = totalSearchTime / (double) numberOfSearches;
        System.out.println("Average search time for " + numberOfSearches + " searches: " + String.format("%.2f", averageSearchTime) + "ms");
    }
}
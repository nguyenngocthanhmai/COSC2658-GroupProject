package benchMark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Random;
import maps.Map2D;
import utils.Rectangle;

public class Benchmark {
    public static void main(String[] args) {
        int[] numberOfNodes = new int[]{1000000,10000000,20000000,50000000,70000000,100000000};
        try (FileWriter writer = new FileWriter(new File(FileSystems.getDefault().getPath("src/","benchMark", "benchmark_results.csv").toString()).getCanonicalPath())) {
            // Write the header of the CSV file
            writer.append("Depth,NodeCount,InitializationTime,MemoryUsage,SearchTime\n");

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
                    long memoryUsage = (runtime.totalMemory() - runtime.freeMemory()) / 1000000;
                    long initializationTime = endTime - startTime;

                    System.out.println("------------------------------------");
                    System.out.println("Depth: " + i + "; Capacity per node: " + String.format("%,d", Map2D.calculateIdealCapacity(numberOfNode, i)) + "; Initialization Time: " + initializationTime + "ms; Memory Usage: " + String.format("%,d", memoryUsage) + "MB");

                    // Benchmark search time
                    double averageSearchTime = benchmarkSearchTime(qt);
                    System.out.println("Average search time for 100 searches: " + String.format("%.2f", averageSearchTime) + "ms");

                    // Write data to CSV file
                    writer.append(String.format("%d,%d,%d,%d,%.2f\n", i, numberOfNode, initializationTime, memoryUsage, averageSearchTime));
                    writer.flush();  // Explicitly flush after each write operation
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double benchmarkSearchTime(Map2D qt) {
        Random rnd = new Random();
        long totalSearchTime = 0;
        int numberOfSearches = 100;

        for (int j = 0; j < numberOfSearches; j++) {
            int x = rnd.nextInt(10000000);
            int y = rnd.nextInt(10000000);
            int width = rnd.nextInt(5000000) + 1000;
            int height = rnd.nextInt(5000000) + 1000;
            Rectangle searchRect = new Rectangle(x, y, width, height);

            long searchStart = System.currentTimeMillis();
            qt.search(searchRect, null, null, 1000);
            long searchEnd = System.currentTimeMillis();

            totalSearchTime += (searchEnd - searchStart);
        }

        return totalSearchTime / (double) numberOfSearches;
    }
}
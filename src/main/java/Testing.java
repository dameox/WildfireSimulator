import java.io.*;

public class Testing {

        public static void main(String[] args) {

            try (PrintWriter out = new PrintWriter(new FileWriter(System.getProperty("user.dir") + "/src/main/resources/testResults.txt"))) {
            WalkAlgorithm algo = new WalkAlgorithm();
            int tests=10;
            out.println("==========================================");
            out.println("WILD FIRE SIMULATION TEST");
            out.println("==========================================\n");

            out.println("Grid scaling test with k=1\n");
            runBatch(algo, 50, 50, 1, tests, out);
            runBatch(algo, 100, 100, 1, tests,out);
            runBatch(algo, 200, 200, 1, tests, out);

            out.println("Ignition points test with 100x100 grid)");
            runBatch(algo, 100, 100, 5, tests, out);
            runBatch(algo, 100, 100, 10, tests, out);
            runBatch(algo, 100, 100, 50, tests, out);



            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }

        public static void runBatch(WalkAlgorithm algo, int n, int m,int k, int tests, PrintWriter out) {
            long sumTicks = 0;
            long sumTime =0;
            TextParser.n=n;
            TextParser.m=m;
            TextParser.k=k;

            for (int i = 0; i < tests; i++) {
                algo.generateForest();
                algo.startIgnition();
                long startTime = System.currentTimeMillis();
                int ticks =0;

                while(!Grid.burningPositions.isEmpty()) {
                    algo.spreadFire(n,m,TextParser.spread,TextParser.burnTicks);
                    ticks++;
                }
                sumTicks += ticks;
                sumTime = System.currentTimeMillis() - startTime;
                algo.restartSimulation();
            }
            double avgTicks = (double) sumTicks / tests;
            double avgTime = (double) sumTime / tests;
            out.println("test parameters: " + "n="+n + " m="+ m +" k="+ k +" tests=" + tests);
            out.println("avg ticks: " + avgTicks);
            out.println("avg absolute time: " + avgTime + " ms\n");
        }
}


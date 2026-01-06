public class Main {

    public static void main(String[] args) {
        TextParser.loadInstruction();
        WalkAlgorithm algo = new WalkAlgorithm();
        algo.generateForest();

        Logger.log("Started simulation");
        WalkAlgorithm.print2D(Grid.grid);

    }


}

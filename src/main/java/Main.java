public class Main {

    public static void main(String[] args) {
        TextParser.loadInstruction();
        WalkAlgorithm algo = new WalkAlgorithm();
        algo.generateForest();
        print2D(Grid.grid);
        algo.startIgnition(TextParser.k);
        print2D(Grid.grid);
        for(int i =0; i<10;i++) {
            algo.spreadFire(TextParser.n, TextParser.m, TextParser.spread, TextParser.burnTicks);
            print2D(Grid.grid);
            System.out.println();

        }

    }

    public static void print2D(Tile[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j].tileState + " ");
            }
            System.out.println();
        }
    }



}

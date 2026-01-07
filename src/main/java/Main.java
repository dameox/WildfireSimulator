import javax.swing.Timer;

public class Main {

    public static void main(String[] args) {
        TextParser.loadInstruction();
        WalkAlgorithm algo = new WalkAlgorithm();
        VisualRenderer vr = new VisualRenderer();
        algo.generateForest();
        System.out.println("Tree count in data: " + Grid.treePositions.size());
        vr.updateRender();
        print2D(Grid.grid);
        algo.startIgnition(TextParser.k);
        vr.updateRender();
        print2D(Grid.grid);
        Timer timer = new Timer(1000, null);
        timer.addActionListener(e -> {
            algo.spreadFire(TextParser.n, TextParser.m, TextParser.spread, TextParser.burnTicks);
            vr.updateRender();
            if(Grid.burningPositions.isEmpty()){
                timer.stop();
            }
        });
        timer.start();



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

import javax.swing.Timer;

public class Main {
    static int timeCount = 0;

    public static void main(String[] args) {
        TextParser.loadInstruction();
        WalkAlgorithm algo = new WalkAlgorithm();
        VisualRenderer vr = new VisualRenderer();

        algo.generateForest();
        vr.updateRender();



        Timer timer = new Timer(100, null);

        timer.addActionListener(e -> {
            if(timeCount==0){
                timeCount++;
                algo.startIgnition();
                vr.updateRender();
            } else {
                algo.spreadFire(TextParser.n, TextParser.m, TextParser.spread, TextParser.burnTicks);
                vr.updateRender();
                if (Grid.burningPositions.isEmpty()) {
                    timeCount=0;
                    timer.stop();
                    vr.showFinishBox();
                    algo.restartSimulation();
                    algo.generateForest();
                    vr.updateRender();
                }
            }
        });
        vr.startTimer(timer);



    }

    // used in early dev for printing the visuals in the console
    public static void print2D(Tile[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j].tileState + " ");
            }
            System.out.println();
        }
    }



}

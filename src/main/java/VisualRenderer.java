import javax.swing.*;
import java.awt.*;

public class VisualRenderer extends JFrame {
    JPanel[][] panelTiles;
    JPanel buttonPanel;
    JButton startButton;
    private Timer simulationTimer;
    JLabel stepLabel;
    int step = 0;

    // constructor to set up the basic visual elements
    public VisualRenderer(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Wildfire Simulator");
        buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stepLabel = new JLabel("Step: 0");
        stepLabel.setFont(new Font("Serif", Font.BOLD, 12));
        buttonPanel.setPreferredSize(new Dimension(100, 100));
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.addActionListener(e -> {
            if (simulationTimer != null) {
                simulationTimer.start();
                startButton.setEnabled(false);
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stepLabel);
        add(buttonPanel, BorderLayout.SOUTH);
        panelTiles = new JPanel[TextParser.n][TextParser.m];
        JPanel gridPanel = new JPanel(new GridLayout(TextParser.n, TextParser.m, 1, 1));

        for(int i = 0; i < TextParser.n; i++) {
            for(int j = 0; j < TextParser.m; j++) {
                JPanel tile = new JPanel();
                tile.setBackground(Color.WHITE);
                tile.setPreferredSize(new Dimension(10, 10));
                panelTiles[i][j] = tile;
                gridPanel.add(tile);
            }
        }
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setPreferredSize(new Dimension(800, 800));
        add(scrollPane);
        add(gridPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    // repaints the visual elements to match game logic
    public void updateRender(){
        for(int i = 0; i < TextParser.n; i++) {
            for(int j = 0; j < TextParser.m; j++) {
                if(Grid.grid[i][j].tileState==TileState.TREE){
                    panelTiles[i][j].setBackground(Color.GREEN);
                }else if(Grid.grid[i][j].tileState==TileState.BURNING){
                    panelTiles[i][j].setBackground(Color.RED);
                }else if(Grid.grid[i][j].tileState==TileState.BURNED){
                    panelTiles[i][j].setBackground(Color.GRAY);
                } else{
                    panelTiles[i][j].setBackground(Color.WHITE);
                }
            }
        }
        step++;
        stepLabel.setText("Step: " + step);
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }

    // shows a simple message box after finishing the simulation
    public void showFinishBox(){
        JOptionPane.showMessageDialog(null, "Simulation finished!");
        step=0;
        startButton.setEnabled(true);
    }

    // connects timer in Main class and timer for the visuals
    public void startTimer(Timer timer) {
        simulationTimer = timer;
    }

}

import javax.swing.*;
import java.awt.*;

public class VisualRenderer extends JFrame {
    JPanel[][] panelTiles;
    public VisualRenderer(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Wildfire Simulator");
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
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }

}

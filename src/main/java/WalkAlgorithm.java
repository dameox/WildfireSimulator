import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class WalkAlgorithm {
    int count = 0;
    int nPos;
    int mPos;
    java.util.Random randomSeed = new java.util.Random(TextParser.seed);

    public void generateForest() {
        Logger.log("Generating forest...");
        Grid.grid = new Tile[TextParser.n][TextParser.m];
        ArrayList<Point> treePositions = new ArrayList<>();
        Logger.log("Generating grass tiles");
        for (int x = 0; x < TextParser.n; x++) {
            for (int y = 0; y < TextParser.m; y++) {
                Grid.grid[x][y] = new Tile();
            }
        }

        nPos = TextParser.n / 2;
        mPos = TextParser.m / 2;
        Grid.grid[nPos][mPos].tileState = TileState.TREE;

        Logger.log("Generating beautiful trees");
        while(count < (TextParser.n * TextParser.m)/2) {
            int walkDirection = randomSeed.nextInt(4);

            if(walkDirection == 0 && mPos > 0) {
                Grid.grid[nPos][mPos--].tileState = TileState.TREE;
                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    treePositions.add(new Point(nPos, mPos));
                    count++;
                }
            }
            if(walkDirection == 1 && mPos < TextParser.m-1) {
                Grid.grid[nPos][mPos++].tileState = TileState.TREE;
                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    treePositions.add(new Point(nPos, mPos));
                    count++;
                }
            }
            if(walkDirection == 2 && nPos > 0) {
                Grid.grid[nPos--][mPos].tileState = TileState.TREE;
                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    treePositions.add(new Point(nPos, mPos));
                    count++;
                }
            }
            if(walkDirection == 3 && nPos < TextParser.n-1) {
                Grid.grid[nPos++][mPos].tileState = TileState.TREE;
                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    treePositions.add(new Point(nPos, mPos));
                    count++;
                }
            }
            Grid.treePositions = treePositions;
        }
        Logger.log("Successfully generated forest");
    }

    public void startIgnition(int k){
        ArrayList<Point> treePositions = Grid.treePositions;
        Collections.shuffle(treePositions);
        Logger.log("Starting ignition...");
        for(int i=0;i<k;i++){
            Point p = treePositions.get(i);
            Grid.grid[p.x][p.y].tileState=TileState.BURNING;
            Grid.grid[p.x][p.y].burnTime=1;
            Grid.burningPositions.add(p);
        }
        Logger.log("Finished ignition");

    }

    public void spreadFire(int n, int m, float spread, int burnTicks) {
        ArrayList<Point> toIgnite = new ArrayList<>();
        Logger.log("Spreading fire...");
        for(Point tree : Grid.treePositions) {
            int x = tree.x;
            int y = tree.y;
            boolean foundFire = false;

            for (int tx=-1;tx<2;tx++) {
                for(int ty=-1;ty<2;ty++) {
                    if(tx==0 && ty==0){continue;}
                    int posX = x+tx;
                    int posY = y+ty;

                    if(posX>=0 && posX<n && posY>=0 && posY<m) {
                        if(Grid.grid[posX][posY].tileState==TileState.BURNING) {
                            foundFire = true;
                            break;
                        }
                    }
                }
                if(foundFire) {break;}
            }
            if(foundFire && Math.random()<spread) {
                toIgnite.add(tree);
            }
        }
        updateBurnTime(burnTicks);
        for (Point p : toIgnite) {
            Grid.grid[p.x][p.y].tileState = TileState.BURNING;
            Grid.grid[p.x][p.y].burnTime = 1;
            Grid.burningPositions.add(p);
        }
        Grid.treePositions.removeAll(toIgnite);
        Logger.log("Finished spreading fire");
    }

    public void updateBurnTime(int burnTicks) {
       ArrayList<Point> toRemove = new ArrayList<>();
       Logger.log("Updating burn time");
        for(Point p : Grid.burningPositions) {
            if( Grid.grid[p.x][p.y].burnTime==burnTicks ) {
                Grid.grid[p.x][p.y].tileState = TileState.BURNED;
                toRemove.add(p);
            } else {
                Grid.grid[p.x][p.y].burnTime++;
            }
        }
        Grid.burningPositions.removeAll(toRemove);
        Logger.log("Finished updating burn time");
    }

    public void restartSimulation(){
        Grid.treePositions.clear();
        Grid.burningPositions.clear();
        count=0;
        for(int i=0;i<TextParser.n;i++){
            for(int j=0;j<TextParser.m;j++){
                Grid.grid[i][j].tileState=TileState.EMPTY;
            }
        }
        generateForest();
    }
}

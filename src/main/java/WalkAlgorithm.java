
public class WalkAlgorithm {
    int count = 0;
    int nPos;
    int mPos;
    java.util.Random randomSeed = new java.util.Random(TextParser.seed);

    public void generateForest() {
        Grid.grid = new Tile[TextParser.n][TextParser.m];
        for (int x = 0; x < TextParser.n; x++) {
            for (int y = 0; y < TextParser.m; y++) {
                Grid.grid[x][y] = new Tile();
            }
        }

        nPos = TextParser.n / 2;
        mPos = TextParser.m / 2;
        Grid.grid[nPos][mPos].tileState = TileState.TREE;
        while(count < (TextParser.n * TextParser.m)/2) {
            int walkDirection = randomSeed.nextInt(4);

            if(walkDirection == 0 && mPos > 0) {
                Grid.grid[nPos][mPos--].tileState = TileState.TREE;
                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    count++;
                }
            }
            if(walkDirection == 1 && mPos < TextParser.m-1) {
                Grid.grid[nPos][mPos++].tileState = TileState.TREE;
                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    count++;
                }
            }
            if(walkDirection == 2 && nPos > 0) {
                Grid.grid[nPos--][mPos].tileState = TileState.TREE;
                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    count++;
                }
            }
            if(walkDirection == 3 && nPos < TextParser.n-1) {
                Grid.grid[nPos++][mPos].tileState = TileState.TREE;

                if(Grid.grid[nPos][mPos].tileState != TileState.TREE) {
                    count++;
                }
            }
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

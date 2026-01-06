import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextParser {
    static int n = 10;
    static int m = 10;
    static int k = 1;
    static float spread = 0.3f;
    static int burnTicks = 5;
    static long seed = System.nanoTime();

    public static void loadInstruction(){
        Logger.log("loading instructions");
        try{
            File f = new File( System.getProperty("user.dir") + "/src/main/resources/instructions.txt");
            Scanner sc = new Scanner(f);

            if(sc.hasNextInt()){ n = sc.nextInt();}
            if(sc.hasNextInt()){ m = sc.nextInt();}
            if(sc.hasNextInt()){ k = sc.nextInt();}
            if(sc.hasNextFloat()){ spread = sc.nextFloat();}
            if(sc.hasNextFloat()){ burnTicks = sc.nextInt();}
            if(sc.hasNextLong()){ seed = sc.nextLong();}
            sc.close();
            if(k>(n*m)){
                k=n*m;
            }
            Logger.log("loaded instructions with values: n="+n+", m="+m+", k="+k +", spread="+spread+", burnTicks="+burnTicks +", seed="+seed);
        }catch(FileNotFoundException e){
            Logger.log("Error: File not found");
        }
    }
}

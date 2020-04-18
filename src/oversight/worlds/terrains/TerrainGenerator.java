package oversight.worlds.terrains;

import java.util.Random;

/**
 *
 * @author Raphael Dalangin
 */
public class TerrainGenerator {
    
    // World Parameters (Dimenstions)
    private static final int height = 10;
    private static final int width = 10;
    
    // World Data Storage        
    private float[][] worldHeightData = new float[height * width][3];
    
    public void create () {
        for (int x = 0; x < worldHeightData.length; x++) {
            float[] data = new float[3];
            
            // X axis
            if (x <= 10) {
                data[0] = x;
            }
            else if (x > 10 && x <= 20) {
                data[0] = x - 10;
            }
            else if (x > 20 && x <= 30) {
                data[0] = x - 20;
            }
            else if (x > 30 && x <= 40) {
                data[0] = x - 30;
            }
            else if (x > 40 && x <= 50) {
                data[0] = x - 40;
            }
            else if (x > 50 && x <= 60) {
                data[0] = x - 50;
            }
            else if (x > 60 && x <= 70) {
                data[0] = x - 60;
            }
            else if (x > 70 && x <= 80) {
                data[0] = x - 70;
            }
            else if (x > 80 && x <= 90) {
                data[0] = x - 80;
            }
            else if (x > 90 && x <= 100) {
                data[0] = x - 90;
            }
            
            // Y axis
            data[1] = (float) Math.round(Math.random() * 1);
            
            // Z axis
            if (x <= 10) {
                data[2] = 0;
            }
            else if (x > 10 && x <= 20) {
                data[2] = 1;
            }
            else if (x > 20 && x <= 30) {
                data[2] = 2;
            }
            else if (x > 30 && x <= 40) {
                data[2] = 3;
            }
            else if (x > 40 && x <= 50) {
                data[2] = 4;
            }
            else if (x > 50 && x <= 60) {
                data[2] = 5;
            }
            else if (x > 60 && x <= 70) {
                data[2] = 6;
            }
            else if (x > 70 && x <= 80) {
                data[2] = 7;
            }
            else if (x > 80 && x <= 90) {
                data[2] = 8;
            }
            else if (x > 90 && x <= 100) {
                data[2] = 9;
            }
            worldHeightData[x] = data;
        }
        
//        System.out.print("Data: ");
//        for (float[] element : worldHeightData) {
//            System.out.print("[");
//            for (int x = 0; x < element.length; x++) {
//                if (x <= 1) {
//                    System.out.print(element[x] + ",");
//                } else {
//                    System.out.print(element[x]);
//                }
//            }
//            System.out.print("], ");
//        }        
         
    }
    
    public float[][] getWorldHeightData() {
        return worldHeightData;
    }
    
    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    // How High the Terrain will be
//    private static final float AMPLITUDE = 70.0f;
//    
//    // Random
//    private Random random = new Random();
//    
//    // Seed
//    private int seed;
//    
//    public HeightsGenerator() {
//        this.seed = random.nextInt(1000000000);
//    }
//    
//    public  float generateHeight(int x, int z) {
//        return 1;
//    }      
}

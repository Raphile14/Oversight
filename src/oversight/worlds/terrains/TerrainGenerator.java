package oversight.worlds.terrains;

import java.util.Random;

/**
 *
 * @author Raphael Dalangin
 */
public class TerrainGenerator {
    
    // World Parameters (Dimenstions)
    private static final int height = 50;
    private static final int width = 50;
    
    // Index
    private int index = 0;
    
    // World Data Storage        
    private float[][] worldHeightData = new float[height * width][3];
    
    public void create (NoiseGenerator noiseGenerator) {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                float[] data = new float[3];
            
                // X axis
                data[0] = x;

                // Y axis
                data[1] = Math.round(((float) noiseGenerator.noise((double) x, (double) y, (double) y)) * 5);
//                data[1] = 0;
//                data[1] = (float) Math.round(Math.random() * 1);

                // Z axis
                data[2] = y;
                worldHeightData[index] = data;
                index += 1;                
            }            
        }
        
        index = 0;       
         
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
}

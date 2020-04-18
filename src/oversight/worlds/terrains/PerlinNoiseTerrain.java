package oversight.worlds.terrains;

/**
 *
 * @author Raphael Dalangin
 */
public class PerlinNoiseTerrain {
    
    int cols, rows;
    int scl = 20;
    int w, h;

    public PerlinNoiseTerrain(int cols, int rows) {        
        this.w = 600;
        this.h = 600;
        this.cols = w / scl;
        this.rows = h / scl;
    }
    
    
    public void create() {
        for (int x  = 0; x  < cols; x++) {
            for (int y = 0; y < rows; y++) {
                
            }
        }
    }
}

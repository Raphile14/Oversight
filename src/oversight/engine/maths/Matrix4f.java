package oversight.engine.maths;

/**
 *
 * @author Raphael Dalangin
 */
public class Matrix4f {
    
    public static final int SIZE = 4;
    private float[] elements;
    
    // Getters and Setters
    public float get(int x, int y) {
        return elements[y * SIZE + x];
    }
    
    public void set(int x, int y, float value) {
        elements[y * SIZE + x] = value;
    }
    
    public float[] getAll() {
        return elements;
    }
}

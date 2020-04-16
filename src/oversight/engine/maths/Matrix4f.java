package oversight.engine.maths;

/**
 *
 * @author Raphael Dalangin
 */
public class Matrix4f {
    
    public static final int SIZE = 4;
    private float[] elements = new float[SIZE * SIZE];
    
    // Basis
    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.set(i, j, 0);
            }            
        }
        
        // Diagonal 1s
        result.set(0, 0, 1); // 1000
        result.set(1, 1, 1); // 0100
        result.set(2, 2, 1); // 0010
        result.set(3, 3, 1); // 0001
        
        return result;
    }
    
    // Translation Matrix
    public static Matrix4f translate(Vector3f translate) {
        Matrix4f result = Matrix4f.identity();
        
        result.set(0, 3, translate.getX()); // 100X
        result.set(1, 3, translate.getY()); // 010Y
        result.set(2, 3, translate.getZ()); // 001Z
                                            // 0001
        return result;
    }
    
    // Rotation Matrix
    public static Matrix4f rotate(float angle, Vector3f axis) {
        Matrix4f result = Matrix4f.identity();
        
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float C = 1 - cos;
        
        result.set(0, 0, cos + axis.getX() * axis.getX() * C); 
        result.set(0, 1, axis.getX() * axis.getY() * C - axis.getZ() * sin); 
        result.set(0, 2, axis.getX() * axis.getZ() * C + axis.getY() * sin);         
        result.set(1, 0, axis.getY() * axis.getX() * C + axis.getZ() * sin); 
        result.set(1, 1, cos + axis.getY() * axis.getY() * C); 
        result.set(1, 2, axis.getY() * axis.getZ() * C - axis.getX() * sin); 
        result.set(2, 0, axis.getZ() * axis.getX() * C - axis.getY() * sin); 
        result.set(2, 1, axis.getZ() * axis.getY() * C + axis.getX() * sin); 
        result.set(2, 2, cos + axis.getZ() * axis.getZ() * C); 

        return result;
    }
    
    // Scale Matrix
    public static Matrix4f scale(Vector3f scalar) {
        Matrix4f result = Matrix4f.identity();
        
        result.set(0, 0, scalar.getX()); // X000
        result.set(1, 1, scalar.getY()); // 0Y00
        result.set(2, 2, scalar.getZ()); // 00Z0
                                         // 0001
        return result;
    }
    
    // Multiply Matrices
    public static Matrix4f multiply(Matrix4f matrix, Matrix4f other) {
        Matrix4f result = Matrix4f.identity();
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.set(i, j, matrix.get(i, 0) * other.get(0, j) +
                                 matrix.get(i, 1) * other.get(1, j) + 
                                 matrix.get(i, 2) * other.get(2, j) + 
                                 matrix.get(i, 3) * other.get(3, j));
            }            
        }
        
        return result;
    }
    
    // Helper
    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f result = Matrix4f.identity();
        
        Matrix4f translationMatrix = Matrix4f.translate(position);
        Matrix4f rotXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rotYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rotZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));
        Matrix4f scaleMatrix = Matrix4f.scale(scale);
        
        Matrix4f rotationMatrix = Matrix4f.multiply(rotXMatrix, Matrix4f.multiply(rotYMatrix, rotZMatrix));
        
        result = Matrix4f.multiply(translationMatrix, Matrix4f.multiply(rotationMatrix, scaleMatrix));
        return result;
    }
    
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

package oversight.engine.maths;

/**
 *
 * @author Raphael Dalangin
 */
public class Vector2f {
    
    private float x, y;
    
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    // Operations
    // Add
    public static Vector2f add(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY());
    } 
    
    // Subtract
    public static Vector2f subtract(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY());
    } 
    
    // Multiply
    public static Vector2f multiply(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() * vector2.getX(), vector1.getY() * vector2.getY());
    } 
    
    // Divide
    public static Vector2f divide(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() / vector2.getX(), vector1.getY() / vector2.getY());
    } 
    
    // Pythagorean Theorem
    public static float length(Vector2f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
    }
    
    // Normalization
    public static Vector2f normalize(Vector2f vector) {
        float len = Vector2f.length(vector);
        return Vector2f.divide(vector, new Vector2f(len, len));
    }
    
    // Useful for Light returns direction
    public static float dot(Vector2f vector1, Vector2f vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }

    // Hash Code
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Float.floatToIntBits(this.x);
        hash = 67 * hash + Float.floatToIntBits(this.y);
        return hash;
    }
    
    // if error, check ep 12 12:50

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector2f other = (Vector2f) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        return true;
    } 
    
    // Getters and Setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    
}

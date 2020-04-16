package oversight.engine.graphics;

import oversight.engine.maths.Vector2f;
import oversight.engine.maths.Vector3f;

/**
 *
 * @author Raphael Dalangin
 */
public class Vertex {
    
    private Vector3f position, color;    
    private Vector2f textureCoord;
    
    public Vertex(Vector3f position, Vector3f color, Vector2f textureCoord) {
        this.position = position;
        this.color = color;
        this.textureCoord = textureCoord;
    }
    
    // Getters
    public Vector3f getPosition() {
        return position;
    }
    
    public Vector3f getColor() {
        return color;
    }

    public Vector2f getTextureCoord() {
        return textureCoord;
    }        
}

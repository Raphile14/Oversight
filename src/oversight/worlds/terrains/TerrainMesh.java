package oversight.worlds.terrains;

import oversight.engine.objects.*;
import oversight.engine.graphics.Material;
import oversight.engine.graphics.Mesh;
import oversight.engine.graphics.Vertex;
import oversight.engine.maths.Vector2f;
import oversight.engine.maths.Vector3f;

/**
 *
 * @author Raphael Dalangin
 */
public class TerrainMesh {
    
    private String name;
    private String texturePath;
    private Mesh mesh;
    private Vector3f vector1, vector2, vector3, vector4;
    
    public TerrainMesh (String name, String texturePath) {
        this.name = name;
        this.texturePath = texturePath;
        this.vector1 = new Vector3f(-0.5f,  0.5f,  0.5f);
        this.vector2 = new Vector3f(-0.5f,  0.5f, -0.5f);
        this.vector3 = new Vector3f(0.5f,  0.5f, -0.5f);
        this.vector4 = new Vector3f(0.5f,  0.5f,  0.5f);
        this.mesh = new Mesh(new Vertex[] {
            //Top face
            new Vertex(vector1, new Vector2f(0.0f, 0.0f)),
            new Vertex(vector2, new Vector2f(0.0f, 1.0f)),
            new Vertex(vector3, new Vector2f(1.0f, 1.0f)),
            new Vertex(vector4, new Vector2f(1.0f, 0.0f))
        }, new int[] {
            // Top face
            0, 1, 3,	
            3, 1, 2,	
        }, new Material(texturePath));
    }
    
    public Mesh getMesh() {
        return mesh;
    }    
    
    // Setters
    public void setVector1(Vector3f vector1) {
        this.vector1 = vector1;
    }
    
    public void setVector2(Vector3f vector2) {
        this.vector2 = vector2;
    }

    public void setVector3(Vector3f vector3) {
        this.vector3 = vector3;
    }

    public void setVector4(Vector3f vector4) {
        this.vector4 = vector4;
    }
    
    // Getters
    public Vector3f getVector1() {
        return vector1;
    }

    public Vector3f getVector2() {
        return vector2;
    }

    public Vector3f getVector3() {
        return vector3;
    }

    public Vector3f getVector4() {
        return vector4;
    }
    
}

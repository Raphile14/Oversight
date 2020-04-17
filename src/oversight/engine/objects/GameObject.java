package oversight.engine.objects;

import oversight.engine.graphics.Mesh;
import oversight.engine.maths.Vector3f;

/**
 *
 * @author Raphael Dalangin
 */
public class GameObject {
    
    private Vector3f position, rotation, scale;
    private Mesh mesh;
//    private double temp;

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }
    
    public void update() {
//        temp += 0.02;
//        position.setX((float) Math.sin(temp));
//        rotation.set((float) Math.sin(temp) * 360, (float) Math.sin(temp) * 360, (float) Math.sin(temp) * 360);
//        scale.set((float) Math.sin(temp), (float) Math.sin(temp), (float) Math.sin(temp));
        position.setZ(position.getZ() - 0.05f);
    }
    
    // Getters
    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Mesh getMesh() {
        return mesh;
    }
    
    
}

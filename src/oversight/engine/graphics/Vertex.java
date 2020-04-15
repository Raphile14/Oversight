/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oversight.engine.graphics;

import oversight.engine.maths.Vector3f;

/**
 *
 * @author Admin
 */
public class Vertex {
    
    private Vector3f position;    
    
    public Vertex(Vector3f position) {
        this.position = position;
    }
    
    // Getters
    public Vector3f getPosition() {
        return position;
    }
}

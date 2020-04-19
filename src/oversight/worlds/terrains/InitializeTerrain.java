/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oversight.worlds.terrains;

import oversight.engine.maths.Vector3f;

/**
 *
 * @author Admin
 */
public class InitializeTerrain {
    
    // Stone Block
    public TerrainMesh ground;
    private String name = "Ground"; 
    private String texturePath = "/oversight/textures/blocks/stone.png";
    
    public void create() {
        ground = new TerrainMesh(name, texturePath);
        ground.getMesh().create();
    }    
    
    public void destroy() {
        ground.getMesh().destroy();
    }
}

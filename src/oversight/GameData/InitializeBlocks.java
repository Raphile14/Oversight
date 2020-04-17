package oversight.GameData;

import oversight.engine.objects.Block;

/**
 *
 * @author Raphael Dalangin
 */
public class InitializeBlocks {    
    
    // Stone Block
    public Block stone;
    private String name = "Stone Block"; 
    private String texturePath = "/oversight/textures/blocks/stone.png";
    
    public void create() {
        stone = new Block(name, texturePath);
        stone.getMesh().create();
    }    
    
    public void destroy() {
        stone.getMesh().destroy();
    }
    
}

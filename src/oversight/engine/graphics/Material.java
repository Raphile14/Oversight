package oversight.engine.graphics;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Raphael Dalangin
 */
public class Material {
    
    private String path;
    private Texture texture;
    private float width, height;
    private int textureID;
    
    // Load Texture
    public Material(String path) {
        this.path = path;
    }
    
    public void create() {
        try {
            texture = TextureLoader.getTexture(path.split("[.]")[1], Material.class.getResourceAsStream(path), GL11.GL_NEAREST);
            width = texture.getWidth();
            height = texture.getHeight();
            textureID = texture.getTextureID();
        } catch (IOException ex) {
            System.err.println("Can't find textute at " + path);
        }
        
    }
    
    public void destroy() {
        GL13.glDeleteTextures(textureID);
    }

    // Getters
    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getTextureID() {
        return textureID;
    }
}

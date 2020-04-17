package oversight.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import oversight.engine.io.Window;
import oversight.engine.maths.Matrix4f;
import oversight.engine.objects.Camera;
import oversight.engine.objects.GameObject;

/**
 *
 * @author Raphael Dalangin
 */
public class Renderer {
    
    private Shader shader;  
    private Window window;
    
    public Renderer(Window window, Shader shader) {
        this.shader = shader;
        this.window = window;
    }
    
    public void renderMesh(GameObject object, Camera camera) {
        
        // Enable/Bind
        GL30.glBindVertexArray(object.getMesh().getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());
        
        // Texture Bind
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, object.getMesh().getMaterial().getTextureID());
        
        // Shader Bind
        shader.bind();
        
        // Scale        
        shader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
        
        // View
        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
        
        // Projection
        shader.setUniform("projection", window.getProjectionMatrix());
        
        // Draw Graphics/Elements
        GL11.glDrawElements(GL11.GL_TRIANGLES, object.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        
        // Shader Unbind
        shader.unbind();
        
        // Terminate/Unbind
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
}

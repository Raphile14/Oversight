package oversight.engine.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

/**
 *
 * @author Raphael Dalangin
 */
public class Mesh {
    
    private Vertex[] vertices;
    private int[] indices;
    private Material material;
    private int vao, pbo, ibo, cbo, tbo;
    
    public Mesh(Vertex[] vertices, int[] indices, Material material) {
        this.vertices = vertices;
        this.indices = indices;
        this.material = material;
    }
    
    public void create() {
        // Create Material
        material.create();
        
        // Vertex Array Object (vao)
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);
        
        // Buffer (sends to GPU)
        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3]; 
        for (int i = 0; i < vertices.length; i ++) {
            positionData[i * 3] = vertices[i].getPosition().getX();
            positionData[i * 3 + 1] = vertices[i].getPosition().getY();
            positionData[i * 3 + 2] = vertices[i].getPosition().getZ();
        }
        // Conversion for OpenGL use
        positionBuffer.put(positionData).flip();
        
        pbo = storeData(positionBuffer, 0, 3);        
        
        // Texture Buffer (sends to GPU)
        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
        float[] textureData = new float[vertices.length * 2]; 
        for (int i = 0; i < vertices.length; i ++) {
            textureData[i * 2] = vertices[i].getTextureCoord().getX();
            textureData[i * 2 + 1] = vertices[i].getTextureCoord().getY();
        }
        // Conversion for OpenGL use
        textureBuffer.put(textureData).flip();
        
        tbo = storeData(textureBuffer, 2, 2);
        
        // Indices Allocation Object and conversion for OpenGL use
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();
        
        ibo = GL15.glGenBuffers();
        
        // Bind IBO Buffers
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        
        // Unbind IBO
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    
    private int storeData(FloatBuffer buffer, int index, int size) {
        // Position Buffer Object (vbo)
        int bufferID  = GL15.glGenBuffers(); 
        
        // Bind Buffer and Assign Buffer Data
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        
        // Shader
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        
        // Unbinds Buffer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        return bufferID;
    }
    
    public void destroy() {
        GL15.glDeleteBuffers(pbo);
        GL15.glDeleteBuffers(cbo);
        GL15.glDeleteBuffers(ibo);
        GL15.glDeleteBuffers(tbo);
        
        GL30.glDeleteVertexArrays(vao);
        
        material.destroy();
    }
    
    // Getters
    public Vertex[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public int getVAO() {
        return vao;
    }

    public int getPBO() {
        return pbo;
    }
    
    public int getTBO() {
        return tbo;
    }
    
    public int getIBO() {
        return ibo;
    }
    
    public Material getMaterial() {
        return material;
    }
}

//        // Color Buffer (sends to GPU)
//        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
//        float[] colorData = new float[vertices.length * 3]; 
//        for (int i = 0; i < vertices.length; i ++) {
//            colorData[i * 3] = vertices[i].getColor().getX();
//            colorData[i * 3 + 1] = vertices[i].getColor().getY();
//            colorData[i * 3 + 2] = vertices[i].getColor().getZ();
//        }
//        // Conversion for OpenGL use
//        colorBuffer.put(colorData).flip();
//        
//        cbo = storeData(colorBuffer, 1, 3);

//    public int getCBO() {
//        return cbo;
//    }

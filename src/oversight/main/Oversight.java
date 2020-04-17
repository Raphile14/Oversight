package oversight.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.lwjgl.glfw.GLFW;
import oversight.GameData.InitializeBlocks;
import oversight.engine.graphics.Material;
import oversight.engine.graphics.Mesh;
import oversight.engine.graphics.Renderer;
import oversight.engine.graphics.Shader;
import oversight.engine.graphics.Vertex;
import oversight.engine.io.Input;
import oversight.engine.io.Window;
import oversight.engine.maths.Vector2f;
import oversight.engine.maths.Vector3f;
import oversight.engine.objects.Camera;
import oversight.engine.objects.GameObject;

/**
 *
 * @author Raphael Dalangin
 */
public class Oversight implements Runnable {
    // Game Data
    public static final String gameName = "Oversight";
    public static final String version = "Alpha 0.1.0";
    
    // Time
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    
    // Threads
    public Thread game;
    
    // Renderer
    public Renderer renderer;
    
    // Shaders
    public Shader shader;
    
    // Window Data
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 720;   
    
    // Block Data
    public InitializeBlocks blocks;
    
    // Object Data (temp)
    public GameObject object;
    public GameObject object2;
        
    // Transfer to individual class (entities, objects, blocks, and etc)
    // Meshes
//    public Mesh mesh = new Mesh(new Vertex[]{
//        new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.5f, 0.0f)),
//        new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
//        new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(0.0f, 0.5f)),
//        new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(0.5f, 0.5f))
//    }, new int[] {
//        0, 1, 2,
//        0, 3, 2
//    }, new Material("/oversight/textures/me.png"));        
    
    // Camera
    public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));
    
    // Initialize Threads
    public void start() {
        game = new Thread(this, "game");
        game.start();
        getTime();
        System.out.println("Thread 'game' Started");
    }
    
    // Initialize Objects
    public void init() {
        System.out.println("Oversight Initializing");
        
        // Initialize Shader
        // Vertext is for position. Fragment is for color
        shader = new Shader("/oversight/shaders/mainVertex.glsl", "/oversight/shaders/mainFragment.glsl");                   
        
        // Initialize Window
        window = new Window(WIDTH, HEIGHT, gameName + " " + version);                
        window.setBackgroundColor(1.0f, 0, 0);
        window.create(); 
        
        // Initialize Renderer
        renderer = new Renderer(window, shader);    
        
        // Initialize Blocks
        blocks = new InitializeBlocks();
        blocks.create();
        
        // Where Objects are inputted (transfer to individual classes along mesh on top)
        // Remember to input individual objects in Update() and Render()
        // Position, Rotation, Scale
        // Transfer to World Gen  DONT FORGET THE RENDERER METHOD PUT WORLD GEN THERE TOO W
        object = new GameObject(new Vector3f(0, 0, -5), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), blocks.stone.getMesh());
        object2 = new GameObject(new Vector3f(0, 0, -2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), blocks.stone.getMesh());
        
        // Create Shader
        shader.create();
    }
    
    // Program Loop
    public void run() {
        init();
        boolean programStatus = true;
        while (programStatus) {
            update();
            render();
            
            // If user pressed escape, program is closed
            if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE) || window.shouldClose()) {
                getTime();
                System.out.println("Oversight Closed");
                programStatus = false;
            }
            
            // Swap fullscreen or window
            if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isIsFullscreen());
            
            // Mouse Lock
            if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT) || Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {               
                window.mouseState(true);
            }
        }
        close();
    }
    
    // Updates Game
    private void update() {
        window.update();  
        camera.update();
//        object.update();
        // Show Mouse Position on Button Click (Left or Right)
//        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT) || Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
//            getTime();
//            System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
//            getTime();
//            System.out.println("X: " + Input.getScrollX()+ ", Y: " + Input.getScrollY());
//        }
    }
    
    // Renders Game
    private void render() {
        renderer.renderMesh(object, camera);
        renderer.renderMesh(object2, camera);
        window.swapBuffers();     
    }
    
    // Closes/Terminates
    private void close() {
        // Terminate Oversight Application/Program
        window.destroy();
        blocks.destroy();
        shader.destroy();           
    }
    
    public static void main (String[] args) {
        
        // Start Application
        new Oversight().start();        
    }
    
    // ============================== MISC ============================== //
    public void getTime() {
        Date date = new Date();
        System.out.print("(" + formatter.format(date) + ") ");
    }
}

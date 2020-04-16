package oversight.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import oversight.main.Oversight;
import oversight.engine.maths.Vector3f;

/**
 *
 * @author Raphael Dalangin
 */
public class Window {
    // Window Data
    private int width, height;   
    private String title;
    private long window;
    
    // Frame counter
    public int frames;
    public static long time;   
    
    // Input Data
    public Input input;
    
    // BG Colors
    private Vector3f background = new Vector3f(0, 0, 0);
    
    // Window Size
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullscreen;
    private int[] windowPosX = new int[1], windowPosY = new int[1];
    
    public Window( int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }
    
    public void create() {
        
        // Initialize GLFW
        if(!GLFW.glfwInit()) {
            System.err.println("ERROR: GLFW wasn't initialized");
            return;
        }
        
        // Initialize Input Variable
        input = new Input();
        
        // Create Window
        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);        
        if (window == 0) {
            System.err.println("ERROR: Window wasn't created");
            return;
        }
        
        // Center Window
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowPosX[0] = (videoMode.width() - width) /2;
        windowPosY[0] = (videoMode.height()- height) /2;
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        
        // Open GL
        GL.createCapabilities();
        
        // 3D Depth Etst
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        
        // Call Callbacks
        createCallbacks();
        
        // Show Window
        GLFW.glfwShowWindow(window);
        
        // Set framerate to monitor refresh rate
        GLFW.glfwSwapInterval(1);
        
        // Frame Time Count Initialize
        time = System.currentTimeMillis();
    }
    
    // Callbacks
    private void createCallbacks() {
        // Window Size
        sizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };
        
        // Input Initialization
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }
    
    public void update() {
        // Update Window Size
        if (isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }        
        
        // Set Colors
        GL11.glClearColor(background.getX(), background.getY(), background.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        
        GLFW.glfwPollEvents();
        // Frame Counter
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, Oversight.gameName + " " + Oversight.version + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
        frames += 1;
    }
    
    // Render Buffers
    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }
    
    // Close window
    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }
    
    // Close input operations and terminate program
    public void destroy() {
        input.destroy();
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
    
    // Set Background Color
    public void setBackgroundColor(float r, float g, float b) {
        background.set(r, g, b);
    }
    
    // Getters and Setters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public long getWindow() {
        return window;
    }

    public boolean isIsFullscreen() {
        return isFullscreen;
    }
    
    public void setFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        isResized = true;
        if (isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }
}

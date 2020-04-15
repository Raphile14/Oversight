/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oversight.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import oversight.main.Oversight;

/**
 *
 * @author Admin
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
        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);        
        if (window == 0) {
            System.err.println("ERROR: Window wasn't created");
            return;
        }
        
        // Center Window
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) /2, (videoMode.height()- height) /2);
        GLFW.glfwMakeContextCurrent(window);
        
        // Input Initialization
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        
        // Show Window
        GLFW.glfwShowWindow(window);
        
        // Set framerate to monitor refresh rate
        GLFW.glfwSwapInterval(1);
        
        // Frame Time Count Initialize
        time = System.currentTimeMillis();
    }
    
    public void update() {
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
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}

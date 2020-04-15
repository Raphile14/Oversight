/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oversight.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.lwjgl.glfw.GLFW;
import oversight.engine.io.Input;
import oversight.engine.io.Window;

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
    
    // Window Data
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 720;        
    
    // Threads
    public Thread game;
    
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
        
        // Initialize Window
        window = new Window(WIDTH, HEIGHT, gameName + " " + version);
        window.create();        
    }
    
    // Program Loop
    public void run() {
        init();
        boolean programStatus = true;
        while (programStatus) {
            update();
            render();
            
            // If user pressed escape, program is closed
            if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
                getTime();
                System.out.println("Oversight Closed");
                programStatus = false;
            }
        }
        
        // Terminate Oversight Application/Program
        window.destroy();
    }
    
    // Updates Game
    private void update() {
        window.update();  
        
        // Show Mouse Position on Button Click (Left or Right)
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT) || Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
            getTime();
            System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
        }
    }
    
    // Renders Game
    private void render() {
        window.swapBuffers();     
    }
    
    public static void main (String[] args) {
        new Oversight().start();
    }
    
    // ============================== MISC ============================== //
    public void getTime() {
        Date date = new Date();
        System.out.print("(" + formatter.format(date) + ") ");
    }
}

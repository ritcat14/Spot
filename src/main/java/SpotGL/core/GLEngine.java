package SpotGL.core;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.input.InputHandler;
import SpotGL.core.states.State;
import SpotGL.core.states.StateManager;
import SpotGL.core.states.StateName;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GLEngine implements Runnable {

    private Thread thread;
    private GLFrame glFrame;

    private InputHandler inputHandler;
    private StateManager stateManager;

    private State[] statesToAdd;

    public GLEngine(State... states) {
        this.statesToAdd = states;
    }

    public void init() {
        glFrame = new GLFrame(this, 1920, 1080);

        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));

        inputHandler = glFrame.getInputHandler();
        inputHandler.init(glFrame.getWindowID());

        stateManager = new StateManager(glFrame);
        for (State state : statesToAdd) {
            stateManager.addState(state);
        }
    }

    public synchronized void start() {
        thread = new Thread(this, "Main");
        thread.start();
    }

    public synchronized void stop() {
        stateManager.cleanUp();
        glfwFreeCallbacks(glFrame.getWindowID());
        glfwDestroyWindow(glFrame.getWindowID());

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void addState(State state) {
        stateManager.addState(state);
    }

    public void setState(StateName stateName) {
        stateManager.setState(stateName);
    }

    public void onEvent() {
        stateManager.onInput(inputHandler);
    }

    @Override
    public void run() {
        init();

        glClearColor(0.5f, 0.5f, 0.5f, 0.0f);

        while (!glfwWindowShouldClose(glFrame.getWindowID())) {
            update();

            render();
        }

        stop();

    }

    private void update() {
        glFrame.update();
        inputHandler.update();
        stateManager.update();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        stateManager.render();
        glfwSwapBuffers(glFrame.getWindowID());
    }

    /*
        double lastTime = glfwGetTime();
        int frames = 0;
        int updates = 0;
        while (!glfwWindowShouldClose(glFrame.getWindowID())) {
            double currentTime = glfwGetTime();

            update();
            updates++;

            frames++;
            render();

            if (currentTime - lastTime >= 1.0) {
                System.out.println("FPS:" + frames);
                System.out.println("UPS:" + frames);
                frames = 0;
                lastTime += 1.0;
            }
        }*/
}

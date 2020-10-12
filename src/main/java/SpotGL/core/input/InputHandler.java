package SpotGL.core.input;

import SpotGL.core.GLEngine;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public final class InputHandler {

    private GLEngine glEngine;

    private long window;
    private final int KEYBOARD_SIZE = 512;
    private final int MOUSE_SIZE = 16;

    private int[] keyStates = new int[KEYBOARD_SIZE];
    private boolean[] activeKeys = new boolean[KEYBOARD_SIZE];

    private int[] mouseButtonStates = new int[MOUSE_SIZE];
    private boolean[] activeMouseButtons = new boolean[MOUSE_SIZE];
    private long lastMouseNS = 0;
    private long mouseDoubleClickPeriodNS = 1000000000 / 5; //5th of a second for double click.
    private Vector2f mousePosition = new Vector2f();

    private int NO_STATE = -1;

    public InputHandler(GLEngine engine) {
        this.glEngine = engine;
    }


    public GLFWKeyCallback keyboard = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            activeKeys[key] = action != GLFW_RELEASE;
            keyStates[key] = action;
        }
    };

    public GLFWMouseButtonCallback mouse = new GLFWMouseButtonCallback() {
        @Override
        public void invoke(long window, int button, int action, int mods) {
            activeMouseButtons[button] = action != GLFW_RELEASE;
            mouseButtonStates[button] = action;
        }
    };

    public GLFWCursorPosCallback mousePositionCallback = new GLFWCursorPosCallback() {
        @Override
        public void invoke(long window, double xpos, double ypos) {
            mousePosition = new Vector2f((float)xpos, (float)ypos);
        }
    };

    public void init(long window) {
        this.window = window;

        resetKeyboard();
        resetMouse();
    }

    public void update() {
        resetKeyboard();
        resetMouse();

        glfwPollEvents();
        glEngine.onEvent();
    }

    private void resetKeyboard() {
        for (int i = 0; i < keyStates.length; i++) {
            keyStates[i] = NO_STATE;
        }
    }

    private void resetMouse() {
        for (int i = 0; i < mouseButtonStates.length; i++) {
            mouseButtonStates[i] = NO_STATE;
        }

        long now = System.nanoTime();

        if (now - lastMouseNS > mouseDoubleClickPeriodNS)
            lastMouseNS = 0;
    }

    public boolean keyDown(int key) {
        return activeKeys[key];
    }

    public boolean keyPressed(int key) {
        return keyStates[key] == GLFW_PRESS;
    }

    public boolean keyReleased(int key) {
        return keyStates[key] == GLFW_RELEASE;
    }

    public boolean mouseButtonDown(int button) {
        return activeMouseButtons[button];
    }

    public boolean mouseButtonPressed(int button) {
        return mouseButtonStates[button] == GLFW_RELEASE;
    }

    public boolean mouseButtonReleased(int button) {
        boolean flag = mouseButtonStates[button] == GLFW_RELEASE;
        if (flag) lastMouseNS = System.nanoTime();

        return flag;
    }

    public boolean mouseButtonDoubleClicked(int button) {
        long last = lastMouseNS;
        boolean flag = mouseButtonReleased(button);

        long now = System.nanoTime();

        if (flag && now - last < mouseDoubleClickPeriodNS) {
            lastMouseNS = 0;
            return true;
        }

        return false;
    }

    public Vector2f getMousePosition() {
        return mousePosition;
    }
}
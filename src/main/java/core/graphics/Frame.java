package core.graphics;

import javax.swing.*;

import core.input.EventListener;
import core.input.KeyHandler;
import core.input.MouseHandler;
import core.states.StateManager;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    public static int WIDTH;
    public static int HEIGHT;

    private final GameCanvas gameCanvas;

    private int width, height;

    public Frame(final int width, final int height, EventListener eventListener) {
        this.width = width;
        this.height = height;
        WIDTH = width;
        HEIGHT = height;

        gameCanvas = new GameCanvas(width, height, eventListener);

        setResizable(false);
        setUndecorated(true);
        add(gameCanvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }

    public void setCounters(final int finalTPS, final int finalFPS) {
        gameCanvas.setCounters(finalTPS, finalFPS);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render(StateManager stateManager) {
        gameCanvas.render(stateManager);
    }

    public void close() {
        gameCanvas.close();
    }
}

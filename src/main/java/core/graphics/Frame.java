package core.graphics;

import javax.swing.*;

public class Frame extends JFrame {

    private int width, height;

    private GameCanvas gameCanvas;

    public Frame(int width, int height) {
        this.width = width;
        this.height = height;

        gameCanvas = new GameCanvas(width, height);

        setResizable(false);
        setUndecorated(true);
        add(gameCanvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        //addWindowListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }

    public void setCounters(int finalTPS, int finalFPS) {
        gameCanvas.setCounters(finalTPS, finalFPS);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render() {
        gameCanvas.render();
    }

    public void close() {
        gameCanvas.close();

    }
}

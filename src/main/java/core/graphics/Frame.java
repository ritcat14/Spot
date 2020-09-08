package core.graphics;

import javax.swing.*;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private final GameCanvas gameCanvas;

    private int width, height;

    public Frame(final int width, final int height) {
        this.width = width;
        this.height = height;

        gameCanvas = new GameCanvas(width, height);

        setResizable(false);
        setUndecorated(true);
        add(gameCanvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        // addWindowListener(this);

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

    public void render() {
        gameCanvas.render();
    }

    public void close() {
        gameCanvas.close();

    }
}

package core.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas {

    private static final long serialVersionUID = 1L;
    private BufferedImage currentFrameBuffer;
    private Renderer renderer;
    private int width, height;

    private int TPS, FPS;

    public GameCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        renderer = new Renderer();

        currentFrameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        setPreferredSize(new Dimension(width, height));
        requestFocus();
    }

    public void render() {
        requestFocus();
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics2D graphics = (Graphics2D) currentFrameBuffer.getGraphics();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        renderer.setGraphics(graphics);

        renderer.renderString(15, 25, new Font("Calibri", Font.BOLD, 25), Color.yellow, "FPS:" + FPS);
        renderer.renderString(15, 50, new Font("Calibri", Font.BOLD, 25), Color.yellow, "UPS:" + TPS);

        bs.getDrawGraphics().drawImage(currentFrameBuffer, 0, 0, getWidth(), getHeight(), null);
        graphics.dispose();
        bs.show();
    }

    public void setCounters(int finalTPS, int finalFPS) {
        FPS = finalFPS;
        TPS = finalTPS;
    }

    public void close() {

    }
}

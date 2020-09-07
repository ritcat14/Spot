package core.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas {

    private BufferedImage currentFrameBuffer;
    private Renderer renderer;
    private int width, height;

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
        bs.getDrawGraphics().drawImage(currentFrameBuffer, 0, 0, getWidth(), getHeight(), null);
        graphics.dispose();
        bs.show();
    }

    public void close() {

    }

}

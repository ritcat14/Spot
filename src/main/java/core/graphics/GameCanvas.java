package core.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import core.input.EventListener;
import core.input.KeyHandler;
import core.input.MouseHandler;
import core.states.StateManager;

public class GameCanvas extends Canvas {

    private static final long serialVersionUID = 1L;

    private final KeyHandler keyHandler;
    private final MouseHandler mouseHandler;

    private BufferedImage currentFrameBuffer;
    private Renderer renderer;
    private int width, height;

    private int TPS, FPS;

    public GameCanvas(int width, int height, EventListener eventListener) {
        this.width = width;
        this.height = height;
        renderer = new Renderer();

        currentFrameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        setPreferredSize(new Dimension(width, height));
        requestFocus();

        keyHandler = new KeyHandler(eventListener);
        mouseHandler = new MouseHandler(eventListener);

        addKeyListener(keyHandler);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void render(StateManager stateManager) {
        requestFocus();
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics2D graphics = (Graphics2D) currentFrameBuffer.getGraphics();
        renderer.setGraphics(graphics);
        renderer.fillRectangle(new Rectangle(0, 0, width, height), Color.GRAY);

        stateManager.render(renderer);

        renderer.renderString("FPS:" + FPS, new Point(15, 25), Color.yellow);
        renderer.renderString("UPS:" + TPS, new Point(15, 50), Color.yellow);

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

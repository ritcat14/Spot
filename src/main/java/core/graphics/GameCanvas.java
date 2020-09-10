package core.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import core.gui.ComponentAnchor;
import core.gui.GLabel;
import core.input.EventListener;
import core.input.KeyHandler;
import core.input.MouseHandler;
import core.states.StateManager;

import static core.graphics.Renderer.staticFont;
import static core.gui.text.TextAlignment.MIDDLE;
import static core.gui.text.TextAlignment.MIDDLE_LEFT;
import static core.gui.text.TextFormat.NONE;

public class GameCanvas extends Canvas {

    private static final long serialVersionUID = 1L;

    private final KeyHandler keyHandler;
    private final MouseHandler mouseHandler;

    private BufferedImage currentFrameBuffer;
    private Renderer renderer;
    private int width, height;

    private int TPS, FPS;
    private GLabel TPSLabal, FPSLabel;

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

        TPSLabal = new GLabel(25, 25, 200, 50, ComponentAnchor.NONE, "UPS:", staticFont,
                Color.YELLOW, null, MIDDLE_LEFT, NONE);

        FPSLabel = new GLabel(25, 50, 200, 50, ComponentAnchor.NONE, "FPS:", staticFont,
                Color.YELLOW, null, MIDDLE_LEFT, NONE);
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

        TPSLabal.setText("UPS:" + TPS);
        TPSLabal.render(renderer);
        FPSLabel.setText("FPS:" + FPS);
        FPSLabel.render(renderer);

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

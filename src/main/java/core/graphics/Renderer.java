package core.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private Graphics2D graphics;

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void renderImage(BufferedImage image) {
        graphics.drawImage(image, 0, 0, null);
    }

}

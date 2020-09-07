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

    public void renderString(int x, int y, Font font, Color colour, String string) {
        Color originC = graphics.getColor();
        Font originF = graphics.getFont();
        graphics.setColor(colour);
        graphics.setFont(font);
        graphics.drawString(string, x, y);
        graphics.setColor(originC);
        graphics.setFont(originF);
    }
}

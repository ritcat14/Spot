package core.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private final Color staticColour = Color.BLACK;
    private final Font staticFont = new Font("Calibri", Font.BOLD, 25);

    private Graphics2D graphics;

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
        graphics.setColor(staticColour);
    }

    /*
    *   Image Rendering
    */

    public void renderImage(BufferedImage image) {
        renderImage(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
    }

    public void renderImage(BufferedImage image, Point point) {
        renderImage(image, new Rectangle((int)point.getX(), (int)point.getY(), 
        image.getWidth(), image.getHeight()));
    }

    public void renderImage(BufferedImage image, Rectangle rectangle) {
        graphics.drawImage(image, (int)rectangle.getX(), (int)rectangle.getY(),
        (int)rectangle.getWidth(), (int)rectangle.getHeight(), null);
    }

    /*
    *   Rectangle Rendering
    */

    public void drawRectangle(Rectangle rectangle) {
        drawRectangle(rectangle, staticColour);
    }

    public void drawRectangle(Rectangle rectangle, Color colour) {
        graphics.setColor(colour);
        graphics.drawRect(
            (int)rectangle.getX(), (int)rectangle.getY(), 
            (int)rectangle.getWidth(), (int)rectangle.getHeight());
    }

    public void fillRectangle(Rectangle rectangle) {
        fillRectangle(rectangle, staticColour);
    }

    public void fillRectangle(Rectangle rectangle, Color colour) {
        graphics.setColor(colour);
        graphics.fillRect((int)rectangle.getX(), (int)rectangle.getY(),
        (int)rectangle.getWidth(), (int)rectangle.getHeight());
    }

    /*
    *   String Rendering
    */

    public void renderString(String string, Point point) {
        renderString(string, point, staticColour, staticFont);
    }

    public void renderString(String string, Point point, Color colour) {
        renderString(string, point, colour, staticFont);
    }

    public void renderString(String string, Point point, Color colour, Font font) {
        graphics.setColor(colour);
        graphics.setFont(font);
        graphics.drawString(string, (int)point.getX(), (int)point.getY());
    }
}

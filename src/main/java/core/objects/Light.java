package core.objects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Light {

    private final float[] dist = {0.0f, 1.0f};

    private int x, y, radius;
    private Color color;
    private Color[] colours;

    public Light(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.colours = new Color[2];
        this.colours[0] = color;
        this.colours[1] = Color.BLACK;
    }

    public void render(BufferedImage currentFrame) {
        Graphics2D graphics2D = (Graphics2D) currentFrame.getGraphics();

        Point2D center = new Point2D.Float(x, y);

        RadialGradientPaint lightPaint = new RadialGradientPaint(center, radius, dist, colours);

        graphics2D.setPaint(lightPaint);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.95f));

        graphics2D.fillRect(0, 0, currentFrame.getWidth(), currentFrame.getHeight());
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

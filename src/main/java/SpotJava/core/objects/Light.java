package SpotJava.core.objects;

import java.awt.*;

public class Light {

    private final float[] dist = {0.0f, 1.0f};

    private int x, y, radius;
    private boolean removed = false;
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

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public int getRadius() {
        return radius;
    }

    public Color[] getColours() {
        return colours;
    }

    public float[] getDist() {
        return dist;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void remove() {
        removed = true;
    }

}

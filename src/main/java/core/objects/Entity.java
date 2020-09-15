package core.objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import core.graphics.Renderer;

public abstract class Entity extends Rectangle {

    protected boolean removed = false;
    protected Color background = Color.BLUE;
    protected BufferedImage backgroundImage;

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Entity(int x, int y, int width, int height, Color colour) {
        super(x, y, width, height);
        this.background = colour;
    }

    public Entity(int x, int y, int width, int height, BufferedImage image) {
        super(x, y, width, height);
        this.backgroundImage = image;
    }

    public abstract void update();

    public abstract void render(Renderer renderer);

    public void setColour(Color colour) {
        this.background = colour;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public double getCenterX() {
        return x + (width/2);
    }

    @Override
    public double getCenterY() {
        return y + (height/2);
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

}
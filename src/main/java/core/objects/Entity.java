package core.objects;

import java.awt.Color;
import java.awt.Rectangle;

import core.graphics.Renderer;

public abstract class Entity extends Rectangle {

    private Color colour = Color.BLUE;

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Entity(int x, int y, int width, int height, Color colour) {
        super(x, y, width, height);
        this.colour = colour;
    }

    public abstract void update();

    public abstract void render(Renderer renderer);
    
}
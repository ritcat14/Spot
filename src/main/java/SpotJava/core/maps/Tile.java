package SpotJava.core.maps;

import SpotJava.core.graphics.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private final int MAX_DISTANCE = 300;
    public static final int TILE_SIZE = 40;

    private boolean solid = false;

    private BufferedImage renderImage;

    public Tile(BufferedImage image) {
        this.renderImage = image;
    }

    public void update() {}

    public void render(Renderer renderer, int x, int y) {
        renderer.renderImage(renderImage, new Point(x, y));
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}

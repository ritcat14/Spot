package game.maps;

import core.graphics.Renderer;
import core.objects.Entity;

import java.awt.image.BufferedImage;

import static core.graphics.Frame.HEIGHT;
import static core.graphics.Frame.WIDTH;

public class Tile extends Entity {

    private final int MAX_DISTANCE = 300;
    public static final int TILE_SIZE = 40;

    private BufferedImage renderImage;
    private BufferedImage[] shadingImages;
    private BufferedImage renderShadingImage;

    public Tile(int x, int y, BufferedImage image, BufferedImage[] shadingImages) {
        super(x, y, TILE_SIZE, TILE_SIZE);
        this.renderImage = image;
        this.shadingImages = shadingImages;
        this.renderShadingImage = shadingImages[0];
    }

    @Override
    public void update() {
    }

    public void setShading(double distance) {

        if (distance < 100) renderShadingImage = shadingImages[0];
        else if (distance <= MAX_DISTANCE) {
            int index = (int) (distance / (MAX_DISTANCE/10)) - 1;
            renderShadingImage = shadingImages[index];
        } else renderShadingImage = shadingImages[9];
    }

    @Override
    public void render(Renderer renderer) {
        if (x > -TILE_SIZE && y > -TILE_SIZE && x + TILE_SIZE < WIDTH + TILE_SIZE && y + TILE_SIZE < HEIGHT + TILE_SIZE) {
            if (renderShadingImage != shadingImages[9]) renderer.renderImage(renderImage, this);
            renderer.renderImage(renderShadingImage, this);
        }
    }
}

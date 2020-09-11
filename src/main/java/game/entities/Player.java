package game.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;

import core.files.Images;
import core.graphics.Renderer;
import core.objects.Entity;
import static core.graphics.Frame.WIDTH;
import static core.graphics.Frame.HEIGHT;

public class Player extends Entity {

    public static int N  = 0;
    public static int NE = 1;
    public static int E  = 2;
    public static int SE = 3;
    public static int S  = 4;
    public static int SW = 5;
    public static int W  = 6;
    public static int NW = 7;

    private BufferedImage[] images;
    private int dir = 0;

    public Player() {
        super((WIDTH/2) - 20, (HEIGHT/2) - 20, 40, 40, Color.BLUE);
        images = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            images[i] = Images.getImage("images/player/walking/" + i + "a.png");
        }
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.fillRectangle(this, colour);

        renderer.renderImage(images[dir], this);
    }
    
}
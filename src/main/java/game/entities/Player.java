package game.entities;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import core.util.Images;
import core.graphics.Renderer;
import core.input.KeyEvent;
import core.input.MouseEvent;
import core.objects.Entity;
import static core.graphics.Frame.WIDTH;
import static core.graphics.Frame.HEIGHT;

public class Player extends Entity {

    public static final int N  = 0;
    public static final int NE = 1;
    public static final int E  = 2;
    public static final int SE = 3;
    public static final int S  = 4;
    public static final int SW = 5;
    public static final int W  = 6;
    public static final int NW = 7;

    private BufferedImage[] images;
    private BufferedImage bulletImage;
    private int dir = 0;

    private boolean rendering = false;

    //TODO: to go into a weapon class
    private boolean tryingToShoot = false;
    private int shootingTimeout = 0;

    private List<Bullet> bullets = new ArrayList<>();
    private List<Bullet> removedBullets = new ArrayList<>();

    public Player() {
        super((WIDTH/2) - 20, (HEIGHT/2) - 20, 40, 40, Color.BLUE);
        images = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            images[i] = Images.getImage("images/player/walking/" + i + "a.png");
        }
        bulletImage = Images.getImage("images/bullets/bullet.png");
    }

    public boolean keyPressed(KeyEvent event) {
        switch (event.getKey()) {
            case java.awt.event.KeyEvent.VK_SPACE:
                tryingToShoot = true;
                return true;
        }
        return false;
    }

    public boolean keyReleased(KeyEvent event) {
        switch (event.getKey()) {
            case java.awt.event.KeyEvent.VK_SPACE:
                tryingToShoot = false;
                return true;
        }
        return false;
    }

    @Override
    public void update() {
        backgroundImage = images[dir];
        for (Bullet bullet : bullets) {
            if (bullet.getX() < 0 || bullet.getX() > WIDTH - bullet.getWidth() ||
                bullet.getY() < 0 || bullet.getY() > HEIGHT - bullet.getHeight()) {
                    bullet.remove();
            } else bullet.update();
        }
        if (!rendering) {
            for (Bullet bullet : bullets) if (bullet.isRemoved()) removedBullets.add(bullet);

            bullets.removeAll(removedBullets);
            removedBullets.clear();
        }
        if (tryingToShoot) {
            shootingTimeout--;
            if (shootingTimeout <= 0) {
                bullets.add(new Bullet(this, bulletImage));
                shootingTimeout = 10;
            }
        }
    }

    @Override
    public void render(Renderer renderer) {
        rendering = true;
        renderer.fillRectangle(this, background);

        if (backgroundImage != null) renderer.renderImage(backgroundImage, this);

        for (Bullet bullet : bullets) {
            bullet.render(renderer);
        }
        rendering = false;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }
    
}
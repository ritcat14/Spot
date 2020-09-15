package game.entities;

import java.awt.image.BufferedImage;

import core.graphics.Renderer;
import core.objects.Entity;

public class Bullet extends Entity {

    private double speed = 5;
    private double xVel = speed * Math.cos(Math.toRadians(45));
    private double yVel = speed * Math.cos(Math.toRadians(45));

    private Player player;

    public Bullet(Player player, BufferedImage image) {
        super((int)((player.getX() + (player.getWidth()/2)) - (image.getWidth()/2)),
              (int)((player.getY() + (player.getHeight()/2)) - (image.getHeight()/2)),
            image.getWidth(), image.getHeight(), image);

        this.player = player;

        switch (player.getDir()) {
            case Player.N:
                xVel = 0;
                yVel = -speed;
                break;
            case Player.NE:
                yVel = -yVel;
                break;
            case Player.E:
                yVel = 0;
                xVel = speed;
                break;
            case Player.S:
                xVel = 0;
                yVel = speed;
                break;
            case Player.SW:
                xVel = -xVel;
                break;
            case Player.W:
                yVel = 0;
                xVel = -speed;
                break;
            case Player.NW:
                xVel = -xVel;
                yVel = -yVel;
                break;
            }
        }

    @Override
    public void update() {
        x += xVel;
        y += yVel;
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderImage(backgroundImage, getLocation());
    }

}
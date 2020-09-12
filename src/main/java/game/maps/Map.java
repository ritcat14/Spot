package game.maps;

import core.input.Event;
import core.input.EventDispatcher;
import core.input.EventListener;
import core.input.KeyEvent;
import core.objects.Entity;
import game.entities.Player;

import static core.files.Images.getImage;
import static core.files.Images.loadSprites;
import static core.input.Event.Type.*;
import static core.graphics.Frame.*;
import static game.entities.Player.*;
import static game.maps.Tile.TILE_SIZE;

import java.awt.*;
import java.awt.image.BufferedImage;

import core.graphics.Renderer;

public class Map extends Entity implements EventListener {

    private final int MAX_DISTANCE = 100;

    private boolean keyUp, keyDown, keyLeft, keyRight;

    private Player player;
    private double playerSpeed = 3;
    private double x1 = playerSpeed * Math.cos(Math.toRadians(45));
    private double y1 = playerSpeed * Math.cos(Math.toRadians(45));

    private int tileNumX, tileNumY;
    private Tile[][] tiles;

    public Map(Player player) {
        super(0, 0, WIDTH, HEIGHT, Color.BLACK);
        this.player = player;

        this.tileNumX = WIDTH/TILE_SIZE;
        this.tileNumY = HEIGHT/TILE_SIZE;

        tiles = new Tile[tileNumY][tileNumX];

        BufferedImage tileImage = getImage("images/tiles/grass.png");
        BufferedImage[][] shadingTiles = loadSprites("images/tiles/shading.png", 10, 1);
        BufferedImage[] shadings = shadingTiles[0];

        for (int y = 0; y < tileNumY; y++) {
            for (int x = 0; x < tileNumX; x++) {
                tiles[y][x] = new Tile(x * TILE_SIZE, y * TILE_SIZE, tileImage, shadings);
            }
        }

    }

    public void update() {
        boolean playerDirSet = false;

        int preX = x;
        int preY = y;

        if (keyUp && keyRight) {
            player.setDir(NE);
            y+=y1;
            x-=x1;
            playerDirSet = true;
        } else if (keyRight && keyDown) {
            player.setDir(SE);
            y-=y1;
            x-=x1;
            playerDirSet = true;
        } else if (keyDown && keyLeft) {
            player.setDir(SW);
            y-=y1;
            x+=x1;
            playerDirSet = true;
        } else if (keyLeft && keyUp) {
            player.setDir(NW);
            y+=y1;
            x+=x1;
            playerDirSet = true;
        }

        if (!playerDirSet) {
            if (keyUp) {
                player.setDir(N);
                y+=playerSpeed;
            } else if (keyRight) {
                player.setDir(E);
                x-=playerSpeed;
            } else if (keyDown) {
                player.setDir(S);
                y-=playerSpeed;
            } else if (keyLeft) {
                player.setDir(W);
                x+=playerSpeed;
            }
        }

        for (int y = 0; y < tileNumY; y++) {
            for (int x = 0; x < tileNumX; x++) {
                Tile tile = tiles[y][x];
                double distanceFromPlayer = calculateDistanceBetweenPoints(tile.getX() + (TILE_SIZE/2),
                        tile.getY() + (TILE_SIZE/2), player.getX() + (player.getWidth()/2),
                        player.getY() + (player.getHeight()/2));
                tile.setShading(distanceFromPlayer);
                tile.setLocation((int)(tile.getX() + (this.x - preX)), (int)(tile.getY() + (this.y - preY)));
                tile.update();
            }
        }

        player.update();
    }

    public void render(Renderer renderer) {
        renderer.fillRectangle(this, colour);

        for (int y = 0; y < tileNumY; y++) {
            for (int x = 0; x < tileNumX; x++) {
                tiles[y][x].render(renderer);
            }
        }

        player.render(renderer);
    }

    private boolean keyPressed(KeyEvent event) {
        switch (event.getKey()) {
            case java.awt.event.KeyEvent.VK_W: case java.awt.event.KeyEvent.VK_UP:
                keyUp = true;
                return true;
            case java.awt.event.KeyEvent.VK_A: case java.awt.event.KeyEvent.VK_LEFT:
                keyLeft = true;
                return true;
            case java.awt.event.KeyEvent.VK_S: case java.awt.event.KeyEvent.VK_DOWN:
                keyDown = true;
                return true;
            case java.awt.event.KeyEvent.VK_D: case java.awt.event.KeyEvent.VK_RIGHT:
                keyRight = true;
                return true;
        }
        return false;
    }

    private boolean keyReleased(KeyEvent event) {
        switch (event.getKey()) {
            case java.awt.event.KeyEvent.VK_W: case java.awt.event.KeyEvent.VK_UP:
                keyUp = false;
                return true;
            case java.awt.event.KeyEvent.VK_A: case java.awt.event.KeyEvent.VK_LEFT:
                keyLeft = false;
                return true;
            case java.awt.event.KeyEvent.VK_S: case java.awt.event.KeyEvent.VK_DOWN:
                keyDown = false;
                return true;
            case java.awt.event.KeyEvent.VK_D: case java.awt.event.KeyEvent.VK_RIGHT:
                keyRight = false;
                return true;
        }
        return false;
    }

    @Override
    public void onEvent(Event event) {
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(KEY_PRESSED, event1 -> keyPressed((KeyEvent) event1));
        dispatcher.dispatch(KEY_RELEASED, event1 -> keyReleased((KeyEvent) event1));
    }

    private double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
    
}
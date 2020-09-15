package game.maps;

import core.input.Event;
import core.input.EventDispatcher;
import core.input.EventListener;
import core.input.KeyEvent;
import core.input.MouseEvent;
import core.objects.Entity;
import core.objects.Light;
import game.entities.Player;

import static core.util.Images.getImage;
import static core.input.Event.Type.*;
import static core.graphics.Frame.*;
import static game.entities.Player.*;
import static game.maps.Tile.TILE_SIZE;
import static core.util.Maths.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import core.graphics.Renderer;

public class Map extends Entity implements EventListener {

    private boolean keyUp, keyDown, keyLeft, keyRight;

    private Player player;
    private double playerSpeed = 3;
    private double x1 = playerSpeed * Math.cos(Math.toRadians(45));
    private double y1 = playerSpeed * Math.cos(Math.toRadians(45));

    private int worldWidth = WIDTH;
    private int worldHeight = HEIGHT;

    private int tileNumX = worldWidth / TILE_SIZE;
    private int tileNumY = worldHeight / TILE_SIZE;

    private Tile[][] tiles;
    private Tile grassTile;

    private Light testLight;

    public Map(Player player) {
        super(0, 0, WIDTH, HEIGHT);
        this.player = player;

        tiles = new Tile[tileNumY][tileNumX];

        BufferedImage tileImage = getImage("images/tiles/grass.png");

        grassTile = new Tile(tileImage);

        for (int y = 0; y < tileNumY; y++) {
            for (int x = 0; x < tileNumX; x++) {
                tiles[y][x] = grassTile;
                if (x == 5) tiles[y][x].setSolid(true);
            }
        }

        testLight = new Light(WIDTH/2, HEIGHT/2, 250, new Color(1f, 1f, 0.0f, 0.0f));

    }

    public void update() {
        boolean playerDirSet = false;

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
                tile.update();
            }
        }

        player.update();
    }

    public void render(Renderer renderer) {
        for (int y = 0; y < tileNumY; y++) {
            for (int x = 0; x < tileNumX; x++) {
                Tile tile = tiles[y][x];
                int tileX = this.x + (x * TILE_SIZE) + (TILE_SIZE / 2);
                int tileY = this.y + (y * TILE_SIZE) + (TILE_SIZE / 2);
                if (tileX > -TILE_SIZE && tileY > -TILE_SIZE && tileX + TILE_SIZE < WIDTH + TILE_SIZE &&
                        tileY + TILE_SIZE < HEIGHT + TILE_SIZE) {
                    double dist = calculateDistance(tileX, tileY, player.getCenterX(), player.getCenterY());
                    if (dist < 200) tile.render(renderer, tileX, tileY);
                }
            }
        }

        testLight.render(renderer.getGameCanvas().getCurrentFrameBuffer());

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
        dispatcher.dispatch(KEY_PRESSED, event1 -> player.keyPressed((KeyEvent) event1));
        dispatcher.dispatch(KEY_RELEASED, event1 -> player.keyReleased((KeyEvent) event1));
    }
    
}
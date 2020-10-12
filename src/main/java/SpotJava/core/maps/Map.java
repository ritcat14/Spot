package SpotJava.core.maps;

import SpotJava.core.input.Event;
import SpotJava.core.input.EventDispatcher;
import SpotJava.core.input.EventListener;
import SpotJava.core.input.KeyEvent;
import SpotJava.core.objects.Entity;
import SpotJava.core.objects.Light;
import SpotJava.game.entities.Player;
import SpotJava.core.graphics.Renderer;
import SpotJava.core.gui.text.TextAlignment;
import SpotJava.core.gui.text.TextFormat;

import static SpotJava.core.util.Images.getImage;
import static SpotJava.core.util.Files.*;
import static SpotJava.core.input.Event.Type.*;
import static SpotJava.core.graphics.Frame.*;
import static SpotJava.game.entities.Player.*;
import static SpotJava.core.maps.Tile.TILE_SIZE;
import static SpotJava.core.util.Maths.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;


public abstract class Map extends Entity implements EventListener {

    private static List<Light> lights = new ArrayList<>();
    private static List<Light> lightsToAdd = new ArrayList<>();
    private static List<Light> lightsToRemove = new ArrayList<>();

    private boolean keyUp, keyDown, keyLeft, keyRight;

    private Player player;
    private double playerSpeed = 3;
    private double x1 = playerSpeed * Math.cos(Math.toRadians(45));
    private double y1 = playerSpeed * Math.cos(Math.toRadians(45));

    private int worldWidth;
    private int worldHeight;

    private int tileNumX;
    private int tileNumY;

    private Tile[][] tiles;

    public Map(Player player) {
        super(0, 0, WIDTH, HEIGHT);
        this.player = player;
    }

    protected void loadMap(String mapFile, List<Tile> availableTiles) {
        String[] mapData = extractDataFromResource(mapFile);
        String[] firstRow = mapData[0].split(",");
        tileNumX = firstRow.length - 1;
        tileNumY = mapData.length - 1;

        worldWidth = tileNumX * TILE_SIZE;
        worldHeight = tileNumY * TILE_SIZE;

        tiles = new Tile[tileNumY][tileNumX];

        for (int y = 0; y < tileNumY; y++) {
            String[] rowData = mapData[y].split(",");
            for (int x = 0; x < tileNumX; x++) {
                int data = Integer.valueOf(rowData[x]);
                tiles[y][x] = availableTiles.get(data);
            }
        }
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

        player.render(renderer);
        
        lights.addAll(lightsToAdd);
        lightsToAdd.clear();
        for (Light light : lights) {
            if (light.isRemoved()) {
                lightsToRemove.add(light);
            }
        }
        lights.removeAll(lightsToRemove);
        lightsToRemove.clear();
        renderer.renderLights(lights);

        Font textFont = Renderer.staticFont.deriveFont(15f);

        renderer.renderString("World width:" + worldWidth, textFont, Color.YELLOW, new Rectangle(25, 100, 200, 50), TextAlignment.MIDDLE_LEFT, TextFormat.NONE);
        renderer.renderString("World height:" + worldHeight, textFont, Color.YELLOW, new Rectangle(25, 125, 200, 50), TextAlignment.MIDDLE_LEFT, TextFormat.NONE);
        renderer.renderString("World X:" + x, textFont, Color.YELLOW, new Rectangle(25, 150, 200, 50), TextAlignment.MIDDLE_LEFT, TextFormat.NONE);
        renderer.renderString("World Y:" + y, textFont, Color.YELLOW, new Rectangle(25, 175, 200, 50), TextAlignment.MIDDLE_LEFT, TextFormat.NONE);
        renderer.renderString("Tile Num X:" + (tileNumX+1), textFont, Color.YELLOW, new Rectangle(25, 200, 200, 50), TextAlignment.MIDDLE_LEFT, TextFormat.NONE);
        renderer.renderString("Tile Num Y:" + (tileNumY+1), textFont, Color.YELLOW, new Rectangle(25, 225, 200, 50), TextAlignment.MIDDLE_LEFT, TextFormat.NONE);
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

    public static void addLight(Light light) {
        lightsToAdd.add(light);
    }
    
}
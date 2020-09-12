package game.maps;

import core.input.Event;
import core.input.EventDispatcher;
import core.input.EventListener;
import core.input.KeyEvent;
import core.objects.Entity;
import game.entities.Player;

import static core.input.Event.Type.*;
import static core.graphics.Frame.*;
import static game.entities.Player.*;

import java.awt.Color;

import core.graphics.Renderer;

public class Map extends Entity implements EventListener {

    private boolean keyUp, keyDown, keyLeft, keyRight;

    private Player player;
    private double playerSpeed = 3;
    private double x1 = playerSpeed * Math.cos(Math.toRadians(45));
    private double y1 = playerSpeed * Math.cos(Math.toRadians(45));

    public Map(Player player) {
        super(0, 0, WIDTH, HEIGHT, Color.GREEN);
        this.player = player;
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

        player.update();
    }

    public void render(Renderer renderer) {
        renderer.fillRectangle(this, colour);
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
    
}
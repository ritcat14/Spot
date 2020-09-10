package core.input;

import java.awt.event.KeyListener;

import static core.input.Event.Type.KEY_PRESSED;
import static core.input.Event.Type.KEY_RELEASED;

public class KeyHandler implements KeyListener {

    private EventListener eventListener;

    public KeyHandler(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent keyEvent) {
        eventListener.onEvent(new KeyEvent(KEY_PRESSED, keyEvent));
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent keyEvent) {
        eventListener.onEvent(new KeyEvent(KEY_RELEASED, keyEvent));
    }
}

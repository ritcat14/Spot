package SpotJava.core.input;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static SpotJava.core.input.Event.Type.*;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private EventListener eventListener;

    public MouseHandler(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent mouseEvent) {
        eventListener.onEvent(new MouseEvent(MOUSE_PRESSED, mouseEvent));
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {
        eventListener.onEvent(new MouseEvent(MOUSE_RELEASED, mouseEvent));
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent mouseEvent) {
        eventListener.onEvent(new MouseEvent(MOUSE_DRAGGED, mouseEvent));
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent mouseEvent) {
        eventListener.onEvent(new MouseEvent(MOUSE_MOVED, mouseEvent));
    }
}
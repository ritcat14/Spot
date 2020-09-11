package core.gui;

import core.graphics.Renderer;
import core.gui.text.TextAlignment;
import core.input.*;
import core.input.Event;

import java.awt.*;

import static core.input.Event.Type.*;

public class GButton extends GLabel implements EventListener {

    private Rectangle backgroundRect;
    private String text = "";
    private Color foreground;

    private boolean mouseOver = false;

    public GButton(int x, int y, int width, int height, String text, Font font, Color background, Color foreground, Color fontColour,
                   TextAlignment textAlignment, int textFormat, ComponentAnchor componentAnchor) {
        super(x, y, width, height, componentAnchor, text, font, fontColour, background, textAlignment, textFormat);
        this.foreground = foreground;
        int fifPer = (int)Math.ceil(width * 0.15);
        this.backgroundRect = new Rectangle(this.x - (fifPer / 2), this.y, this.width + fifPer, this.height);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        if (mouseOver) renderer.fillRectangle(backgroundRect, foreground);

        renderer.fillRectangle(this, colour);

        super.render(renderer);
    }

    protected boolean mousePressed(MouseEvent event) {
        return contains(event.getPosition());
    }

    protected boolean mouseReleased(MouseEvent event) {
        return contains(event.getPosition());
    }

    protected boolean mouseMoved(MouseEvent event) {
        mouseOver = contains(event.getPosition());
        return mouseOver;
    }

    @Override
    public void onEvent(Event event) {
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(MOUSE_PRESSED, event1 -> mousePressed((MouseEvent) event1));
        dispatcher.dispatch(MOUSE_RELEASED, event1 -> mouseReleased((MouseEvent) event1));
        dispatcher.dispatch(MOUSE_MOVED, event1 -> mouseMoved((MouseEvent) event1));
    }

    @Override
    public void close() {

    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }
}

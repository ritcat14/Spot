package SpotJava.core.gui;

import SpotJava.core.graphics.Renderer;
import SpotJava.core.gui.text.TextAlignment;
import SpotJava.core.input.*;
import SpotJava.core.input.Event;

import java.awt.*;

import static SpotJava.core.input.Event.Type.*;

public class GButton extends GLabel implements EventListener {

    private Rectangle backgroundRect;
    private String text = "";
    private Color foreground, background;

    private boolean mouseOver = false;

    private boolean enabled = true;

    public GButton(int x, int y, int width, int height, String text, Font font, TextAlignment textAlignment, int textFormat,
    ComponentAnchor componentAnchor) {
        super(x, y, width, height, componentAnchor, text, font, Color.BLACK, Color.LIGHT_GRAY, textAlignment, textFormat);
        this.foreground = Color.DARK_GRAY;
        int fifPer = (int)Math.ceil(width * 0.15);
        this.backgroundRect = new Rectangle(this.x - (fifPer / 2), this.y, this.width + fifPer, this.height);
        this.background = Color.LIGHT_GRAY;
    }

    @Override
    public void adjustLocation(int newx, int newy) {
        super.adjustLocation(newx, newy);
        backgroundRect.setLocation((int)(backgroundRect.getX() + newx), (int)(backgroundRect.getY() + newy));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        if (!enabled) setColour(Color.DARK_GRAY);
        else {
            setColour(background);
            if (mouseOver) renderer.fillRectangle(backgroundRect, foreground);
        }

        super.render(renderer);
    }

    protected boolean mousePressed(MouseEvent event) {
        if (!enabled) return false;
        return contains(event.getPosition());
    }

    protected boolean mouseReleased(MouseEvent event) {
        if (!enabled) return false;
        return contains(event.getPosition());
    }

    protected boolean mouseMoved(MouseEvent event) {
        if (!enabled) return false;
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

    public boolean isEnabled() {
        return enabled;
    }

    public GButton setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}

package core.input;

import java.awt.*;

public class MouseEvent extends Event {

    private Point position;
    private int button;

    public MouseEvent(Event.Type type, java.awt.event.MouseEvent event) {
        super(type);
        this.button = event.getButton();
        this.position = new Point(event.getLocationOnScreen().x, event.getLocationOnScreen().y);
    }

    public int getButton() {
        return button;
    }

    public Point getPosition() {
        return position;
    }
}

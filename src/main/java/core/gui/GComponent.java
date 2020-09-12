package core.gui;

import core.graphics.Frame;
import core.objects.Entity;
import java.awt.Color;

public abstract class GComponent extends Entity {

    protected ComponentAnchor componentAnchor;

    public GComponent(int x, int y, int width, int height, Color colour, ComponentAnchor componentAnchor) {
        super(x, y, width, height, colour);
        this.componentAnchor = componentAnchor;
        setAnchor();
    }

    protected void setAnchor() {
        switch (componentAnchor) {
            case CENTERX:
                x = (Frame.WIDTH/2) - (width / 2);
                break;
            case CENTERY:
                y = (Frame.HEIGHT/2) - (height / 2);
                break;
            case CENTER:
                x = (Frame.WIDTH/2) - (width / 2);
                y = (Frame.HEIGHT/2) - (height / 2);
                break;

        }
    }

    public abstract void close();

}

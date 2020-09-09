package game;

import java.awt.*;

import core.graphics.Frame;
import core.graphics.Renderer;
import core.states.State;
import core.states.StateName;

public class Start extends State {

    private Rectangle bounds;

    public Start() {
        super(StateName.START);
        bounds = new Rectangle(25, 25, Frame.WIDTH - 50, Frame.HEIGHT - 50);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Renderer renderer) {
        renderer.fillRectangle(bounds, Color.CYAN);
        renderer.renderString("SPOT", new Point(100, 100), Color.DARK_GRAY, 120);
    }

    @Override
    public void close() {
        
    }
    
}
package game;

import java.awt.*;

import core.graphics.Frame;
import core.graphics.Renderer;
import core.gui.GButton;
import core.input.Event;
import core.states.State;
import core.states.StateName;

public class Start extends State {

    private Rectangle bounds;

    private GButton startbutton;
    private GButton settingstbutton;
    private GButton exitbutton;

    public Start() {
        super(StateName.START);
        bounds = new Rectangle(25, 25, Frame.WIDTH - 50, Frame.HEIGHT - 50);
    }

    @Override
    public void init() {
        startbutton = new GButton(100, 250, 200, 70, "START", Color.LIGHT_GRAY, Color.DARK_GRAY,
                Color.BLACK, Renderer.staticFont.deriveFont((float)60));
    }

    @Override
    public void update() {
        startbutton.update();
    }

    @Override
    public void render(Renderer renderer) {
        renderer.fillRectangle(bounds, Color.CYAN);
        renderer.renderString("SPOT", new Point(100, 150), Color.DARK_GRAY, 120);

        startbutton.render(renderer);
    }

    @Override
    public void onEvent(Event event) {
        System.out.println("Event: " + event.getType().toString());
    }

    @Override
    public void close() {
        
    }
}
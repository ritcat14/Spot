package game;

import java.awt.*;

import core.graphics.Frame;
import core.graphics.Renderer;
import core.gui.ComponentAnchor;
import core.gui.GButton;
import core.gui.GLabel;
import core.input.Event;
import core.input.MouseEvent;
import core.states.State;
import core.states.StateName;

import static core.graphics.Renderer.staticFont;
import static core.gui.ComponentAnchor.CENTERX;
import static core.gui.text.TextAlignment.MIDDLE;
import static core.gui.text.TextFormat.NONE;
import static core.states.StateName.GAME;

public class Start extends State {

    private Rectangle bounds;

    private GButton startbutton;
    private GButton settingstbutton;
    private GButton exitbutton;

    private GLabel title;

    public Start() {
        super(StateName.START);
    }

    @Override
    public void init() {
        bounds = new Rectangle(25, 25, Frame.WIDTH - 50, Frame.HEIGHT - 50);
        startbutton = new GButton(0, 300, 250, 70, "START", staticFont.deriveFont((float)60),
                Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, MIDDLE, NONE, CENTERX) {
            @Override
            protected boolean mousePressed(MouseEvent event) {
                if (super.mousePressed(event)) {
                    if (event.getButton() == 1) { // left click
                        requestChange(GAME);
                    } else if (event.getButton() == 2) { // middle click

                    } else if (event.getButton() == 3) { // right click

                    }
                    return true;
                }
                return false;
            }
        };
        settingstbutton = new GButton(0, 400, 250, 70, "SETTINGS", staticFont.deriveFont((float)60),
                Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, MIDDLE, NONE, CENTERX);
        exitbutton = new GButton(0, 500, 250, 70, "EXIT", staticFont.deriveFont((float)60),
                Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK,MIDDLE, NONE, CENTERX);
        title = new GLabel(100, 150, 300, 150, CENTERX, "SPOT", staticFont.deriveFont((float)120),
                Color.DARK_GRAY, null, MIDDLE, NONE);
    }

    @Override
    public void update() {
        title.update();

        startbutton.update();
        settingstbutton.update();
        exitbutton.update();
    }

    @Override
    public void render(Renderer renderer) {
        renderer.fillRectangle(bounds, Color.CYAN);

        title.render(renderer);

        startbutton.render(renderer);
        settingstbutton.render(renderer);
        exitbutton.render(renderer);
    }

    @Override
    public void onEvent(Event event) {
        startbutton.onEvent(event);
        settingstbutton.onEvent(event);
        exitbutton.onEvent(event);
    }

    @Override
    public void close() {
        title.close();
        startbutton.close();
        settingstbutton.close();
        exitbutton.close();
    }
}
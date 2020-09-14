package game.states;

import java.awt.*;

import core.Engine;
import core.graphics.Frame;
import core.graphics.Renderer;
import core.gui.GButton;
import core.gui.GContainer;
import core.gui.GLabel;
import core.input.Event;
import core.input.MouseEvent;
import core.states.State;
import core.states.StateName;

import static core.graphics.Renderer.staticFont;
import static core.gui.ComponentAnchor.CENTERX;
import static core.gui.text.TextAlignment.MIDDLE;
import static core.gui.text.TextFormat.NONE;
import static core.states.StateName.*;

public class Start extends State {

    private Rectangle bounds;

    private GContainer gContainer;

    public Start() {
        super(StateName.START);
    }

    @Override
    public void init() {
        bounds = new Rectangle(25, 25, Frame.WIDTH - 50, Frame.HEIGHT - 50);
        gContainer = new GContainer(bounds, null,
            new GButton(0, 300, 250, 70, "START", staticFont.deriveFont((float)60),
                    MIDDLE, NONE, CENTERX) {
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
            },

            new GButton(0, 400, 250, 70, "SETTINGS", staticFont.deriveFont((float)60),
                    MIDDLE, NONE, CENTERX) {
                @Override
                protected boolean mousePressed(MouseEvent event) {
                    if (super.mousePressed(event) && event.getButton() == 1) {
                        requestChange(SETTINGS);
                        return true;
                    }
                    return false;
                }
            },

            new GButton(0, 500, 250, 70, "EXIT", staticFont.deriveFont((float)60),
                    MIDDLE, NONE, CENTERX) {
                @Override
                protected boolean mousePressed(MouseEvent event) {
                    if (super.mousePressed(event) && event.getButton() == 1) {
                        Engine.exit();
                        return true;
                    }
                    return false;
                }
            }
        );

        gContainer.addComponent(new GLabel(100, 150, 300, 150, CENTERX, "SPOT", staticFont.deriveFont((float)120),
                Color.DARK_GRAY, null, MIDDLE, NONE));
    }

    @Override
    public void update() {
        gContainer.update();
    }

    @Override
    public void render(Renderer renderer) {
        gContainer.render(renderer);
    }

    @Override
    public void onEvent(Event event) {
        gContainer.onEvent(event);
    }

    @Override
    public void close() {
        gContainer.close();
    }
}
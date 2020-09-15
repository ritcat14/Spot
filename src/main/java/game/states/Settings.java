package game.states;

import core.graphics.Frame;
import core.graphics.Renderer;
import core.gui.ComponentAnchor;
import core.gui.GButton;
import core.gui.GContainer;
import core.gui.GLabel;
import core.input.Event;
import core.input.MouseEvent;
import core.states.State;

import java.awt.*;

import static core.states.StateName.*;
import static core.graphics.Renderer.staticFont;
import static core.graphics.Frame.*;
import static core.gui.ComponentAnchor.CENTERX;
import static core.gui.text.TextAlignment.MIDDLE;
import static core.gui.text.TextFormat.NONE;

public class Settings extends State {

    private Rectangle bounds;

    private GContainer gContainer;

    public Settings() {
        super(SETTINGS);
    }

    @Override
    public void init() {
        bounds = new Rectangle(25, 25, Frame.WIDTH - 50, Frame.HEIGHT - 50);

        int stepX = WIDTH/4;
        int buttonY = HEIGHT-150;

        gContainer = new GContainer(bounds, null, 
        
            new GLabel(100, 25, 350, 150, CENTERX, "SETTINGS", staticFont.deriveFont((float)80),
                    Color.DARK_GRAY, null, MIDDLE, NONE),

                
            new GButton(stepX - 50, buttonY, 100, 50, "SAVE", staticFont.deriveFont((float)40),
                MIDDLE, NONE, ComponentAnchor.NONE) {
                    @Override
                    protected boolean mousePressed(MouseEvent event) {
                        if (super.mousePressed(event) && event.getButton() == 1) {
                            return true;
                        }
                        return false;
                    }
                }.setEnabled(false),         

            new GButton(stepX*2 - 50, buttonY, 120, 50, "RESET", staticFont.deriveFont((float)40),
                MIDDLE, NONE, ComponentAnchor.NONE) {
                    @Override
                    protected boolean mousePressed(MouseEvent event) {
                        if (super.mousePressed(event) && event.getButton() == 1) {
                            requestChange(START);
                            return true;
                        }
                        return false;
                    }
                }.setEnabled(false),

            new GButton(stepX*3 - 50, buttonY, 100, 50, "EXIT", staticFont.deriveFont((float)40),
               MIDDLE, NONE, ComponentAnchor.NONE) {
                    @Override
                    protected boolean mousePressed(MouseEvent event) {
                        if (super.mousePressed(event) && event.getButton() == 1) {
                            requestChange(START);
                            return true;
                        }
                        return false;
                    }
                }
        );
    }

    @Override
    public void onEvent(Event event) {
        gContainer.onEvent(event);
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
    public void close() {
        gContainer.close();
    }

}
package SpotJava.game.states;

import SpotJava.core.graphics.Frame;
import SpotJava.core.graphics.Renderer;
import SpotJava.core.gui.ComponentAnchor;
import SpotJava.core.gui.GButton;
import SpotJava.core.gui.GContainer;
import SpotJava.core.gui.GLabel;
import SpotJava.core.input.Event;
import SpotJava.core.input.MouseEvent;
import SpotJava.core.states.State;

import java.awt.*;

import static SpotJava.core.states.StateName.*;
import static SpotJava.core.graphics.Renderer.staticFont;
import static SpotJava.core.graphics.Frame.*;
import static SpotJava.core.gui.ComponentAnchor.CENTERX;
import static SpotJava.core.gui.text.TextAlignment.MIDDLE;
import static SpotJava.core.gui.text.TextFormat.NONE;

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
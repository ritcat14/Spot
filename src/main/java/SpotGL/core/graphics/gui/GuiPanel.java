package SpotGL.core.graphics.gui;

import SpotGL.core.graphics.Texture;
import SpotGL.core.input.InputHandler;

public class GuiPanel extends GuiComponent {

    public GuiPanel(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean onInput(InputHandler inputHandler) {
        return false;
    }
}

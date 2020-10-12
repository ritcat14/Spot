package SpotGL.core.graphics.gui;

import SpotGL.core.graphics.Texture;
import SpotGL.core.input.InputListener;
import SpotGL.core.objects.model.Entity;

public abstract class GuiComponent extends Entity implements InputListener {

    public GuiComponent(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }
}

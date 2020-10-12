package SpotGL.core.graphics.gui;

import SpotGL.core.graphics.Texture;
import SpotGL.core.input.InputHandler;
import org.joml.Vector2f;

import java.awt.*;

import static SpotGL.core.utils.FileUtils.loadTexture;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;

public abstract class GuiButton extends GuiComponent {

    private Texture mouseOverTexture;
    private Texture normalTexture;
    private String name;

    public GuiButton(float x, float y, float width, float height, String texture) {
        super(x, y, width, height, loadTexture("/images/gui/" + texture + "Buttona.png"));
        this.mouseOverTexture = loadTexture("/images/gui/" + texture + "Buttonb.png");
        this.normalTexture = this.texture;
        this.name = texture;
    }

    public abstract boolean onAction();

    @Override
    public void update() {

    }

    public boolean mouseInBounds(Vector2f mousePosition) {
        Rectangle bounds = new Rectangle((int)(position.x - (size.x/2)), (int)(position.y + (size.y/2)), (int)size.x, (int)size.y);
        return bounds.contains(new Point((int)mousePosition.x, (int)mousePosition.y));
    }

    @Override
    public boolean onInput(InputHandler inputHandler) {
        boolean result = false;
        Vector2f mousePosition = inputHandler.getMousePosition();

        if (mouseInBounds(mousePosition)) {
            this.texture = mouseOverTexture;

            if (inputHandler.mouseButtonReleased(GLFW_MOUSE_BUTTON_1)) {
                System.out.println("Button " + name + " pressed!");
                result = onAction();
            }
        }
        else {
            this.texture = normalTexture;
        }
        return result;
    }
}

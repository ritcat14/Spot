package SpotGL.game.managers;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.graphics.Shader;
import SpotGL.core.graphics.gui.GuiComponent;
import SpotGL.core.input.InputHandler;
import SpotGL.core.objects.Camera;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GuiManager extends Manager {

    private List<GuiComponent> componentList = new ArrayList<GuiComponent>();

    public GuiManager(GLFrame glFrame, Camera camera) {
        super(glFrame, new Shader("shaders/guiVertex.glsl", "shaders/guiFragment.glsl"), camera);
    }

    public void addComponent(GuiComponent component) {
        componentList.add(component);
    }

    public void removeComponent(GuiComponent component) {
        componentList.remove(component);
    }

    @Override
    public void update() {
        for (GuiComponent component : componentList) {
            component.update();
        }
    }

    @Override
    public void render() {
        shader.bind();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        shader.setUniformMatrix4f("viewMatrix", new Matrix4f().identity());
        shader.setUniformMatrix4f("projectionMatrix", glFrame.getProjectionMatrix());

        for (int i = componentList.size()-1; i >= 0; i--) {
            componentList.get(i).render(shader);
        }

        glDisable(GL_BLEND);
        shader.unbind();
    }

    @Override
    public void cleanUp() {
        for (GuiComponent component : componentList) component.cleanUp();
    }

    @Override
    public boolean onInput(InputHandler inputHandler) {
        boolean result = false;
        for (GuiComponent component : componentList) if (!result) result = component.onInput(inputHandler);
        return result;
    }
}

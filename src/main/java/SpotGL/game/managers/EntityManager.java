package SpotGL.game.managers;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.graphics.Shader;
import SpotGL.core.input.InputHandler;
import SpotGL.core.input.InputListener;
import SpotGL.core.objects.Camera;
import SpotGL.core.objects.model.Entity;
import SpotGL.game.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class EntityManager extends Manager {

    private List<Entity> entityList;
    private Player player;

    public EntityManager(GLFrame glFrame, Camera camera) {
        super(glFrame, new Shader("shaders/entityVertex.glsl", "shaders/entityFragment.glsl"), camera);
        entityList = new ArrayList<Entity>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    @Override
    public void update() {
        player.update(camera);

        for (Entity entity : entityList) {
            entity.update();
        }
    }

    @Override
    public void render() {
        shader.bind();
        player.render(shader);

        shader.setUniformMatrix4f("viewMatrix", camera.getViewMatrix());
        shader.setUniformMatrix4f("projectionMatrix", glFrame.getProjectionMatrix());
        for (Entity entity : entityList) {
            entity.render(shader);
        }
        shader.unbind();
    }

    @Override
    public void cleanUp() {
        for (Entity entity : entityList) entity.cleanUp();
        shader.cleanUp();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean onInput(InputHandler inputHandler) {
        boolean result = player.onInput(inputHandler);

        for (Entity entity : entityList) {
            if (entity instanceof InputListener) {
                if (!result) result = ((InputListener) entity).onInput(inputHandler);
            }
        }
        return result;
    }
}

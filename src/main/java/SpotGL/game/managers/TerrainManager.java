package SpotGL.game.managers;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.graphics.Shader;
import SpotGL.core.input.InputHandler;
import SpotGL.core.objects.Camera;
import SpotGL.core.objects.maps.Map;

public class TerrainManager extends Manager {

    private Map map;

    public TerrainManager(GLFrame glFrame, Map map, Camera camera) {
        super(glFrame, new Shader("shaders/terrainVertex.glsl", "shaders/terrainFragment.glsl"), camera);
        this.map = map;
    }

    @Override
    public void update() {
        map.update();
    }

    @Override
    public void render() {
        shader.bind();
        shader.setUniformMatrix4f("viewMatrix", camera.getViewMatrix());
        shader.setUniformMatrix4f("projectionMatrix", glFrame.getProjectionMatrix());

        map.render(shader);

        shader.unbind();
    }

    @Override
    public void cleanUp() {
        map.cleanUp();
        shader.cleanUp();
    }

    @Override
    public boolean onInput(InputHandler inputHandler) {
        return false;
    }
}

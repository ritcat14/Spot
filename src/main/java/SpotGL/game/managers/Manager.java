package SpotGL.game.managers;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.graphics.Shader;
import SpotGL.core.input.InputListener;
import SpotGL.core.objects.Camera;

public abstract class Manager implements InputListener {

    protected final Shader shader;
    protected final Camera camera;
    protected final GLFrame glFrame;

    public Manager(GLFrame glFrame, Shader shader, Camera camera) {
        this.glFrame = glFrame;
        this.shader = shader;
        this.camera = camera;
    }

    public abstract void update();

    public abstract void render();

    public abstract void cleanUp();

}

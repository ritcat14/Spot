package SpotGL.game.entities;

import SpotGL.core.graphics.Shader;
import SpotGL.core.input.InputHandler;
import SpotGL.core.input.InputListener;
import SpotGL.core.objects.Camera;
import SpotGL.core.objects.model.Entity;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

import static SpotGL.core.VarStore.JAVA_HEIGHT;
import static SpotGL.core.VarStore.JAVA_WIDTH;
import static SpotGL.core.utils.FileUtils.loadOBJ;
import static SpotGL.core.utils.FileUtils.loadTexture;
import static SpotGL.core.utils.MatrixUtils.updateTransformationMatrix;

public class Player extends Entity implements InputListener {

    private boolean up, down, left, right;

    public Player() {
        super((JAVA_WIDTH/2) - 30, (JAVA_HEIGHT/2) - 30, 60, 60,
                loadTexture("/images/player/playerTest.png"));
        vertexArray = loadOBJ("player.obj");
        updateTransformationMatrix(this);
    }

    public void update(Camera camera) {
        if (up) {
            camera.getPosition().y -=5f;
        }
        if (down) {
            camera.getPosition().y +=5f;
        }
        if (left) {
            camera.getPosition().x -=5f;
        }
        if (right) {
            camera.getPosition().x +=5f;
        }
        camera.update();
        update();
    }

    @Override
    public void update() {
        updateTransformationMatrix(this);
    }

    @Override
    public void render(Shader shader) {
        shader.setUniformMatrix4f("viewMatrix", new Matrix4f().identity());
        shader.setUniformMatrix4f("transformationMatrix", transformationMatrix);
        vertexArray.render(shader, texture);
    }

    @Override
    public boolean onInput(InputHandler inputHandler) {
        boolean result = false;
        if (up) {
            if (inputHandler.keyReleased(GLFW.GLFW_KEY_W)) {
                up = false;
                result = true;
            }
        } else {
            up = inputHandler.keyPressed(GLFW.GLFW_KEY_W);
            result = true;
        }

        if (down) {
            if (inputHandler.keyReleased(GLFW.GLFW_KEY_S)) {
                down = false;
                result = true;
            }
        } else {
            down = inputHandler.keyPressed(GLFW.GLFW_KEY_S);
            result = true;
        }

        if (left) {
            if (inputHandler.keyReleased(GLFW.GLFW_KEY_A)) {
                left = false;
                result = true;
            }
        } else {
            left = inputHandler.keyPressed(GLFW.GLFW_KEY_A);
            result = true;
        }

        if (right) {
            if (inputHandler.keyReleased(GLFW.GLFW_KEY_D)) {
                right = false;
                result = true;
            }
        } else {
            right = inputHandler.keyPressed(GLFW.GLFW_KEY_D);
            result = true;
        }

        return result;
    }
}

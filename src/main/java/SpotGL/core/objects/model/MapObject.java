package SpotGL.core.objects.model;

import SpotGL.core.graphics.Shader;
import SpotGL.core.graphics.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static SpotGL.core.VarStore.*;
import static SpotGL.core.utils.FileUtils.loadPlane;
import static SpotGL.core.utils.MatrixUtils.updateTransformationMatrix;
import static org.lwjgl.opengl.GL11.*;

public abstract class MapObject extends Entity {

    private Vector3f origin;

    public MapObject(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
        this.vertexArray = loadPlane();
        updateTransformationMatrix(this);
        this.origin = new Vector3f(position);
    }

    public void render(Shader shader, Vector2f centerPosition) {
        if (position.x < 0 || position.y < 0 || position.x >= JAVA_WIDTH || position.y >= JAVA_HEIGHT) return;
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        updateTransformationMatrix(this);
        shader.setUniform1f("renderDistance", RENDER_DISTANCE);
        shader.setUniform2f("centerPos", centerPosition);
        shader.setUniformMatrix4f("transformationMatrix", transformationMatrix);
        vertexArray.render(shader, texture);
        glDisable(GL_BLEND);
    }

}

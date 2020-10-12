package SpotGL.core.objects.model;

import SpotGL.core.graphics.Shader;
import SpotGL.core.graphics.Texture;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static SpotGL.core.utils.MatrixUtils.updateTransformationMatrix;

public abstract class Entity {

    protected VertexArray vertexArray;
    protected Texture texture;

    protected Vector3f position;
    protected Vector3f size;
    protected Vector3f rotation;

    protected Matrix4f transformationMatrix = new Matrix4f();

    public Entity(float x, float y, float width, float height, Texture texture) {
        this.vertexArray = new VertexArray(); // Creates a blank rectangle
        this.texture = texture;

        this.position = new Vector3f(x + (width/2), y - (height/2), -1f);
        this.size = new Vector3f(width, height, 1f);
        this.rotation = new Vector3f();

        updateTransformationMatrix(this);
    }

    public abstract void update();

    public void render(Shader shader) {
        updateTransformationMatrix(this);
        shader.setUniformMatrix4f("transformationMatrix", transformationMatrix);
        vertexArray.render(shader, texture);
    }

    public void cleanUp() {
        vertexArray.cleanUp();
        texture.cleanUp();
    }

    public Matrix4f getTransformationMatrix() {
        return transformationMatrix;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getSize() {
        return size;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setTransformationMatrix(Matrix4f transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public void setSize(float width, float height) {
        this.size.x = width;
        this.size.y = height;
    }

    public void setRotation(float xRot, float yRot, float rotZ) {
        this.rotation.x = xRot;
        this.rotation.y = yRot;
        this.rotation.z = rotZ;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setSize(Vector3f size) {
        this.size = size;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}

package SpotGL.core.objects;

import SpotGL.core.utils.MatrixUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static SpotGL.core.VarStore.JAVA_HEIGHT;
import static SpotGL.core.VarStore.JAVA_WIDTH;
import static SpotGL.core.utils.MathUtils.pixelToGL;
import static SpotGL.core.utils.MatrixUtils.updateViewMatrix;

public class Camera {

    private Vector3f position = new Vector3f(JAVA_WIDTH/2, JAVA_HEIGHT/2, 0f);
    private float pitch = -0.5f;
    private float yaw;
    private float roll;

    private Matrix4f viewMatrix = new Matrix4f();

    public Camera() {
        MatrixUtils.camera = this;
        updateViewMatrix();
    }

    public void update() {
        updateViewMatrix();
    }

    public Vector3f getPosition() {
        return position;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public Vector3f getInversePosition() {
        Vector3f glPos = pixelToGL(position);
        return new Vector3f(-glPos.x, -glPos.y, -glPos.z);
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setViewMatrix(Matrix4f viewMatrix) {
        this.viewMatrix = viewMatrix;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
}

package SpotGL.core.utils;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.objects.Camera;
import SpotGL.core.objects.model.Entity;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static SpotGL.core.VarStore.*;
import static SpotGL.core.utils.MathUtils.pixelSizeToGL;
import static SpotGL.core.utils.MathUtils.pixelToGL;

public abstract class MatrixUtils {

    public static Camera camera;
    public static GLFrame glFrame;

    private MatrixUtils(){}

    public static void updateProjectionMatrix() {
        Matrix4f projectionMatrix = glFrame.getProjectionMatrix();
        float aspectRatio = JAVA_WIDTH/JAVA_HEIGHT;
        projectionMatrix.identity();
        projectionMatrix.perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
        glFrame.setProjectionMatrix(projectionMatrix);
    }

    public static Matrix4f updateTransformationMatrix(Entity entity) {
        Vector3f glPos = pixelToGL(entity.getPosition());
        Vector3f glScale = pixelSizeToGL(entity.getSize());
        Matrix4f transformationMatrix = entity.getTransformationMatrix();
        transformationMatrix.identity().translate(glPos).
                    rotateX((float)Math.toRadians(entity.getRotation().x)).
                    rotateY((float)Math.toRadians(entity.getRotation().y)).
                    rotateZ((float)Math.toRadians(entity.getRotation().z)).
                    scale(glScale);
        entity.setTransformationMatrix(transformationMatrix);
        return transformationMatrix;
    }

    public static Matrix4f updateViewMatrix() {
        Matrix4f viewMatrix = camera.getViewMatrix();
        viewMatrix.identity().rotateX(camera.getPitch()).
                rotateY(camera.getYaw()).
                rotateZ(camera.getRoll()).
                translate(camera.getInversePosition());
        camera.setViewMatrix(viewMatrix);
        return viewMatrix;
    }

}

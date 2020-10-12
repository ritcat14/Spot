package SpotGL.core.utils;

import org.joml.Vector2f;
import org.joml.Vector3f;

import static SpotGL.core.VarStore.*;

public class MathUtils {

    private MathUtils() {}


    public static float calculateDistance1f(float x1, float y1, float x2, float y2) {
        return (float)Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public static Vector2f calculateDistance2f(float x1, float y1, float x2, float y2) {
        Vector2f result = new Vector2f();
        result.x = x2 - x1;
        result.y = y2 - y1;
        return result;
    }

    public static float roundFloat(float f) {
        return (float)Math.round(f * 12.0f) / 12.0f;
    }

    public static float roundFloatToHalf(float f) {
        return (float)Math.round(f * 2.0f) / 2.0f;
    }

    public static Vector3f pixelToGL(Vector3f pixelVector) {
        Vector3f glVector = new Vector3f();
        glVector.x = glX + ((pixelVector.x / JAVA_WIDTH) * glW);
        glVector.y = -(glY + ((pixelVector.y / JAVA_HEIGHT) * glH));
        glVector.z = pixelVector.z;
        return glVector;
    }

    public static Vector3f pixelSizeToGL(Vector3f pixelVector) {
        Vector3f glVector = new Vector3f();
        glVector.x = (pixelVector.x / JAVA_WIDTH);
        glVector.y = (pixelVector.y / JAVA_HEIGHT);
        glVector.z = pixelVector.z;
        return glVector;
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public static double getTOA(double opposite, double adjacent) {
        return Math.toDegrees(Math.atan2(opposite, adjacent));
    }

    public static double getSOH(double opposite, double hypotenuse) {
        return Math.toDegrees(Math.asin(opposite/hypotenuse));
    }

    public static double getCAH(double adjacent, double hypotenuse) {
        return Math.toDegrees(Math.acos(adjacent/hypotenuse));
    }

    public static double missingAngleTriangle(double width, double height) {
        double angle1 = 90;
        double angle2 = getTOA(width, height);
        return 180 - angle1 - angle2;
    }

}

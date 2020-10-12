package SpotJava.core.util;

public class Maths {

    private Maths() {}

    public static float calculateDistance(double x1, double y1, double x2, double y2) {
        return (float)Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
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
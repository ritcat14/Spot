package SpotGL.core;

import java.awt.*;

public abstract class VarStore {

    private VarStore() {}

    public static final boolean VSYNC = true;
    public static final float FPS_CAP = 120.0f;
    public static final float UPS_CAP = 60.0f;
    public static final float JAVA_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final float JAVA_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final float glX = -(2.0f * (9.0f / 16.0f));
    public static final float glY = -0.5769f;
    public static final float glW = 2.0f * (2.0f * (9.0f / 16.0f));
    public static final float glH = 0.5769f * 2f;

    public static final float FOV = (float)Math.toRadians(60.0f);
    public static final float NEAR_PLANE = 0.1f;
    public static final float FAR_PLANE = 1000.f;

    public static int VERTEX_ATTRIB = 0;
    public static int TEXTURE_ATTRIB = 1;

    public static final float RENDER_DISTANCE = 0.8f;

}

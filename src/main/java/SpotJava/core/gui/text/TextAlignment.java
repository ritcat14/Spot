package SpotJava.core.gui.text;

public enum TextAlignment
{
    TOP_LEFT,
    TOP,
    TOP_RIGHT,
    MIDDLE_LEFT,
    MIDDLE,
    MIDDLE_RIGHT,
    BOTTOM_LEFT,
    BOTTOM,
    BOTTOM_RIGHT;

    public boolean isMiddle() {
        return (this.toString().contains("MIDDLE"));
    }

    public boolean isBottom() {
        return (this.toString().contains("BOTTOM"));
    }

    public boolean isCenter() {
        return (this.toString().contains("CENTER"));
    }

    public boolean isRight() {
        return (this.toString().contains("RIGHT"));
    }
};

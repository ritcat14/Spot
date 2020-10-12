package SpotJava.core.input;

public class Event {

    private Type type;

    private boolean handled = false;

    public enum Type {
        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_MOVED,
        MOUSE_DRAGGED,
        KEY_PRESSED,
        KEY_RELEASED
    }

    protected Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }
}

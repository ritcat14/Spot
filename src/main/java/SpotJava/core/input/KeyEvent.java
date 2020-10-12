package SpotJava.core.input;

public class KeyEvent extends Event {

    private int key;

    public KeyEvent(Type type, java.awt.event.KeyEvent event) {
        super(type);
        this.key = event.getKeyCode();
    }

    public int getKey() {
        return key;
    }
}

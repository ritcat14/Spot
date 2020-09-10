package core.input;

public class EventDispatcher {

    private Event event;

    public EventDispatcher(Event event) {
        this.event = event;
    }

    public void dispatch(Event.Type type, EventHandler handler) {
        if (event.isHandled()) return;
        if (event.getType() == type) event.setHandled(handler.onEvent(event));
    }

}

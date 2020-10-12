package SpotJava.core.states;

import SpotJava.core.graphics.Renderer;
import SpotJava.core.input.EventListener;

public abstract class State implements EventListener {

    private StateName stateName;

    private StateName requestedState;
    private boolean requestedChange = false;

    public State(StateName stateName) {
        this.stateName = stateName;
    }

    public boolean verify(StateName stateName) {
        return this.stateName == stateName;
    }

    public abstract void init();

    public abstract void update();
    
    public abstract void render(Renderer renderer);

    public abstract void close();

    public void requestChange(StateName state) {
        requestedState = state;
        requestedChange = true;
    }

    public boolean requestedChange() {
        return requestedChange;
    }

    public StateName getRequestedState() {
        return requestedState;
    }

    public void requestComplete() {
        requestedState = null;
        requestedChange = false;
    }

    public StateName getStateName() {
        return stateName;
    }
}

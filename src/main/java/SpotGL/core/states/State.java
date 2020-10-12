package SpotGL.core.states;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.input.InputListener;

public abstract class State implements InputListener {

    private final StateName stateName;

    private StateName requestedState;
    private boolean requestedChange = false;

    public State(StateName stateName) {
        this.stateName = stateName;
    }

    public boolean verify(StateName stateName) {
        return this.stateName == stateName;
    }

    public abstract void init(GLFrame glFrame);

    public abstract void update();

    public abstract void render();

    public abstract void cleanUp();

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

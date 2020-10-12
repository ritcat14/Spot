package SpotGL.core.states;

import SpotGL.core.graphics.GLFrame;
import SpotGL.core.input.InputHandler;
import SpotGL.core.input.InputListener;

import java.util.ArrayList;
import java.util.List;

public class StateManager implements InputListener {

    private GLFrame glFrame;
    private List<State> states;
    private List<State> statesToAdd;

    private State currentState;
    private State requestedState;

    private boolean rendering, updating, input;

    public StateManager(GLFrame glFrame) {
        states = new ArrayList<>();
        statesToAdd = new ArrayList<>();
        this.glFrame = glFrame;
    }

    public void update() {
        if (!rendering && !input) {
            if (statesToAdd.size() > 0) {
                if (currentState == null) {
                    currentState = statesToAdd.get(0);
                    currentState.init(glFrame);
                }
                states.addAll(statesToAdd);
                statesToAdd.clear();
            }
            if (requestedState != null) {
                if (currentState != null && currentState.requestedChange()) currentState.requestComplete();

                if (currentState != null) currentState.cleanUp();
                currentState = requestedState;
                currentState.init(glFrame);
                requestedState = null;
            }
        }
        if (currentState != null) {
            updating = true;
            currentState.update();
            if (currentState.requestedChange()) setState(currentState.getRequestedState());
            updating = false;
        }
    }

    public void addState(State state) {
        statesToAdd.add(state);
    }

    public void render() {
        if (currentState != null) {
            rendering = true;
            currentState.render();
            rendering = false;
        }
    }

    public void setState(StateName stateName) {
        if (currentState != null) {
            if (currentState.verify(stateName)) {
                // State is already set
            } else {
                State newState = getState(stateName);
                if (newState != null) {
                    if (updating || rendering || input) requestedState = newState;
                } else {
                    // State does not exist/was not found
                }
            }
        }
    }

    private State getState(StateName stateName) {
        for (State state : states) {
            if (state.verify(stateName)) return state;
        }
        // State name was not found in list of states, therefore requested state does not exist
        return null;
    }

    @Override
    public boolean onInput(InputHandler inputHandler) {
        boolean result = false;
        if (currentState != null) {
            input = true;
            result = currentState.onInput(inputHandler);
            input = false;
        }
        return result;
    }

    public void cleanUp() {
        currentState.cleanUp();
        states.clear();
        statesToAdd.clear();
    }
}
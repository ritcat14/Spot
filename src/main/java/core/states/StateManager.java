package core.states;

import java.util.ArrayList;
import java.util.List;

import core.graphics.Renderer;
import core.input.Event;
import core.input.EventListener;

public class StateManager implements EventListener {
    
    private List<State> states;
    private List<State> statesToAdd;

    private State currentState;
    private State requestedState;

    private boolean rendering, updating, input;

    public StateManager() {
        states = new ArrayList<>();
        statesToAdd = new ArrayList<>();
    }

    public void update() {
        if (!rendering && !input) {
            if (statesToAdd.size() > 0) {
                if (currentState == null) {
                    currentState = statesToAdd.get(0);
                    currentState.init();
                }
                states.addAll(statesToAdd);
                statesToAdd.clear();
            }
            if (requestedState != null) {
                if (currentState != null && currentState.requestedChange()) currentState.requestComplete();

                currentState = requestedState;
                currentState.init();
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

    public void render(Renderer renderer) {
        if (currentState != null) {
            rendering = true;
            currentState.render(renderer);
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
    public void onEvent(Event event) {
        if (currentState != null) {
            input = true;
            currentState.onEvent(event);
            input = false;
        }
    }

    public void close() {
        for (State state : states) {
            state.close();
        }
        states.clear();
        statesToAdd.clear();
    }
}
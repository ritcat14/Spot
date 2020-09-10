package core.states;

import java.util.ArrayList;
import java.util.List;

import core.graphics.Renderer;

public class StateManager {
    
    private List<State> states;
    private List<State> statesToAdd;

    private State currentState;
    private State requestedState;

    private boolean rendering, updating;

    public StateManager() {
        states = new ArrayList<>();
        statesToAdd = new ArrayList<>();
    }

    public void update() {
        if (!rendering) {
            if (statesToAdd.size() > 0) {
                if (currentState == null) {
                    currentState = statesToAdd.get(0);
                    currentState.init();
                }
                states.addAll(statesToAdd);
                statesToAdd.clear();
            }
            if (requestedState != null) {
                currentState = requestedState;
                currentState.init();
                requestedState = null;
            }
        }
        if (currentState != null) {
            updating = true;
            currentState.update();
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
                    if (updating || rendering) requestedState = newState;
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

    public void close() {
        for (State state : states) {
            state.close();
        }
        states.clear();
        statesToAdd.clear();
    }

}
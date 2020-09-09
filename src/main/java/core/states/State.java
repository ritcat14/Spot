package core.states;

import core.graphics.Renderer;

public abstract class State {

    private StateName stateName;

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

}

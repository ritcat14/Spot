package game;

import core.graphics.Renderer;
import core.input.Event;
import core.states.State;
import game.entities.Player;
import game.maps.Map;

import static core.states.StateName.GAME;

public class Game extends State {

    private Map map;

    public Game() {
        super(GAME);
    }

    @Override
    public void init() {
        map = new Map(new Player());
    }

    @Override
    public void update() {
        map.update();
    }

    @Override
    public void render(Renderer renderer) {
        map.render(renderer);
    }

    @Override
    public void close() {

    }

    @Override
    public void onEvent(Event event) {
        map.onEvent(event);
    }
}

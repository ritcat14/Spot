package SpotJava.game.states;

import SpotJava.core.graphics.Renderer;
import SpotJava.core.input.Event;
import SpotJava.core.objects.Light;
import SpotJava.core.states.State;
import SpotJava.game.entities.Player;
import SpotJava.game.maps.StartMap;
import SpotJava.core.maps.Map;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import static SpotJava.core.states.StateName.GAME;

public class Game extends State {

    private Map map;

    public Game() {
        super(GAME);
    }

    @Override
    public void init() {
        map = new StartMap(new Player());
    }

    @Override
    public void update() {
        map.update();
    }

    @Override
    public void render(Renderer renderer) {
        map.render(renderer);

        /*Graphics2D g2 = renderer.getGameCanvas().getGraphics();
        double width = 300;
        double height = 400;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(0.0, 8.0);
        path.curveTo(0.0, 0.0, 8.0, 0.0, 8.0, 0.0);
        path.lineTo(width - 8.0, 0.0);
        path.curveTo(width, 0.0, width, 8.0, width, 8.0);
        path.lineTo(width, height - 8.0);
        path.curveTo(width, height, width - 8.0, height, width - 8.0, height);
        path.lineTo(8.0, height);
        path.curveTo(0.0, height, 0.0, height - 8.0, 0, height - 8.0);
        path.closePath();
        g2.setColor(Color.RED);
        g2.fill(path);*/
    }

    @Override
    public void close() {

    }

    @Override
    public void onEvent(Event event) {
        if (map != null) map.onEvent(event);
    }
}

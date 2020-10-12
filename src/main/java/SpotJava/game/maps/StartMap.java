package SpotJava.game.maps;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import SpotJava.core.maps.Map;
import SpotJava.core.maps.Tile;
import SpotJava.core.objects.Light;
import SpotJava.core.util.Images;
import SpotJava.game.entities.Player;

import static SpotJava.core.graphics.Frame.HEIGHT;
import static SpotJava.core.graphics.Frame.WIDTH;

public class StartMap extends Map {

    public StartMap(Player player) {
        super(player);
        List<Tile> availableTiles = new ArrayList<Tile>();
        availableTiles.add(new Tile(Images.getImage("images/tiles/grass.png")));
        loadMap("maps/map1.txt", availableTiles);

        addLight(new Light(WIDTH/2, HEIGHT/2, 250, new Color(1f, 1f, 0.0f, 0.0f)));
    }
    
}
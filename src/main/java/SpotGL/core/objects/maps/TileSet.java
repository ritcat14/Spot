package SpotGL.core.objects.maps;

import SpotGL.core.graphics.Texture;
import SpotGL.game.entities.Tile;

import java.util.HashMap;
import java.util.Map;

import static SpotGL.core.utils.FileUtils.loadTexture;

public class TileSet {

    private int width = 40;
    private int height = 40;

    private Map<Integer, Tile> tiles = new HashMap<>();

    public TileSet() {
        Texture tile1 = loadTexture("/images/tiles/background.png");
        Texture tile2 = loadTexture("/images/tiles/grass.png");
        tiles.put(0, new Tile(40, 40, tile2));
        tiles.put(1, new Tile(40, 40, tile2));
    }

    public Tile getTile(int tileID) {
        Tile tile = tiles.get(tileID);
        if (tile != null) {
            return tile;
        }
        System.out.println("Tile for id: " + tileID + " does not exist in TileSet!");
        return null;
    }

    public void cleanUp() {
        for (Map.Entry<Integer, Tile> entry : tiles.entrySet()) {
            entry.getValue().cleanUp();
        }
        tiles.clear();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

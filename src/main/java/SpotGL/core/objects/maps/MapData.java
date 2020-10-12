package SpotGL.core.objects.maps;

import SpotGL.core.graphics.Shader;
import org.joml.Vector2f;

import java.util.List;

public class MapData {

    private final List<TileLayer> tileLayers;
    private final List<ObjectLayer> objectLayers;
    private final TileSet tileSet;
    private final int mapWidth, mapHeight;

    public MapData(List<TileLayer> tileLayers, List<ObjectLayer> objectLayers, TileSet tileSet) {
        this.tileLayers = tileLayers;
        this.objectLayers = objectLayers;
        this.tileSet = tileSet;
        int wTotal = 0, hTotal = 0;
        for (TileLayer tileLayer : tileLayers) {
            wTotal += tileLayer.getWidth();
            hTotal += tileLayer.getHeight();
        }
        this.mapWidth = wTotal;
        this.mapHeight = hTotal;
    }

    public void update() {
        for (ObjectLayer objectLayer : objectLayers) {
            objectLayer.update();
        }
    }

    public void renderObjectLayers(Shader shader, Vector2f centerPos) {
        for (ObjectLayer objectLayer : objectLayers) {
            objectLayer.render(shader, centerPos);
        }
    }

    public void renderTileLayers(Shader shader, Vector2f centerPosition) {
        for (TileLayer tileLayer : tileLayers) {
            tileLayer.render(shader, tileSet, centerPosition);
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void cleanUp() {
        tileSet.cleanUp();
    }
}

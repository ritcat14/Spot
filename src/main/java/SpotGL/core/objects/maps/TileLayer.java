package SpotGL.core.objects.maps;

import SpotGL.core.graphics.Shader;
import org.joml.Vector2f;

import java.util.List;

public class TileLayer {

    private final int id;
    private final String name;
    private final int width;
    private final int height;
    private final List<Chunk> layerData;

    public TileLayer(int id, String name, int width, int height, List<Chunk> layerData) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.layerData = layerData;
    }

    @Override
    public String toString() {
        return "Layer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", layerData=" + layerData +
                '}';
    }

    public void render(Shader shader, TileSet tileSet, Vector2f centerPosition) {
        for (Chunk chunk : layerData) {
            chunk.render(shader, tileSet, centerPosition);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

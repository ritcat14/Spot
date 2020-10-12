package SpotGL.core.objects.maps;

import SpotGL.core.graphics.Shader;
import SpotGL.game.entities.Tile;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Arrays;

import static SpotGL.core.VarStore.RENDER_DISTANCE;
import static SpotGL.core.utils.MathUtils.*;

public class Chunk {

    private final int x, y, width, height, ID;
    private final int[][] data;

    public Chunk(int id, int x, int y, int width, int height, int[][] data) {
        this.ID = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", ID=" + ID +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public void render(Shader shader, TileSet tileSet, Vector2f centerPosition) {
        for (int y1 = 0; y1 < height; y1++) {
            for (int x1 = 0; x1 < width; x1++) {
                int tileID = data[y1][x1];
                float tileX = ((x + x1) * tileSet.getWidth());
                float tileY = ((y + y1) * tileSet.getHeight());
                Tile tile = tileSet.getTile(tileID);
                if (tile != null) tile.render(shader, tileX, tileY, centerPosition);
            }
        }
    }

}

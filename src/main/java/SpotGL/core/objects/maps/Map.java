package SpotGL.core.objects.maps;

import SpotGL.core.graphics.Shader;
import SpotGL.core.objects.Camera;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static SpotGL.core.utils.FileUtils.readMap;
import static SpotGL.core.utils.MathUtils.pixelToGL;

public abstract class Map {

    protected MapData mapData;
    protected Camera camera;
    protected Vector2f centerPosition = new Vector2f();
    private Vector2f centerPositionGL = new Vector2f();

    public Map(Camera camera, String fileName) {
        this.camera = camera;
        try {
            mapData = readMap(fileName);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        centerPosition.x = camera.getPosition().x;
        centerPosition.y = camera.getPosition().y;

        Vector3f cGL = pixelToGL(new Vector3f(centerPosition.x, centerPosition.y, -1f));
        centerPositionGL = new Vector2f(cGL.x, cGL.y);
    }

    public void render(Shader shader) {
        mapData.renderObjectLayers(shader, centerPositionGL);
        mapData.renderTileLayers(shader, centerPositionGL);
    }

    public void cleanUp() {
        mapData.cleanUp();
    }

}

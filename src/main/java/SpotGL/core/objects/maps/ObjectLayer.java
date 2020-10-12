package SpotGL.core.objects.maps;

import SpotGL.core.graphics.Shader;
import SpotGL.core.objects.model.MapObject;
import org.joml.Vector2f;

import java.util.List;

public class ObjectLayer {

    private List<MapObject> objectList;

    public ObjectLayer(List<MapObject> objectList) {
        this.objectList = objectList;
    }

    public void update() {
        for (MapObject entity : objectList) entity.update();
    }

    public void render(Shader shader, Vector2f centerPos) {
        for (MapObject entity : objectList) entity.render(shader, centerPos);
    }

    public void cleanUp() {
        for (MapObject entity : objectList) entity.cleanUp();
        objectList.clear();
    }

}

package core.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.*;

import core.graphics.Renderer;
import core.input.EventListener;
import core.input.Event;

import static core.gui.ComponentAnchor.*;

/*
    Container for handling multiple GComponents
*/
public class GContainer extends GComponent {

    private List<GComponent> gComponents = new ArrayList<>();
    private List<GComponent> gComponentsToAdd = new ArrayList<>();
    private List<GComponent> gComponentsToRemove = new ArrayList<>();

    private boolean updating, rendering, input;

    public GContainer(Rectangle bounds, Color background, GComponent... components) {
        super((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight(), background, NONE);

        for (GComponent gComponent : components) {
            addComponent(gComponent);
        }
    }

    private void adjustComponent(GComponent gComponent) {
        gComponent.adjustLocation((int)getX(), (int)getY());
    }

    public void addComponent(GComponent gComponent) {
        if (!gComponents.contains(gComponent)) {
            adjustComponent(gComponent);
            gComponentsToAdd.add(gComponent);
        }
    }

    public void removeComponent(GComponent gComponent) {
        if (gComponents.contains(gComponent)) gComponentsToRemove.add(gComponent);
    }

    public void update() {
        updating = true;
        if (!rendering && !input) {
            if (gComponentsToRemove.size() > 0) {
                gComponents.removeAll(gComponentsToRemove);
                gComponentsToRemove.clear();
            }
            if (gComponentsToAdd.size() > 0) {
                gComponents.addAll(gComponentsToAdd);
                gComponentsToAdd.clear();
            }
        }
        for (GComponent gComponent : gComponents) gComponent.update();
        updating = false;
    }

    public void render(Renderer renderer) {
        if (background != null) renderer.fillRectangle(this, background);
        rendering = true;

        for (GComponent gComponent : gComponents) {
            gComponent.render(renderer);
        }
        rendering = false;
    }

    public void onEvent(Event event) {
        while (updating); // Hang event until we have finished updating to get up to date component list

        input = true;
        for (GComponent gComponent : gComponents) {
            if (gComponent instanceof EventListener) ((EventListener)gComponent).onEvent(event);
        }
        input = false;
    }

    public void close() {
        for (GComponent gComponent : gComponentsToAdd) {
            gComponent.close();
        }
        for (GComponent gComponent : gComponents) {
            gComponent.close();
        }
        gComponents.clear();
        gComponentsToAdd.clear();
        gComponentsToRemove.clear();    }
    
}
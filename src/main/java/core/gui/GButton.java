package core.gui;

import core.graphics.Renderer;

import java.awt.*;

public class GButton extends GComponent {

    private String text = "";
    private Color background;
    private Color foreground;
    private Color fontColour;
    private Font font;

    public GButton(int x, int y, int width, int height, String text, Color background, Color foreground, Color fontColour,
                   Font font) {
        super(x, y, width, height);
        this.text = text;
        this.background = background;
        this.foreground = foreground;
        this.font = font;
        this.fontColour = fontColour;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.fillRectangle(this, background);
        renderer.renderString(text, new Point(x, y + (3 * (height / 4))), fontColour, font);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public String getText() {
        return text;
    }
}

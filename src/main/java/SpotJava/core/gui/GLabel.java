package SpotJava.core.gui;

import SpotJava.core.graphics.Renderer;
import SpotJava.core.gui.text.TextAlignment;

import java.awt.*;

public class GLabel extends GComponent {

    protected String text;
    protected Font font;
    protected Color textColor;
    protected TextAlignment textAlignment;
    protected int textFormat;

    public GLabel(int x, int y, int width, int height, ComponentAnchor componentAnchor,
                  String text, Font font, Color textColour, Color background, TextAlignment textAlignment,
                  int textFormat) {
        super(x, y, width, height, background, componentAnchor);
        this.text = text;
        this.font = font;
        this.textColor = textColour;
        this.textAlignment = textAlignment;
        this.textFormat = textFormat;
    }

    @Override
    public void update() {}

    @Override
    public void render(Renderer renderer) {
        if (background != null) renderer.fillRectangle(this, background);

        renderer.renderString(text, font, textColor, this, textAlignment, textFormat);
    }

    @Override
    public void close() {

    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
    }

    public void setTextFormat(int textFormat) {
        this.textFormat = textFormat;
    }
}

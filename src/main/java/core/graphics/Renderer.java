package core.graphics;

import core.gui.text.TextAlignment;
import core.gui.text.TextFormat;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class Renderer {

    public static final Color staticColour = Color.BLACK;
    public static Font staticFont = new Font("Calibri", Font.BOLD, 25);

    private GameCanvas gameCanvas;

    public Renderer(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }

    /*
    *   Image Rendering
    */

    public void renderImage(BufferedImage image) {
        renderImage(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
    }

    public void renderImage(BufferedImage image, Point point) {
        renderImage(image, new Rectangle((int)point.getX(), (int)point.getY(), 
        image.getWidth(), image.getHeight()));
    }

    public void renderImage(BufferedImage image, Rectangle rectangle) {
        Graphics2D graphics = gameCanvas.getGraphics();
        graphics.drawImage(image, (int)rectangle.getX(), (int)rectangle.getY(),
        (int)rectangle.getWidth(), (int)rectangle.getHeight(), null);
    }


    public void drawBlended(BufferedImage image1, BufferedImage image2, Point point, float alpha) {
        Graphics2D graphics = gameCanvas.getGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics.drawImage(image1, (int)point.getX(), (int)point.getY(), null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - alpha));
        graphics.drawImage(image2, (int)point.getX(), (int)point.getY(), null);

        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

    }

    /*
    *   Rectangle Rendering
    */

    public void drawRectangle(Rectangle rectangle) {
        drawRectangle(rectangle, staticColour);
    }

    public void drawRectangle(Rectangle rectangle, Color colour) {
        Graphics2D graphics = gameCanvas.getGraphics();
        graphics.setColor(colour);
        graphics.drawRect(
            (int)rectangle.getX(), (int)rectangle.getY(), 
            (int)rectangle.getWidth(), (int)rectangle.getHeight());
    }

    public void fillRectangle(Rectangle rectangle) {
        fillRectangle(rectangle, staticColour);
    }

    public void fillRectangle(Rectangle rectangle, Color colour) {
        Graphics2D graphics = gameCanvas.getGraphics();
        graphics.setColor(colour);
        graphics.fillRect((int)rectangle.getX(), (int)rectangle.getY(),
        (int)rectangle.getWidth(), (int)rectangle.getHeight());
    }

    /*
    *   String Rendering
    */

    public Rectangle renderString(String text, Font font, Color color, Rectangle bounds, TextAlignment align, int format) {
        Graphics2D graphics = gameCanvas.getGraphics();
        if (text.length() == 0)
            return new Rectangle(bounds.x, bounds.y, 0, 0);

        AttributedString attributedString = new AttributedString(text);
        attributedString.addAttribute(TextAttribute.FOREGROUND, color);
        attributedString.addAttribute(TextAttribute.FONT, font);

        AttributedCharacterIterator attributedCharIterator = attributedString.getIterator();

        FontRenderContext fontContext = new FontRenderContext(null, !TextFormat.isEnabled(format, TextFormat.NO_ANTI_ALIASING), false);
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(attributedCharIterator, fontContext);

        Point targetLocation = new Point(bounds.x, bounds.y);
        int nextOffset = 0;

        if (align.isMiddle() || align.isBottom())
        {
            if (align.isMiddle())
                targetLocation.y = bounds.y + (bounds.height / 2);
            if (align.isBottom())
                targetLocation.y = bounds.y + bounds.height;

            while (lineMeasurer.getPosition() < text.length())
            {
                nextOffset = lineMeasurer.nextOffset(bounds.width);
                nextOffset = nextTextIndex(nextOffset, lineMeasurer.getPosition(), text);

                TextLayout textLayout = lineMeasurer.nextLayout(bounds.width, nextOffset, false);

                if (align.isMiddle())
                    targetLocation.y -= (textLayout.getAscent() + textLayout.getLeading() + textLayout.getDescent()) / 2;
                if (align.isBottom())
                    targetLocation.y -= (textLayout.getAscent() + textLayout.getLeading() + textLayout.getDescent());
            }

            lineMeasurer.setPosition(0);
        }

        if (align.isRight() || align.isCenter())
            targetLocation.x = bounds.x + bounds.width;
        Rectangle consumedBounds = new Rectangle(targetLocation.x, targetLocation.y, 0, 0);

        while (lineMeasurer.getPosition() < text.length())
        {
            nextOffset = lineMeasurer.nextOffset(bounds.width);
            nextOffset = nextTextIndex(nextOffset, lineMeasurer.getPosition(), text);

            TextLayout textLayout = lineMeasurer.nextLayout(bounds.width, nextOffset, false);
            Rectangle2D textBounds = textLayout.getBounds();

            targetLocation.y += textLayout.getAscent();
            consumedBounds.width = Math.max(consumedBounds.width, (int)textBounds.getWidth());

            switch (align)
            {
                case TOP_LEFT:
                case MIDDLE_LEFT:
                case BOTTOM_LEFT:
                    textLayout.draw(graphics, targetLocation.x, targetLocation.y);
                    break;

                case TOP:
                case MIDDLE:
                case BOTTOM:
                    targetLocation.x = bounds.x + (bounds.width / 2) - (int)(textBounds.getWidth() / 2);
                    consumedBounds.x = Math.min(consumedBounds.x, targetLocation.x);
                    textLayout.draw(graphics, targetLocation.x, targetLocation.y);
                    break;

                case TOP_RIGHT:
                case MIDDLE_RIGHT:
                case BOTTOM_RIGHT:
                    targetLocation.x = bounds.x + bounds.width - (int)textBounds.getWidth();
                    textLayout.draw(graphics, targetLocation.x, targetLocation.y);
                    consumedBounds.x = Math.min(consumedBounds.x, targetLocation.x);
                    break;
            }

            targetLocation.y += textLayout.getLeading() + textLayout.getDescent();
        }
        consumedBounds.height = targetLocation.y - consumedBounds.y;

        return consumedBounds;
    }

    private static int nextTextIndex(int nextOffset, int measurerPosition, String text)
    {
        for (int i = measurerPosition + 1; i < nextOffset; ++i)
        {
            if (text.charAt(i) == '\n')
                return i;
        }

        return nextOffset;
    }

    public GameCanvas getGameCanvas() {
        return gameCanvas;
    }
}

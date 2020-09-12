package core.files;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import core.states.State;

public class Images {
    
    public static BufferedImage getImage(String fileName) {
        BufferedImage loadedImage = null;
        try {
            loadedImage = ImageIO.read(Objects.requireNonNull(State.class.getClassLoader().getResourceAsStream(fileName)));
        } catch (NullPointerException e) {
            System.out.println("Failed to find file: " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedImage;
    }

    public static BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
        return dest;
    }

    public static BufferedImage[][] loadSprites(String fileName, int amountX, int amountY) {
        BufferedImage[][] images = new BufferedImage[amountY][amountX];

        BufferedImage mainImage = getImage(fileName);

        int width = mainImage.getWidth() / amountX;
        int height = mainImage.getHeight() / amountY;

        for (int y = 0; y < amountY; y++) {
            for (int x = 0; x < amountX; x++) {
                int px = x * width;
                int py = y * height;
                images[y][x] = mainImage.getSubimage(px, py, width, height);
            }
        }

        return images;
    }

}
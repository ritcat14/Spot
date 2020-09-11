package core.files;

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

}
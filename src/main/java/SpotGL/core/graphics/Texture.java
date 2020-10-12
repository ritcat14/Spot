package SpotGL.core.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Texture {

    private int texture;

    public Texture(int textureID) {
        this.texture = textureID;
    }

    public void bind(Shader shader) {
        shader.setUniform1f("textureSampler", 0);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void cleanUp() {
        glDeleteTextures(texture);
    }

}

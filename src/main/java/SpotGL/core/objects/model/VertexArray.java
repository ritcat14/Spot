package SpotGL.core.objects.model;

import SpotGL.core.graphics.Shader;
import SpotGL.core.graphics.Texture;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static SpotGL.core.VarStore.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

    private static float[] vertices = new float[] {
            glX,  glY, 0f,
            glX, glY - glH, 0f,
            glX + glW, glY - glH, 0f,
            glX + glW,  glY, 0f,
    };

    private static int[] indices = new int[] {
            0, 1, 3, 3, 1, 2,
    };

    public static float[] textureCoords = new float[] {
            0, 0,
            0, 1,
            1, 1,
            1, 0
    };

    public static float[] normals = new float[] {
            1.7019f, 1.2642937198294f, 1.125f,
            2.8557f, 2.0642062614962f, 1.7307f,
            2.8557f, 2.0642062614962f, 1.7307f,
            1.7019f, 1.2642937198294f, 1.125f
    };

    private int vao, vbo, ibo, tbo;
    private int count;

    public VertexArray() {
        this(vertices, indices, textureCoords, normals);
    }

    public VertexArray(float vertices[], int[] indices, float[] textureCoords, float[] normals) {
        this.count = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Create vertex buffer and bind
        FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
        verticesBuffer.put(vertices).flip();

        // Position VBO
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(VERTEX_ATTRIB);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        MemoryUtil.memFree(verticesBuffer);

        // Create texture buffer and bind
        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(textureCoords.length);
        textureBuffer.put(textureCoords).flip();

        // Texture coordinates VBO
        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(TEXTURE_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(TEXTURE_ATTRIB);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        MemoryUtil.memFree(textureBuffer);

        // create index buffer and bind
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        // Index VBO
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        MemoryUtil.memFree(indicesBuffer);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void bind() {
        glBindVertexArray(vao);
        glEnableVertexAttribArray(VERTEX_ATTRIB);
        glEnableVertexAttribArray(TEXTURE_ATTRIB);
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(VERTEX_ATTRIB);
        glDisableVertexAttribArray(TEXTURE_ATTRIB);
        glBindVertexArray(0);
    }

    public void draw() {
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
    }

    public void render(Shader shader, Texture texture) {
        texture.bind(shader);
        bind();
        draw();
        texture.unbind();
        unbind();
    }

    public void cleanUp() {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
        glDeleteBuffers(tbo);
        glDeleteBuffers(ibo);
    }

}

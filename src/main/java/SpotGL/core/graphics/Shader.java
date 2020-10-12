package SpotGL.core.graphics;
import SpotGL.core.utils.FileUtils;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    protected final int ID;
    protected final String name;

    private int vertexID, fragmentID;

    private Map<String, Integer> locationCache = new HashMap<>();

    public Shader(String vertex, String fragment) {
        String[] parts = vertex.split("/");
        String namePart = parts[parts.length-1];
        this.name = namePart.substring(0, namePart.length() - 11);
        System.out.println(name);

        StringBuilder vertexString = FileUtils.loadAsString(vertex);
        StringBuilder fragmentString = FileUtils.loadAsString(fragment);

        this.ID = createShader(vertexString, fragmentString);
    }

    private int createShader(StringBuilder vertex, StringBuilder fragment) {
        int ID = glCreateProgram();
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);


        glShaderSource(vertexID, vertex);
        glShaderSource(fragmentID, fragment);

        glCompileShader(vertexID);
        if (glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile vertex shader!");
            System.err.println(glGetShaderInfoLog(vertexID));
            return 0;
        }

        glCompileShader(fragmentID);
        if (glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile fragment shader!");
            System.err.println(glGetShaderInfoLog(fragmentID));
            return 0;
        }

        glAttachShader(ID, vertexID);
        glAttachShader(ID, fragmentID);

        glLinkProgram(ID);
        glValidateProgram(ID);

        return ID;
    }

    public void bind() {
        glUseProgram(ID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name))
            return locationCache.get(name);
        int result = glGetUniformLocation(ID, name);
        if (result < 0) System.err.println("Could not find uniform variable '" + name + "'!");
        else locationCache.put(name, result);
        return result;
    }

    public void setUniformBoolean(String name, boolean value) {
        float toLoad = (value) ? 1 : 0;
        setUniform1f(name, toLoad);
    }

    public void setUniform1i(String name, int value) {
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, Vector2f vector2f) {
        glUniform2f(getUniform(name), vector2f.x, vector2f.y);
    }

    public void setUniform3f(String name, Vector3f vector3f) {
        glUniform3f(getUniform(name), vector3f.x, vector3f.y, vector3f.z);
    }

    public void setUniform4f(String name, Vector4f vector4f) {
        glUniform4f(getUniform(name), vector4f.x, vector4f.y, vector4f.z, vector4f.w);
    }

    public void setUniformMatrix4f(String name, Matrix4f matrix4f) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer floatBuffer = stack.mallocFloat(16);
            matrix4f.get(floatBuffer);
            glUniformMatrix4fv(getUniform(name), false, floatBuffer);
        }
    }
    public void cleanUp() {
        unbind();
        glDetachShader(ID, vertexID);
        glDetachShader(ID, fragmentID);
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
        glDeleteProgram(ID);
    }
}

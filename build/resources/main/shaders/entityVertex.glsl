#version 400 core

in vec4 position;
in vec2 textureCoords;

out vec2 passTextureCoords;

uniform mat4 projectionMatrix;
uniform mat4 transformationMatrix;

void main()
{
    gl_Position = projectionMatrix * transformationMatrix * position;
    passTextureCoords = textureCoords;
}
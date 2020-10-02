#version 400 core

in vec2 passTextureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;

void main()
{
    vec4 colour = texture(textureSampler,passTextureCoords);
    out_Color = colour;
}
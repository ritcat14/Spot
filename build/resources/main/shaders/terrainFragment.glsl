#version 450 core

in vec2 passTextureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;

void main()
{
    out_Color = texture(textureSampler,passTextureCoords);
}
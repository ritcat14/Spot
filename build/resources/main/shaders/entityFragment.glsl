#version 330

in vec2 inTexCoord;

out vec4 fragColor;

uniform sampler2D textureSampler;

void main()
{
    fragColor = texture(textureSampler, inTexCoord);
}
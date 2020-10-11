#version 450

in vec2 outTexCoord;
in float outDistance;
in float outRenderDistance;

out vec4 fragColor;

uniform sampler2D textureSampler;

void main()
{
    if (outDistance >= outRenderDistance) discard;
    fragColor = texture(textureSampler, outTexCoord);
}
#version 450

layout (location=0) in vec3 position;
layout (location=1) in vec2 texCoord;

out vec2 outTexCoord;
out float outDistance;
out float outRenderDistance;

uniform mat4 projectionMatrix;
uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform float renderDistance;
uniform vec2 centerPos;

void main()
{
    gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
    outTexCoord = texCoord;
    outDistance = sqrt((centerPos.y - gl_Position.y) * (centerPos.y - gl_Position.y) + (centerPos.x - gl_Position.x) * (centerPos.x - gl_Position.x));
    outRenderDistance = renderDistance;
}
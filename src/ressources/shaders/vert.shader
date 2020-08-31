#version 150

in vec3 position;
in vec2 texCoords;
out vec2 pass_texCoords;

uniform mat4 transformMat;
uniform mat4 projectMat;
uniform mat4 viewMat;

void main(void){
    pass_texCoords = texCoords;
    gl_Position = projectMat * viewMat * transformMat * vec4(position, 1.0);
}
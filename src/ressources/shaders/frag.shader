#version 150

in vec2 pass_texCoords;
out vec4 out_Color;

uniform sampler2D texSamp;

void main(void){
    out_Color = vec4(pass_texCoords.x, pass_texCoords.y, 0, 1);
    //out_Color = vec4(1, 1, 0, 1);
    //out_Color = texture(texSamp, in_texCoords);
}
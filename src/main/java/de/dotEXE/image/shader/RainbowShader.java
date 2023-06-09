package de.dotEXE.image.shader;


import java.awt.*;
import java.util.Vector;

public class RainbowShader extends Shader {

    public RainbowShader() {
        super();
    }

    @Override
    public Color main(Vector<Integer> fragCoord, float iTime) {
        super.fragCoord = fragCoord;
        super.updateUv();
        return new Color((int) ((0.5+0.5*Math.cos(iTime+uv.get(0)+0))*255),
                (int) ((0.5+0.5*Math.cos(iTime+uv.get(1) +2))*255),
                (int) (0.5+0.5*Math.cos(iTime+uv.get(0)+4))*255);
    }
}

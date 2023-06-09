package de.dotEXE.image.shader;

import java.awt.*;
import java.util.Vector;

public class Shader {
    final Vector<Integer> iResolution;
    Vector<Integer> uv;
    Vector<Integer> fragCoord;

    public Shader(){
        this.iResolution = new Vector<>();
        iResolution.set(1,128);
        iResolution.set(2,128);
        this.uv = new Vector<>();
    }
    public Color main(Vector<Integer> fragCoord, float iTime){
        this.fragCoord = fragCoord;
        updateUv();

        return new Color(255,255,255);
    }
    void updateUv(){
        uv.set(1,fragCoord.get(1)/iResolution.get(1));
        uv.set(2,fragCoord.get(2)/iResolution.get(2));
    }
}

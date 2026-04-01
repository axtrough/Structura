package net.raccoon.will.structura.api.util;

import java.awt.*;

public class ColorUitl {


    public static int rgb(int R, int G, int B) {
        Color color = new Color(R, G, B);
        return color.getRGB();
    }

    public static int argb(int A, int R, int G, int B) {
        Color color = new Color(A,R, G, B);
        return color.getRGB();
    }


}

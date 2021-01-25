package mandelbrot;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelFractal {
    public void draw(PixelWriter pw, Complex a, Complex b, int w, int h) {
        int max = 100;
        Complex p = new Complex();
        Complex zn = new Complex();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                p.setVal(a.re() + x * (b.re() - a.re()) / w, a.im() + y * (b.im() - a.im()) / h);
                int i = 0;
                zn.setVal(p);
                while (i < max) {
                    zn.mul(zn).add(p); // zn^2+p
                    if (zn.sqrAbs() >= 4 * 4) {
                        break;
                    }
                    i++;
                }
                if (i < max) {
                    pw.setColor(x, y, Color.rgb(0,i,2*i+50));
                } else
                    pw.setColor(x, y,  Color.BLACK);
            }
        }
    }
}

package mandelbrot;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

import static javafx.scene.input.KeyCode.R;

public class Controller {
    public Label label;											// Etykieta
    public Canvas canvas;										// "PĹĂłtno" do rysowania
    private GraphicsContext gc;									// Kontekst graficzny do "pĹĂłtna"
    private double x1, y1, x2, y2;								// WspĂłĹrzÄdne ramki
    private double l;
    private double r;
    private double u;
    private double d;
    private double param;
    private int w;
    private int h;
    Complex a;
    Complex b;
    public int R=4;
    int size=512;
    private double pix_X=size;
    private double pix_Y=size;
    private double sizeH=size;
    private double sizeW=size;
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clear(gc);
        a=new Complex(-2.5,1.5);
        b=new Complex(1.25,-1.25);
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void rect(GraphicsContext gc) {						// Metoda rysuje prostokÄt o rogach (x1, y1) i (x2, y2)
        double x = x1;
        double y = y1;
        double w = x2 - x1;
        double h = y2 - y1;

        if (w < 0) {
            x = x2;
            w = -w;
        }

        if (h < 0) {
            y = y2;
            h = -h;
        }

        gc.strokeRect(x + 0.5, y + 0.5, w, h);
    }

    public void sayHello(ActionEvent actionEvent) {
        label.setText("Hello");
    }

    public void mouseMoves(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        gc.setGlobalBlendMode(BlendMode.DIFFERENCE);
        gc.setStroke(Color.WHITE);
        rect(gc);
        x2 = x;
        y2 = y;
        rect(gc);
        this.label.setText(String.format(" %.1f %.1f %.1f %.1f\n", this.x1, this.y1, this.x2, this.y2));

    }

    public void drawRect(ActionEvent actionEvent) {
        gc.setStroke(Color.web("#FFF0F0"));
        gc.setGlobalBlendMode(BlendMode.MULTIPLY);
        gc.strokeRect(100.5, 100.5, 200, 200);
    }

    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;
        label.setText(String.valueOf(x1)+" "+String.valueOf(y1));

    }

    public void mouseReleased(MouseEvent mouseEvent) {
        rect(gc);
        System.out.format("%f %f %f %f\n", x1, y1, x2, y2);
        zoom(gc);
    }

    public void clearCanvas(ActionEvent actionEvent) {
        clear(gc);
    }

    public void draw(ActionEvent actionEvent) {
        final int size = 512;
        WritableImage wr = new WritableImage(size, size);
        PixelWriter pw = wr.getPixelWriter();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                pw.setArgb(x, y, (x & y) == 0 ? 0xFFFF00FF : 0xFFFFFFFF);	// Rysuje trĂłjkÄt SierpiĹskiego
            }
        }

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, 512, 512);
    }

    public void drawmandel(ActionEvent actionEvent) {
        WritableImage wr = new WritableImage(512, 512);
        PixelWriter pw = wr.getPixelWriter();
        MandelFractal mandel= new MandelFractal();
        mandel.draw(pw, a, b, size, size);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, size, size);
        this.label.setText(" Done");
    }
    public void zoom(GraphicsContext gc){
        MandelFractal mandelFractal=new MandelFractal();
        WritableImage wr= new WritableImage(size,size);
        PixelWriter pw= wr.getPixelWriter();
        double width=Math.max(x2-x1,y2-y1);
        double X2=x1+width;
        System.out.println(X2);
        double Y2=y1+width;
        System.out.println(Y2);

        double ax=((b.re()-a.re())/size)*x1+a.re();
        double ay=((b.im()-a.im())/size)*y1+a.im();
        double bx=((b.re()-a.re())/size)*X2+a.re();
        double by=((b.im()-a.im())/size)*Y2+a.im();
        a=new Complex(ax,ay);
        b=new Complex(bx,by);

        mandelFractal.draw(pw,a,b,size,size);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr,0,0,size,size);

    }

}
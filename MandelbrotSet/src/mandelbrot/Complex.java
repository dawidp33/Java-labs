package mandelbrot;


import java.lang.ArithmeticException;

/**
 * Klasa Complex implementuje interfejs Field
 */
public class Complex implements Field<Complex> {
    private double r, i;

    /**
     * Konstruktory
     */
    public Complex(){
        this.r=0.0;
        this.i=0.0;

    }
    public Complex(double r)
    {
        this.r=r;
    }

    public Complex(double r, double i) {
        this.r = r;
        this.i = i;
    }
    public Complex(Complex x){
        this.r=x.r;
        this.i=x.i;
    }

    public Complex(String liczba){
        int litera_i = liczba.indexOf('i'); //zwraca indeks i
        int znak = liczba.indexOf('+'); // szukamy indexu +, jesli nie ma to -1
        if( znak == -1 ){
            znak = liczba.indexOf('-');
            if(znak == 0){
                String pom;
                pom = liczba.substring(1,liczba.length());
                znak = pom.indexOf('-') + 1;
            }
        }
        String pom1 = liczba.substring(0,znak);
        String pom2 = liczba.substring(znak,litera_i);
        double pomLiczba1 = Double.parseDouble(pom1);
        double pomLiczba2 = Double.parseDouble(pom2);
        this.r=pomLiczba1;
        this.i=pomLiczba2;

    }

    /**
     * Metody niestatyczne
     * @param b
     * @return
     */

    @Override
    public Complex add(Complex b) {
        Complex a=this;
        a.r+=b.r;
        a.i+=b.i;
        return this;
    }

    @Override
    public Complex sub(Complex b ) {
        Complex a=this;
        a.r-=b.r;
        a.i-=b.i;
        return this;

    }

    @Override
    public Complex mul(Complex b) {
        Complex a = this;
        double re=a.r * b.r - a.i * b.i;
        double im=a.r * b.i + a.i * b.r;;
        a.r=re;
        a.i=im;

        return this;
    }

    @Override
    /**
     * dzielenie prez 0 to ArithmeticException
     */

    public Complex div(Complex b) throws ArithmeticException  {
        Complex a = this;
        int rePom = (int)b.r;
        int imPom = (int)b.i;

        try {
            rePom=2/rePom;
            imPom=2/imPom;

        }
        catch (ArithmeticException e){
            System.out.println("dzielenie przez 0");
            System.exit(0);
        }
        double re = ((a.r * b.r) + (a.i * b.i)) / ((b.r * b.r) + (b.i * b.i));
        double im = ((a.i * b.r) - (a.r * b.i)) / ((b.r * b.r) + (b.i * b.i));

        a.r=re;
        a.i=im;

        return this;
    }

    @Override
    public String toString(){
        String re = this.r+"";
        String im = "";
        if(this.i < 0)
            im = this.i+"i";
        else
            im = "+"+this.i+"i";
        return re+im;
    }
    public double abs(){
        Complex a = this;
        double abs=Math.hypot(a.i,a.r);
        return abs;

    }
    public double sqrAbs(){
        return r * r + i * i;
    }
    public double phase(){
        Complex a=this;
        return Math.atan2(a.r,a.i);
    }
    public double re(){
        Complex a= this;
        return a.r;
    }
    public double im(){
        Complex a= this;
        return a.i;
    }


    /**
     * Metody statyczne:
     * @param a
     * @param b
     * @return
     */

    public static Complex add(Complex a, Complex b){
        double real = a.r + b.r;
        double imag = a.i + b.i;
        Complex sum = new Complex(real, imag);
        return sum;
    }
    public static Complex sub(Complex a, Complex b){
        double real = a.r - b.r;
        double imag = a.i - b.i;
        Complex sum = new Complex(real, imag);
        return sum;

    }
    public static Complex mul(Complex a, Complex b){
        double real = a.r * b.r - a.i * b.i;
        double imag = a.r * b.i + a.i * b.r;
        Complex sum= new Complex(real,imag);
        return sum;
    }
    public static Complex div(Complex a , Complex b ) throws ArithmeticException{
        int rePom = (int)b.r;
        int imPom = (int)b.i;

        try {
            rePom=2/rePom;
            imPom=2/imPom;

        }
        catch (ArithmeticException e){
            System.out.println("dzielenie przez 0");
            System.exit(0);
        }
        double real = ((a.r * b.r) + (a.i * b.i)) / ((b.r * b.r) + (b.i * b.i));
        double imag = ((a.i * b.r) - (a.r * b.i)) / ((b.r * b.r) + (b.i * b.i));
        Complex sum = new Complex(real, imag);
        return sum;
    }
    public static double abs(Complex a){
        double sqrAbs=Math.hypot(a.i,a.r)*Math.hypot(a.i,a.r);
        return  sqrAbs;

    }
    public static double phase(Complex a){
        return Math.atan2(a.r,a.i);
    }
    public  static double sqrAbs(Complex a){
        double sqrAbs=Math.hypot(a.i,a.r)*Math.hypot(a.i,a.r);
        return  sqrAbs;
    }
    public static double re(Complex a){
        return a.r;
    }
    public static double im(Complex a){
        return a.i;
    }

    public void setRe(double x){
        this.r=x;
    }
    public void setIm(double x){
        this.i=x;
    }
    public void setVal(Complex b){
        Complex a=this;
        a.r=b.r;
        a.i=b.i;
    }
    public void setVal(double a, double b){
        Complex c=this;
        c.r=a;
        c.i=b;

    }
    static Complex valueOf(String liczba){
        int litera_i = liczba.indexOf('i');
        int znak = liczba.indexOf('+');
        if( znak == -1 ){
            znak = liczba.indexOf('-');
            if(znak == 0){
                String pom;
                pom = liczba.substring(1,liczba.length());
                znak = pom.indexOf('-') + 1;
            }
        }
        String pom1 = liczba.substring(0,znak);
        String pom2 = liczba.substring(znak,litera_i);
        double pomLiczba1 = Double.parseDouble(pom1);
        double pomLiczba2 = Double.parseDouble(pom2);

        return new Complex(pomLiczba1,pomLiczba2);
    }



}


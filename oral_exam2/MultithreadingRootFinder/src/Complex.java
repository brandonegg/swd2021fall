//Source: https://www.math.ksu.edu/~bennett/jomacg/c.html
/**
  Complex implements a complex number and defines complex
  arithmetic and mathematical functions
 */
public class Complex extends Object {

    private double x,y;

    /**
     Constructs the complex number z = u + i*v
     @param u Real part
     @param v Imaginary part
     */
    public Complex(double u,double v) {
        x=u;
        y=v;
    }

    /**
     Real part of this Complex number
     (the x-coordinate in rectangular coordinates).
     @return Re[z] where z is this Complex number.
     */
    public double real() {
        return x;
    }

    /**
     Imaginary part of this Complex number
     (the y-coordinate in rectangular coordinates).
     @return Im[z] where z is this Complex number.
     */
    public double imag() {
        return y;
    }

    /**
     String representation of this Complex number.
     @return x+i*y, x-i*y, x, or i*y as appropriate.
     */
    public String toString() {
        if (x!=0 && y>0) {
            return x+" + "+y+"i";
        }
        if (x!=0 && y<0) {
            return x+" - "+(-y)+"i";
        }
        if (y==0) {
            return String.valueOf(x);
        }
        if (x==0) {
            return y+"i";
        }
        // shouldn't get here (unless Inf or NaN)
        return x+" + i*"+y;

    }
}
public class Master {

    public static void main(String[] args) {
        Complex[] roots = findRoots(1,2,3);

        System.out.println(roots[0].toString() + " " + roots[1].toString());
    }

    //TODO: Doesn't work for imaginaries!
    public static Complex[] findRoots(double a, double b, double c) {
        // x= (-b +- sqrt(b^2 -4ac))/2a
        double sqrtTerm = (Math.pow(b,2) - 4*a*c);
        Complex[] complexRoots = new Complex[2];

        if (sqrtTerm < 0) {
            complexRoots[0] = new Complex(-b/(2*a), Math.sqrt(-1*sqrtTerm)/(2*a));
            complexRoots[1] = new Complex(-b/(2*a), -1*Math.sqrt(-1*sqrtTerm)/(2*a));
        } else {
            complexRoots[0] = new Complex((-b + Math.sqrt(sqrtTerm)) / (2 * a), 0);
            complexRoots[1] = new Complex((-b - Math.sqrt(sqrtTerm)) / (2 * a), 0);
        }

        return complexRoots;
    }

}

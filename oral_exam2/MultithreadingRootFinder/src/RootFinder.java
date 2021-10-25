public class RootFinder implements Runnable {

    @Override
    public void run() {

    }

    private void findRoots(double a, double b, double c) {
        // x= (-b +- sqrt(b^2 -4ac))/2a
        double root1 = (-b + Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
        double root2 = (-b - Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
    }

}

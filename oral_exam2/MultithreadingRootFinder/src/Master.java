public class Master {

    public static void main(String[] args) {
        double[] roots = findRoots(1,2,3);

        System.out.println(roots[0] + " " + roots[1]);
    }

    public static double[] findRoots(double a, double b, double c) {
        // x= (-b +- sqrt(b^2 -4ac))/2a
        double root1 = (-b + Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
        double root2 = (-b - Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);

        return new double[] {root1, root2};
    }

}

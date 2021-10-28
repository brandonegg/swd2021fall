package data;

/**
 * Class used to store data related to a root, and how it was computed.
 * @see threading.RootFinder
 * @see Complex
 */
public class RootData {

    /**
     * The 3-tuple input for the quadratic function that produced the associated roots.
     */
    private double[] inputABC;
    /**
     * An array of Complex with size of 2, storing each of the two roots computed from the inputABC
     * @see Complex
     */
    private Complex[] roots;
    /**
     * Reference to the thread object which computed the roots
     */
    private Thread thread;

    /**
     * Creates a RootData object, typically created by RootFinder when a new root has been computed.
     * @param inputABC  The a,b,c 3-tuple which was taken to compute the roots
     * @param roots     The two roots computed from the inputABC values
     * @param thread    The thread which computed the roots
     */
    public RootData(double[] inputABC, Complex[] roots, Thread thread) {
        this.inputABC = inputABC;
        this.roots = roots;
        this.thread = thread;
    }

    /**
     * Gets the thread object that produced the RootData object.
     * @return  Thread object which produced the roots
     * @see Thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Displays the root data in string format.
     * @return  shows "For = a;b;c\n Roots: root1; root2\n Roots computed on Thread: thread data"
     */
    @Override
    public String toString() {
       return "For a = " + inputABC[0] + "; b = " + inputABC[1] + "; c = " + inputABC[2] + "\n" +
               "Roots = " + roots[0].toString() + "; " + roots[1].toString() + "\n" +
               "Roots computed on thread: " + thread.toString();
    }

}

package threading;

import data.Complex;
import data.RootData;

/**
 * Class for computing the roots implementing runnable to allow for running on separate threads.
 * Until the thread this object is on is shutdown, it will continue to compute 3-tuple sets placed
 * into the inputBuffer. Results are sent to outputBuffer
 * @see RootFinder
 */
public class RootFinder implements Runnable {

    /**
     * Roots to be computed are placed in the inputBuffer
     */
    private final CircularBuffer<double[]> inputBuffer;
    /**
     * Output roots are added to the outputBuffer
     */
    private final CircularBuffer<RootData> outputBuffer;

    /**
     * RootFinder constructor which initializes inputBuffer and outputBuffer to a provided
     * shared buffer for each. Used to share workload between threads.
     * @param inputBuffer   Inputbuffer where 3-tuples are consumed to output roots
     * @param outputBuffer  Outputbuffer where produced roots are added
     */
    public RootFinder(CircularBuffer<double[]> inputBuffer, CircularBuffer<RootData> outputBuffer) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
    }

    /**
     * Run method comes from Runnable interface. This method is called on thread start
     * which is handled by ExecutorService in our Master class.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                double[] abcInput = inputBuffer.blockingGet();
                Complex[] roots = findRoots(abcInput);

                outputBuffer.blockingPut(new RootData(abcInput, roots, Thread.currentThread()));

            } catch (InterruptedException e) {
                System.out.println("Closing " + Thread.currentThread().toString());
            }
        }
    }

    /**
     * Find roots from a 3-tuple array.
     * @param array double array with size of 3, where index 0 represents a term, 1 represents b term, etc.
     * @return  The resulting two roots stored in a Complex object.
     * @see Complex
     */
    private Complex[] findRoots(double[] array) {
        if (array.length < 3) {
            return null;
        }
        return findRoots(array[0], array[1], array[2]);
    }

    /**
     * Takes inputs a,b, and c and outputs two complex Roots from these inputs.
     * @param a a value of ax^2 + bx + c
     * @param b b value of ax^2 + bx + c
     * @param c c value of ax^2 + bx + c
     * @return  The two roots represented as Complex objects
     * @see Complex
     */
    private Complex[] findRoots(double a, double b, double c) {
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

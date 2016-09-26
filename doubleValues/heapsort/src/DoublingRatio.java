import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Disclaimer: This code was taken from 
 *  http://algs4.cs.princeton.edu/14analysis/DoublingRatio.java.html and only 
 *  minor modifications have been made to adapt it to my purpose.
 * 
 *  Compilation:  javac DoublingRatio.java
 *  Execution:    java DoublingRatio
 *  Dependencies: Heap.java (my code) Stopwatch.java StdRandom.java StdOut.java
 ******************************************************************************/

/**
 *  The {@code DoublingRatio} class provides a client for measuring
 *  the running time of a method using a doubling ratio test.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DoublingRatio {
    private static final double MAXIMUM_DOUBLE = 1000000.0;

    // This class should not be instantiated.
    private DoublingRatio() { }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     *   with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_DOUBLE, MAXIMUM_DOUBLE);
        }
        Stopwatch timer = new Stopwatch();
        Heap.sort(a);
        return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth, along
     * with ratios of running times between successive array sizes.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) { 
        double prev = timeTrial(125);
        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%6d %7.1f %5.1f\n", n, time, time/prev);
            //StdOut.printf("%6d %f %f\n", n, time, time/prev);
            prev = time;
        } 
    } 
}
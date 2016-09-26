import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

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

    // This class should not be instantiated.
    private DoublingRatio() { }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     *   with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(String[] a, int n) {
        // Double array size      
        int N = n * a.length;
        String[] newA = new String[N];
        // Fill new larger array with n smaller a arrays
        for (int i = 0; i < N; i = i + a.length)
            for (int j = 0, k = i; j < a.length; j++, k++)
                newA[k] = a[j];
        // Shuffle array to guarantee true timing 
        StdRandom.shuffle(newA);
        // Gather empirical data for this iteration
        Stopwatch timer = new Stopwatch();
        Insertion.sort(newA);
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
        In smallIn = new In("/Users/Hennessy/algs4/project2/comparableValues/Data/small.txt");
        String s = smallIn.readAll();
        String[] sm = s.split("\\s+");
        double prev = timeTrial(sm, 1);
        for (int n = 2; true; n += n) {
            double time = timeTrial(sm, n);
            StdOut.printf("%6d %7.1f %5.1f\n", n *sm.length, time, time/prev);
            //StdOut.printf("%6d %f %f\n", n, time, time/prev);
            prev = time;
        } 
    } 
}
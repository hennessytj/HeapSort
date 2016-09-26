import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class CorrectnessTest
{
    private static final double MAXIMUM_DOUBLE = 1000000.0;
    
    // Should not be instantiated
    private CorrectnessTest() { }
    
    public static boolean isSorted(double[] a)
    {
        int n = a.length;
        for (int i = 1; i < n; i++)
            if (less(a[i],a[i-1])) return false;
        return true;
    }
    
    public static void print(double[] a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }       
    
    public static void fillRandom(double[] a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++) 
        {
            a[i] = StdRandom.uniform(-MAXIMUM_DOUBLE, MAXIMUM_DOUBLE);
        }
    }
    
    public static void main(String[] args)
    {
        final int LARGE = (int) Math.pow(2, 25);
        final int SMALL = (int) Math.pow(2, 16);
        double time;
        
        /* Test Case 1 - Empty */
        double[] a = new double[0];
        Stopwatch sw = new Stopwatch();
        Heap.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 1 - Empty in " + time + "s");        
        // print(a);
        
        /* Test Case 2 - Size 1 */
        a = new double[1];
        a[0] = 0;
        sw = new Stopwatch();
        Heap.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 2 - Size 1 in " + time + "s");
        // print(a);
        
        /* Test Case # - Large n 
        // will be tested below with other cases

        /* Test Case 3 - All Duplicates */
        a = new double[LARGE];
        for (int i = 0; i < LARGE; i++)
            a[i] = 1;
        sw = new Stopwatch();
        Heap.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 3 - All Duplicates in " + time + "s");
        // print(a):
        
        /* Test Case 4 - Some Duplicates */
        a = new double[LARGE];
        for (int i = 0; i < LARGE; i++)
        {
            if (i % 2 == 0) a[i] = 42.0;
            else            a[i] = 13.0;
        }
        sw = new Stopwatch();
        Heap.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 4 - Some Duplicates in " + time + "s");
        // print(a);
        
        /* Test Case 5 - Non-decreasing */
        a = new double[LARGE];
        for (int i = 0; i < LARGE; i++)
            a[i] = i;
        sw = new Stopwatch();
        Heap.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 5 - Non-decreasing in " + time + "s");
        // print(a);
        
        /* Test Case 6 - Decreasing */
        a = new double[LARGE];
        double j = (double) LARGE;
        for (int i = 0; i < LARGE; i++)
        {
            a[i] = j;
            j--;
        }
        sw = new Stopwatch();
        Heap.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 6 - Decreasing in " + time + "s");
        // print(a);

        /* Test Case 7 - Random */
        boolean result = true;
        int trials = 0;
        double sum = 0.0;
        double avgTime;
        a = new double[SMALL];
        while (result && trials < 10000)
        {
            trials++;
            fillRandom(a);
            sw = new Stopwatch();
            Heap.sort(a);
            sum += sw.elapsedTime();
            if (!isSorted(a)) { result = false; break; }
            // print(a);
        }
        avgTime = sum / SMALL;
        if (result) StdOut.println("Passed Test Case 7 - Random");
        StdOut.println("average time for random cases is " + avgTime + "s");
    }
    
    /**
     * Use comparable interface capability compareTo to
     * generically return order.
     * 
     * compareTo Cases:
     * -a  > b -> positive value returned
     * -a == b -> zero value returned
     * -a  < b -> negative value returned
     */
    private static boolean less(double a, double b)
    { return a < b; }
}
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class CorrectnessTest
{
    private static final double MAXIMUM_INTEGER = 1000000.0;
    
    // Should not be instantiated
    private CorrectnessTest() { }
    
    public static boolean isSorted(double[] a)
    {
        int N = a.length;
        for (int i = 1; i < N; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }
    
    public static void print(double[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }       
    
    public static void fillRandom(double[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++) 
        {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
    }
    
    public static void main(String[] args)
    {
        /* Test Case 1 - Empty */
        double[] a = new double[0];
        Insertion.sort(a);
        if (isSorted(a)) StdOut.println("Passed Test Case 1 - Empty");
        // print(a);
        
        /* Test Case 2 - Size 1 */
        a = new double[1];
        a[0] = 0;
        Insertion.sort(a);
        if (isSorted(a)) StdOut.println("Passed Test Case 2 - Size 1");
        // print(a);
        
        /* Test Case # - Large n */
        // will be tested below with other cases

        /* Test Case 3 - All Duplicates */
        a = new double[250000];
        for (int i = 0; i < 250000; i++)
            a[i] = 1.0;
        Insertion.sort(a);
        if (isSorted(a)) StdOut.println("Passed Test Case 3 - All Duplicates");
        // print(a):
        
        /* Test Case 4 - Some Duplicates */
        a = new double[250000];
        for (int i = 0; i < 250000; i++)
        {
            if (i % 2 == 0) a[i] = 42.0;
            else            a[i] = 13.0;
        }
        Insertion.sort(a);
        if (isSorted(a)) StdOut.println("Passed Test Case 4 - Some Duplicates");
        // print(a);
        
        /* Test Case 5 - Non-decreasing */
        a = new double[1000000];
        for (int i = 0; i < 1000000; i++)
            a[i] = (double) i;
        Insertion.sort(a);
        if (isSorted(a)) StdOut.println("Passed Test Case 5 - Non-decreasing");
        // print(a);
        
        /* Test Case 6 - Decreasing */
        a = new double[25000];
        int j = 25000;
        for (int i = 0; i < 25000; i++)
        {
            a[i] = (double) j;
            j--;
        }
        Insertion.sort(a);
        if (isSorted(a)) StdOut.println("Passed Test Case 6 - Decreasing");
        // print(a);

        /* Test Case 7 - Random */
        boolean result = true;
        int trials = 0;
        a = new double[10];
        while (trials < 10000)
        {
            trials++;
            fillRandom(a);
            Insertion.sort(a);
            if (!isSorted(a)) { result = false; break; }
            // print(a);
        }
        if (result) StdOut.println("Passed Test Case 7 - Random");
    }
}
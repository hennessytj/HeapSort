import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

// TODO: Add a stopwatch to time each test and print out the time next to them
public class CorrectnessTest
{    
    // Should not be instantiated
    private CorrectnessTest() { }
    
    public static boolean isSorted(Comparable[] a)
    {
        int n = a.length;
        for (int i = 1; i < n; i++)
            if (less(a[i],a[i-1])) return false;
        return true;
    }
    
    public static void print(Comparable[] a)
    {
        int n = a.length;
        for (int i = 1; i < n; i = i + i)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }       
    
    public static void shuffle(Comparable[] a)
    { StdRandom.shuffle(a); }
    
    public static void reverseArray(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N/2; i++)
            swap(a, i, N - 1 - i);
    }
    
    public static void main(String[] args)
    {
        final int LARGE = (int) Math.pow(2, 12);
        final int SMALL = (int) Math.pow(2, 12);
        double time;
        
        // Read text files containing strings
        In smallIn = new In("/Users/Hennessy/algs4/project2/comparableValues/Data/small.txt");
        In medIn = new In("/Users/Hennessy/algs4/project2/comparableValues/Data/medium.txt");
        In largeIn = new In("/Users/Hennessy/algs4/project2/comparableValues/Data/large.txt");
        
        // read all strings from file
        String s = smallIn.readAll();
        String m = medIn.readAll();
        String l = largeIn.readAll();
        
        // put all strings into arrays
        // sm contains 1694 words
        // md contains 419911 words
        // lg contains 1259719 words
        String[] sm = s.split("\\s+");
        String[] md = m.split("\\s+");
        String[] lg = l.split("\\s+");
        
        StdOut.println("Small file size = " + sm.length);
        StdOut.println("Medium file size = " + md.length);
        StdOut.println("Large file size = " + lg.length);
        
        /* Test Case 1 - Empty */
        String[] a = new String[0];
        Stopwatch sw = new Stopwatch();
        Insertion.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 1 - Empty in " + time + "s");        
        // print(a);
        
        /* Test Case 2 - Size 1 */
        a = new String[1];
        a[0] = "Hello World!";
        sw = new Stopwatch();
        Insertion.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 2 - Size 1 in " + time + "s");
        // print(a);
        
        /* Test Case # - Large n */
        // will be tested below with other cases

        /* Test Case 3 - All Duplicates */
        a = new String[SMALL];
        for (int i = 0; i < SMALL; i++)
            a[i] = "duplicate";
        sw = new Stopwatch();
        Insertion.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 3 - All Duplicates in " + time + "s");
        // print(a);
        
        /* Test Case 4 - Some Duplicates */
        a = new String[LARGE];
        for (int i = 0; i < LARGE; i++)
        {
            if (i % 2 == 0) a[i] = "Apple";
            else            a[i] = "Zoo";
        }
        sw = new Stopwatch();
        Insertion.sort(a);
        time = sw.elapsedTime();
        if (isSorted(a)) StdOut.println("Passed Test Case 4 - Some Duplicates in " + time + "s");
        // print(a);
        
        /* Test Case 5 - Non-decreasing aka sorted already */
        Arrays.sort(md);
        sw = new Stopwatch();
        Insertion.sort(md);
        time = sw.elapsedTime();
        if (isSorted(md)) StdOut.println("Passed Test Case 5 - Non-decreasing in " + time + "s");
        // print(md);
        
        
        /* Test Case 6 - Decreasing */
        reverseArray(sm);
        // print(sm);
        sw = new Stopwatch();
        Insertion.sort(sm);
        time = sw.elapsedTime();
        if (isSorted(sm)) StdOut.println("Passed Test Case 6 - Decreasing in " + time + "s");
        // print(sm);

        /* Test Case 7 - Random */
        boolean result = true;
        int trials = 0;
        double sum = 0.0;
        double avgTime;
        while (result && trials < 1000)
        {
            trials++;
            shuffle(sm);
            sw = new Stopwatch();
            Insertion.sort(sm);
            sum += sw.elapsedTime();
            if (!isSorted(sm)) { result = false; break; }
            // print(sm);
        }
        avgTime = sum / SMALL;
        if (result) StdOut.println("Passed Test Case 7 - Random Ordering");
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
    private static boolean less(Comparable a, Comparable b)
    { return a.compareTo(b) < 0; }
    
    public static void swap(Comparable[] a, int p, int q)
    {
        Object temp = a[p];
        a[p] = a[q];
        a[q] = (Comparable) temp;
    }
}
import edu.princeton.cs.algs4.StdOut;

public class Insertion
{
    
    public static void sort(double[] a)
    {
        int N = a.length;
        for (int j = 1; j < N; j++)
        {
            double key = a[j];
            int i = j - 1;
            while (i >= 0 && a[i] > key)
            {
                // move elements one posn to the right
                a[i + 1] = a[i];
                i--;
            }
            // key can now be inserted into correct posn
            a[i + 1] = key;
        }
    }

    public static void main(String[] args)
    {
        double[] a = {5, 2, 4, 4, 4, 5, 6, 6, 1, 3, 3};
        sort(a);
        
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}
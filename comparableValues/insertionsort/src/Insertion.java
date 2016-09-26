import edu.princeton.cs.algs4.StdOut;

public class Insertion
{
    
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int j = 1; j < N; j++)
        {
            Comparable key = a[j];
            int i = j - 1;
            while (i >= 0 && less(key, a[i]))
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
        String[] a = {"a", "b", "c", "e", "a", "b"};
        sort(a);
        
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
    
    /****************** Helper functions ************************/
    /**
     * Use comparable interface capability compareTo to
     * generically return order.
     * 
     * compareTo Cases:
     * -pq[a]  > pq[b] -> positive value returned
     * -pq[a] == pq[b] -> zero value returned
     * -pq[a]  < pq[b] -> negative value returned
     */
    private static boolean less(Comparable p, Comparable q)
    { return p.compareTo(q) < 0; }
    
    /**
     * Takes two indices into pq array and swaps them.
     * 
     * a index of first swapped
     * b index of second swapped
     */
    public static void swap(Comparable[] heap, int p, int q)
    {
        Object temp = heap[p];
        heap[p] = heap[q];
        heap[q] = (Comparable) temp;
    }
}
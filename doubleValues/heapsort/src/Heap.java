import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Heap
{   
    // Do not instantiate
    public Heap() { }
    
    public static void sort(double[] heap)
    {
        int n = heap.length - 1;
        for (int k = n/2; k >= 0; k--)
            sink(heap, k, n);
        while (n > 0)
        {
            swap(heap, 0, n--);
            sink(heap, 0, n);
        }
    }
    
    /**
     * Demote element at index k until heap conditions are met.
     * The element at index k will be moved down the tree until
     * a smaller child or array bounds are encountered.
     * 
     * k index of element to be sunk
     */
    public static void sink(double[] heap, int k, int n)
    {
        // Make sure left child index is still within the valid range
        // The valid range consists of all keys in pq, N can be less than
        // or equal to size of pq array
        int parent = k;
        int child = 2*k + 1;    
        while (child <= n)
        { 
            // condition a: short circuit if child + 1 will be out of range of N
            // condition b: ensure the swapped child is larger of the siblings
            if (child < n && less(heap[child], heap[child + 1])) child++;
            // make sure largest sibling is larger than parent before swapping
            // with parent, i.e., if largest child is less than parent this 
            // sub-heap is heap ordered
            if (less(heap[child], heap[parent])) break;
            swap(heap, parent, child);
            // extra code but intent is to make it more readable
            // if child and parent swapped, child becomes parent
            parent = child;
            child = 2*parent + 1;
        }
    }

    public static void main(String[] args)
    {    }
    
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
    private static boolean less(double p, double q)
    { return p < q; }
    
    /**
     * Takes two indices into pq array and swaps them.
     * 
     * a index of first swapped
     * b index of second swapped
     */
    public static void swap(double[] heap, int p, int q)
    {
        double temp = heap[p];
        heap[p] = heap[q];
        heap[q] = temp;
    }
}
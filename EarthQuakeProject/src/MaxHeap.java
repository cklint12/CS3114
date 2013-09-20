

/**
 * Source code example for "A Practical Introduction to Data Structures and
 * Algorithm Analysis, 3rd Edition (Java)" by Clifford A. Shaffer Copyright
 * 2008-2011 by Clifford A. Shaffer
 */

/** Max-heap implementation */
public class MaxHeap
{
    private EQ[] Heap; // Pointer to the heap array
    private int  size; // Maximum size of the heap
    private int  n;   // Number of things in heap

    /**
     * Constructor supporting preloading of heap contents
     * @param h An array of EQ's
     * @param num the maximum size
     * @param max
     */
    public MaxHeap(EQ[] h, int num, int max)
    {
        Heap = h;
        n = num;
        size = max;
        buildheap();
    }

    /** @return Current size of the heap */
    public int heapsize()
    {
        return n;
    }

    /**
     * @param pos the position of the leaf
     * @return True if pos a leaf position, false otherwise
     */
    public boolean isLeaf(int pos)
    {
        return (pos >= n / 2) && (pos < n);
    }

    /**
     * @param pos the position of the leftchild
     * @return Position for left child of pos
     */
    public int leftchild(int pos)
    {
        assert pos < n / 2 : "Position has no left child";
        return 2 * pos + 1;
    }


    /**
     * @param pos the position of the right child
     * @return Position for right child of pos
     */
    public int rightchild(int pos)
    {
        assert pos < (n - 1) / 2 : "Position has no right child";
        return 2 * pos + 2;
    }


    /**
     * @param pos the position of the parent
     * @return Position for parent
     */
    public int parent(int pos)
    {
        assert pos > 0 : "Position has no parent";
        return (pos - 1) / 2;
    }


    /**
     * Insert val into heap
     * @param val the earthquake to insert into the heap
     */
    public void insert(EQ val)
    {
        assert n < size : "Heap is full";
        int curr = n++;
        Heap[curr] = val; // Start at end of heap
        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0)
            && (Heap[curr].getMagnitude() > (Heap[parent(curr)].getMagnitude())))
        {
            int parentTemp = parent(curr);
            DSutil.swap(Heap, curr, parentTemp);
            Heap[curr].setHeapPosition(curr);
            Heap[parentTemp].setHeapPosition(parentTemp);
            curr = parent(curr);
        }
    }

    /** Heapify contents of Heap */
    public void buildheap()
    {
        for (int i = n / 2 - 1; i >= 0; i--)
            siftdown(i);
    }


    /** Put element in its correct place */
    private void siftdown(int pos)
    {
        assert (pos >= 0) && (pos < n) : "Illegal heap position";
        while (!isLeaf(pos))
        {
            int j = leftchild(pos);
            if ((j < (n - 1))
                && (Heap[j].getMagnitude() < (Heap[j + 1].getMagnitude())))
                j++; // j is now index of child with greater value
            if ((Heap[pos].getMagnitude() >= Heap[j].getMagnitude()))

                return;
            int temp = j;
            DSutil.swap(Heap, pos, temp);
            Heap[pos].setHeapPosition(pos);
            Heap[temp].setHeapPosition(temp);
            pos = j; // Move down
        }
    }


    /**
     * Remove and return maximum value
     * @return Heap[n] the Earthquake that needs to be removed
     */
    public EQ removemax()
    {
        assert n > 0 : "Removing from empty heap";

        DSutil.swap(Heap, 0, --n); // Swap maximum with last value
        Heap[0].setHeapPosition(0);
        if (n != 0) // Not on last element
            siftdown(0); // Put new heap root val in correct place
        return Heap[n];
    }


    /**
     * Remove and return element at specified position
     *
     * @param pos the position of the earthquake
     * @return EQ the earthquake to be removed
     */
    public EQ remove(int pos)
    {
        assert (pos >= 0) && (pos < n) : "Illegal heap position";
        if (pos == (n - 1))
            n--; // Last element, no work to be done
        else
        {
            int temp0 = n-1;
            DSutil.swap(Heap, pos, --n); // Swap with last value
            Heap[temp0].setHeapPosition(temp0);
            Heap[pos].setHeapPosition(pos);

            // If we just swapped in a big value, push it up
            while ((pos > 0)
                && (Heap[pos].getMagnitude() > Heap[parent(pos)].getMagnitude()))
            {
                int temp1 = parent(pos);
                DSutil.swap(Heap, pos, parent(pos));
                Heap[pos].setHeapPosition(pos);
                Heap[temp1].setHeapPosition(temp1);
                pos = parent(pos);
            }
            if (n != 0)
                siftdown(pos); // If it is little, push down
        }
        return Heap[n];
    }


    // ----------------------------------------------------------
    /**
     * Returns the earthquake with the greatest magnitude
     *
     * @return Heap[0] the largest earthquake
     */
    public EQ getMax()
    {
        return Heap[0];
    }

    // ----------------------------------------------------------
    /**
     *
     */
    public String toString()
    {
        for (int i = 0; i < heapsize(); i++)
            System.out.println(Heap[i].getMagnitude());
        return "";
    }

}

import java.util.ArrayList;
import realtimeweb.earthquakeservice.domain.Coordinate;
import realtimeweb.earthquakeservice.domain.Earthquake;
import realtimeweb.earthquakeservice.domain.Report;

// -------------------------------------------------------------------------
/**
 * This class processes the Earthquakes by using two data-structures: 1. A Heap
 * that houses the Earthquakes by magnitude 2. A linked-queue that housees the
 * Earthquakes in the order they arrive. Earthquakes in the queue that are older
 * than 6 hours are dequeued.
 *
 * @author Klint Ciriaco
 * @author Romico Macatula
 * @version Sep 17, 2013
 */
public class EarthquakeTracker
{
    /**
     * A Linked-Queue that holds earthquakes
     */
    private LQueue<EQ>     eqQueue;
    /**
     * A Heap that holds the magnitude of the Earthquake
     */
    private MaxHeap        eqHeap;
    /**
     * An array of Earthquakes
     */
    private EQ[]           eqArray;
    /**
     * Processes the watchers
     */
    private WatcherTracker watcherTracker;

    // ----------------------------------------------------------
    /**
     * Create a new EarthquakeTracker object.
     */
    public EarthquakeTracker()
    {
        watcherTracker = new WatcherTracker();
        eqArray = new EQ[1000];
        eqQueue = new LQueue<EQ>();
        eqHeap = new MaxHeap(eqArray, 0, 1000);
    }

    // ----------------------------------------------------------
    /**
     * Removes earthquakes from the Heap and Queue
     * @param report
     *            The report from the earthquakeservice
     */
    public void purge(Report report)
    {
        long sixHours = 21600000;
        while ((eqQueue.length() != 0 && (report.getGeneratedTime()
            - eqQueue.frontValue().getTime() > sixHours)))
        {
            eqHeap.remove(eqQueue.frontValue().getHeapPosition());
            eqQueue.dequeue();
        }
    }
    // ----------------------------------------------------------
    /**
     * Adds the earthquakes to the queue
     * @param eqs The arraylist of EQ's
     */
    public void AddEarthquakes(ArrayList<EQ> eqs)
    {
        for (int i = 0; i < eqs.size(); i++)
        {

            //check the time stamps of the earthquake
           //if (eqs.get(i).getTime() > eqQueue.rearValue().getTime())
            {
                eqQueue.enqueue(eqs.get(i));
                eqHeap.insert(eqs.get(i));
            }
        }
    }
    // ----------------------------------------------------------
    /**
     * Queries the Earthquake
     */
    public void query()
    {
        if (eqHeap.heapsize() == 0)
        {
            System.out.println("No record on MaxHeap");
        }
        else
        {
            System.out
                .println("Location of largest earthquake in past 6 hours:\n");
            System.out.println(eqHeap.getMax().getLocationDescription());
        }
    }
    // ----------------------------------------------------------
    /**
     * Returns the queue for testing purposes
     *
     * @return eqQueue The queue that contains the earthquakess
     */
    public LQueue<EQ> getEQ()
    {
        return eqQueue;
    }

    // ----------------------------------------------------------
    /**
     * Converts the List of Earthquakes to EQ
     * @param earthquake The list of Earthquakes that need to be converted
     * @return convertedList the list of EQ's
     */
    public ArrayList<EQ> convertEarthquakesToEQ(ArrayList<Earthquake> earthquake)
    {
        ArrayList<EQ> convertedList = new ArrayList<EQ>();
        for (Earthquake convertMe: earthquake)
        {
           EQ newEQ = new EQ(convertMe);
           convertedList.add(newEQ);
        }

        return convertedList;
    }

    // ----------------------------------------------------------
    /**
     * Returns the heap for testing purposes
     * @return eqHeap the Heap that contains the earthquakes
     */
    public MaxHeap getHeap()
    {
        return eqHeap;
    }

    // ----------------------------------------------------------
    /**
     * Gets the WatcherTracker
     * @return watcherTracker the watcherTracker
     */
    public WatcherTracker getWatcherTracker()
    {
        return watcherTracker;
    }
}
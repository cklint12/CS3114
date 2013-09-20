

import java.util.ArrayList;
import realtimeweb.earthquakeservice.domain.Earthquake;
import realtimeweb.earthquakeservice.domain.History;
import realtimeweb.earthquakeservice.domain.Report;
import realtimeweb.earthquakeservice.domain.Threshold;
import realtimeweb.earthquakeservice.exceptions.EarthquakeException;
import realtimeweb.earthquakeservice.regular.EarthquakeService;

public class EarthquakeTrackerTest
    extends junit.framework.TestCase
{
    private EarthquakeTracker eqTracker;
    private EarthquakeService earthquakeService;
    private Report latestQuakes;

    public void setUp()
    {
         eqTracker = new EarthquakeTracker();
         earthquakeService = EarthquakeService.getInstance();

         latestQuakes = null;
         try {
              latestQuakes = earthquakeService.getEarthquakes(Threshold.ALL, History.HOUR);
         } catch (EarthquakeException e) {
             e.printStackTrace();
         }

    }

    public void testPurge()
    {
        ArrayList<Earthquake> earthquake = latestQuakes.getEarthquakes();

        Earthquake eqTest1 = latestQuakes.getEarthquakes().get(0);
        Earthquake eqTest2 = latestQuakes.getEarthquakes().get(1);
        Earthquake eqTest3 = latestQuakes.getEarthquakes().get(2);

        long eqTest1Time = eqTest1.getTime();
        long eqTest2Time = eqTest1.getTime();
        long eqTest3Time = eqTest1.getTime();


    }

    public void testAdd()
    {


    }





























}

package jujumap.juju;

import android.util.Log;
import org.osmdroid.util.BoundingBoxE6;

import java.util.ArrayList;
import java.util.Arrays;

public class Track extends ArrayList <TrackPoint> {

    float minLon = +180;
    float maxLon = -180;
    float minLat =  +90;
    float maxLat =  -90;

    float minAlt = 10000;
    float maxAlt = -1000;

    long minTime = 1397630887000L;
    long maxTime = 0;

    BoundingBoxE6 bBox;

    public Track() {}

    public void addPath (String rawPath) {

        TrackPoint tp;

        ArrayList <String> rawTrackpoints = new ArrayList <String> (Arrays.asList (rawPath.split (" ")));

        for (String rawTrackpoint : rawTrackpoints) {

            tp = new TrackPoint (rawTrackpoint);

            if (tp.lon < minLon) minLon = tp.lon;
            if (tp.lon > maxLon) maxLon = tp.lon;
            if (tp.lat < minLat) minLat = tp.lat;
            if (tp.lat > maxLat) maxLat = tp.lat;

            if (tp.alt < minAlt) minAlt = tp.alt;
            if (tp.alt > maxAlt) maxAlt = tp.alt;

            this.add (tp);
        }
    }

    public void addTP(double lat, double lon, double alt, float accuracy, double speed, double bearing, long time) {


        if (time < minTime) minTime = time;
        if (time > maxTime) maxTime = time;

        TrackPoint tp = new TrackPoint (lat, lon, alt, accuracy, speed, bearing, time);

        add(tp);
    }

    @Override
    public String toString () {

        StringBuilder sb = new StringBuilder();


        for (TrackPoint tp : this ) {

            sb.append(
                    tp.lon + "," + tp.lat + "," + tp.alt + "," +
                    tp.accuracy + "," + tp.speed + "," + tp.bearing + "," +
                    tp.time + "\n");
        }

        return sb.toString();
    }

    public BoundingBoxE6 get_bBox () {

        bBox = new BoundingBoxE6 (maxLat, maxLon, minLat, minLon);

        return bBox;
    }
}
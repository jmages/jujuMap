package jujumap.juju;

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

    private BoundingBoxE6 bBox;

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

    BoundingBoxE6 get_bBox () {

        bBox = new BoundingBoxE6 (maxLat, maxLon, minLat, minLon);

        return bBox;
    }
}
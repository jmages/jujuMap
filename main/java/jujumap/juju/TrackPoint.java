package jujumap.juju;

public class TrackPoint extends Geopoint {

    public TrackPoint (String rawTrackPoint) {

        super(rawTrackPoint);
    }

    public TrackPoint (double lat, double lon, long time) {

        super(lat, lon, time);
    }
}

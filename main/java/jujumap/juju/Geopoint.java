package jujumap.juju;

import java.util.ArrayList;
import java.util.Arrays;

public class Geopoint {

    String name     = "";

    String url      = "";
    String ortsInfo = "";
    String geo      = "";
    String roadInfo = "";
    String km       = "";

    float lon;
    float lat;
    float alt;

    public Geopoint (String rawTrackPoint) {

        splitRawTrackPoint (rawTrackPoint);
    }

    public Geopoint (String name, String rawTrackPoint, String description) {

        this.name = name;

        splitRawTrackPoint (rawTrackPoint);

        splitDescription (description);
    }

    private void splitDescription(String description) {

        ArrayList <String> rawInfo = new ArrayList <String> (Arrays.asList (description.split ("<!--")));

        if (rawInfo.size() == 5) {

            url      = rawInfo.get(0);
            ortsInfo = rawInfo.get(1).substring(0, rawInfo.get(1).length()-8).trim();
            geo      = rawInfo.get(2).substring(0, rawInfo.get(2).length()-8).trim();
            roadInfo = rawInfo.get(3).substring(0, rawInfo.get(3).length()-8).trim();
            km       = rawInfo.get(4).substring(0, rawInfo.get(4).length()-3).trim();
        }
    }

    public Geopoint (double lat, double lon) {

        name = "";
        this.lat = (float) lat;
        this.lon = (float) lon;
    }

    private void splitRawTrackPoint (String rawTrackPoint) {

        ArrayList <String> rawGeoData = new ArrayList <String> (Arrays.asList (rawTrackPoint.split (",")));

        switch (rawGeoData.size ()) {

            case 2:

                lon = Float.valueOf (rawGeoData.get(0));
                lat = Float.valueOf (rawGeoData.get(1));
                alt = 0;

                break;

            case 3:

                lon = Float.valueOf (rawGeoData.get(0));
                lat = Float.valueOf (rawGeoData.get(1));
                alt = Float.valueOf (rawGeoData.get(2));

                break;

            default:
                System.out.println ("Error: Trackpoint data is not valid: " + rawTrackPoint);
                System.exit (1);
        }
    }
}

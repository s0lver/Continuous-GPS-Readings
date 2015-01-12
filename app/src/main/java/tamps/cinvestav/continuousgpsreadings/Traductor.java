package tamps.cinvestav.continuousgpsreadings;

import android.location.Location;

import java.util.Date;

public class Traductor {
    public static String ubicacionAString(Location ubicacion) {
        return String.valueOf(ubicacion.getLatitude()) + ',' +
                ubicacion.getLongitude() + ',' +
                ubicacion.getAccuracy() + ',' +
                ubicacion.getAltitude() + ',' +
                new Date(ubicacion.getTime());
    }
}

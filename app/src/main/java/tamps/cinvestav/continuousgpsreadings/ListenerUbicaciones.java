package tamps.cinvestav.continuousgpsreadings;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class ListenerUbicaciones implements LocationListener{
    Presentador presentador;

    public ListenerUbicaciones(Presentador presentador) {
        this.presentador = presentador;
    }

    @Override
    public void onLocationChanged(Location location) {
        presentador.lecturaObtenida(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

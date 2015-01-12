package tamps.cinvestav.continuousgpsreadings;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class Presentador {
    private Context contexto;
    private boolean lecturaGPSActiva;
    private LocationManager adminUbicaciones;
    private ListenerUbicaciones listenerUbicaciones;
    private Escritor escritor;
    private LectorBateria lectorBateria;

    public Presentador(Context contexto) {
        this.contexto = contexto;
        this.adminUbicaciones = (LocationManager) contexto.getSystemService(Context.LOCATION_SERVICE);
        this.listenerUbicaciones = new ListenerUbicaciones(this);
    }

    public void lecturaObtenida(Location ubicacion) {
        escritor.escribirRegistro(ubicacion);
        Log.v(this.getClass().getSimpleName(), "Lectura obtenida: " + Traductor.ubicacionAString(ubicacion));
    }

    public void iniciarLecturas(String nombreArchivo) {
        if (!lecturaGPSActiva) {
            this.lectorBateria = new LectorBateria(contexto);
            this.escritor = new Escritor("ubicacionesContinuas", nombreArchivo, lectorBateria);
            escritor.prepararAccesoArchivo();
            this.adminUbicaciones.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listenerUbicaciones);
            lecturaGPSActiva = true;
        }
        else {
            Log.w(this.getClass().getSimpleName(), "Llamada a iniciar lecturas cuando las lecturas ya han sido iniciadas");
        }
    }

    public void detenerLecturas() {
        if (lecturaGPSActiva) {
            this.adminUbicaciones.removeUpdates(listenerUbicaciones);
            lecturaGPSActiva = false;
        }
        else {
            Log.w(this.getClass().getSimpleName(), "Llamada a detener lecturas cuando las lecturas ya han sido detenidas");
        }
    }

}

package tamps.cinvestav.continuousgpsreadings;

import android.location.Location;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {
    private String nombreCarpeta;
    private String nombreArchivo;
    private String rutaAlmacenamiento;

    private LectorBateria lectorBateria;

    public Escritor(String nombreCarpeta, String nombreArchivo, LectorBateria lectorBateria) {
        this.nombreCarpeta = nombreCarpeta;
        this.nombreArchivo = nombreArchivo;
        this.lectorBateria = lectorBateria;
    }

    public void prepararAccesoArchivo() {
        String pathSeparator = File.separator;
        this.rutaAlmacenamiento = Environment.getExternalStorageDirectory().getAbsolutePath() +
                pathSeparator + nombreCarpeta + pathSeparator;

        File jerarquiaDirectorios = new File(rutaAlmacenamiento);
        if (!jerarquiaDirectorios.exists()) jerarquiaDirectorios.mkdirs();
    }

    public void escribirRegistro(Location ubicacion) {
        try {
            FileWriter archivoSalida = new FileWriter(rutaAlmacenamiento + nombreArchivo, true);
            PrintWriter printerArchivo = new PrintWriter(archivoSalida);
            printerArchivo.println(Traductor.ubicacionAString(ubicacion) + ','
                    + lectorBateria.obtenerNivelBateria());
            printerArchivo.flush();
            printerArchivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

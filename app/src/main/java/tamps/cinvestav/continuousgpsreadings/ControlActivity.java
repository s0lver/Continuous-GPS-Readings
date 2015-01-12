package tamps.cinvestav.continuousgpsreadings;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ControlActivity extends ActionBarActivity {
    private Presentador presentador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        this.presentador = new Presentador(this.getBaseContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presentador.detenerLecturas();
    }

    public void btnIniciarLecturas(View view) {
        EditText txtNombreArchivo = (EditText) findViewById(R.id.txtNombreArchivo);
        String nombreArchivo = txtNombreArchivo.getText().toString();

        if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
            Toast.makeText(this.getBaseContext(), "Debe escribir un nombre de archivo válido", Toast.LENGTH_SHORT).show();
            return;
        }
        presentador.iniciarLecturas(nombreArchivo);
    }

    public void btnDetenerLecturas(View view) {
        presentador.detenerLecturas();
    }
}

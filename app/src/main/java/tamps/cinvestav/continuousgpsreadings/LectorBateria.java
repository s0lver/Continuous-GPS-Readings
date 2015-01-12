package tamps.cinvestav.continuousgpsreadings;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class LectorBateria {
    private static final String STRING_BATT_LEVEL = "level";
    private static final String STRING_BATT_SCALE = "scale";
    private static final String STRING_BATT_STATUS = "status";

    private Context contexto;

    public LectorBateria(Context contexto) {
        this.contexto = contexto;
    }

    public String obtenerNivelBateria() {
        Intent batteryIntent = contexto.registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int rawlevel = batteryIntent.getIntExtra(STRING_BATT_LEVEL, -1);
        double scale = batteryIntent.getIntExtra(STRING_BATT_SCALE, -1);
        double level = -1;
        if (rawlevel >= 0 && scale > 0) {
            level = rawlevel / scale;
        }

        int status = batteryIntent.getIntExtra(STRING_BATT_STATUS, 0);

        StringBuilder sb = new StringBuilder();
        sb.append(level).append(',');

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                sb.append("charging");
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                sb.append("discharging");
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                sb.append("full");
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                sb.append("not-charging");
                break;
            default:
                sb.append("unknown");
        }

        return sb.toString();
    }
}

package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    LocationManager lm;
    TextView tvLatitude, tvLongitude;
    Button bnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        bnt = findViewById(R.id.button);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        bnt.setOnClickListener(v -> requestLocationUpdates());
    }

    public void requestLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
            return;
        }

        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Por favor, ative o GPS do seu dispositivo.", Toast.LENGTH_LONG).show();
            tvLatitude.setText("-");
            tvLongitude.setText("-");
            return;
        }

        try {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
            tvLatitude.setText("Aguardando...");
            tvLongitude.setText("Aguardando...");
            bnt.setText("Buscando...");
            bnt.setEnabled(false);
        } catch (SecurityException e) {
            Toast.makeText(this, "Erro de permissão.", Toast.LENGTH_SHORT).show();
        }

        Location lastLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            updateLocationUI(lastLocation);
        }
    }

    private void updateLocationUI(Location location) {
        tvLatitude.setText(Double.toString(location.getLatitude()));
        tvLongitude.setText(Double.toString(location.getLongitude()));
        Toast.makeText(this, "Localização capturada!", Toast.LENGTH_SHORT).show();
        bnt.setText("Obter Localização");
        bnt.setEnabled(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            requestLocationUpdates();
        } else {
            Toast.makeText(this, "Permissão de localização negada.", Toast.LENGTH_SHORT).show();
            tvLatitude.setText("-");
            tvLongitude.setText("-");
        }
    }



    @Override
    public void onLocationChanged(@NonNull Location location) {
        updateLocationUI(location);
        lm.removeUpdates(this);
        bnt.setText("Obter Localização");
        bnt.setEnabled(true);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        if (LocationManager.GPS_PROVIDER.equals(provider)) {
            Toast.makeText(this, "GPS desativado, não é possível obter localização.", Toast.LENGTH_LONG).show();
            tvLatitude.setText("-");
            tvLongitude.setText("-");
            bnt.setText("Obter Localização");
            bnt.setEnabled(true);
        }
    }
}
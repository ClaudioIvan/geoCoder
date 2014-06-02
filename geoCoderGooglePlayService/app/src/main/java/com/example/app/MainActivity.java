package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity implements GooglePlayServicesClient.OnConnectionFailedListener, GooglePlayServicesClient.ConnectionCallbacks {

    Button ubicacion;
    TextView pais,ciudad,direccion;

//LocationClient localizacion = new LocationClient(this,this,this);

// Location localizacionActual= localizacion.getLastLocation();


    Context contexto = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

       onStart();
         ubicacion=(Button)findViewById(R.id.ubicacion);
        pais=(TextView)findViewById(R.id.pais);
        ciudad=(TextView)findViewById(R.id.ciudad);
        direccion=(TextView)findViewById(R.id.direccion);

        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             geoCoder();
            }
        });




    }


  public void geoCoder(){
       double lat = 28.7087445;
       double lng = -106.103646;

       Geocoder myLocation = new Geocoder(contexto, Locale.getDefault());
       List<Address> myList= null;
       try {
          myList = myLocation.getFromLocation(lat, lng, 1);
       } catch (IOException e) {
           e.printStackTrace();
       }
       Address add = myList.get(0);
       String direc = add.getAddressLine(0);
       String Pais = add.getCountryName();
       String Ciudad= add.getLocality();

       direccion.setText(direc);
       pais.setText(Pais);
       ciudad.setText(Ciudad);}

    protected void onStart() {
        super.onStart();
        // Connect the client.
        //localizacion.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



}

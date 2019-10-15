package com.example.location;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText e;
    Button s;
    GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        e = (EditText)findViewById(R.id.edittext);
        s = (Button)findViewById(R.id.searchbutton) ;

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findonMap(v);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // gotoLocation(23.777176, 90.399452);


    }

    public void gotoLocation(double latitude , double longitude) {
        LatLng latlng = new LatLng(latitude, longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlng, 15);
        mMap.addMarker(new MarkerOptions().position(latlng));
        mMap.moveCamera(update);
    }

    public void findonMap(View v){
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> adlist = geocoder.getFromLocationName(e.getText().toString(),1);
            Address address = adlist.get(0);
            String locality = address.getLocality();
            double lat = address.getLatitude();
            double lon = address.getLongitude();
            gotoLocation(lat,lon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
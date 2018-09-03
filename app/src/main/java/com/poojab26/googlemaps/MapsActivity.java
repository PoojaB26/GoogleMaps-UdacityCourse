package com.poojab26.googlemaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap googleMap;
    boolean mapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_maps );

        Button btnMap = findViewById ( R.id.btnMap );
        btnMap.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    googleMap.setMapType ( GoogleMap.MAP_TYPE_NORMAL );
            }
        } );

        Button btnMapSatellite = findViewById ( R.id.btnSatellite );
        btnMapSatellite.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    googleMap.setMapType ( GoogleMap.MAP_TYPE_SATELLITE );
            }
        } );

        Button btnMapHybrid = findViewById ( R.id.btnHybrid );
        btnMapHybrid.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    googleMap.setMapType ( GoogleMap.MAP_TYPE_HYBRID );
            }
        } );

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        googleMap = map;
        LatLng newYork = new LatLng(40.7484,-73.9857);
        CameraPosition target = CameraPosition.builder().target ( newYork ).zoom ( 14 ).build();
        googleMap.moveCamera ( CameraUpdateFactory.newCameraPosition ( target ) );

    }
}
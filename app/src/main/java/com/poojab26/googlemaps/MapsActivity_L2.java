package com.poojab26.googlemaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity_L2 extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    boolean mapReady = false;

    Button btn1, btn2, btn3;

    static final CameraPosition NEWYORK = CameraPosition.builder()
            .target(new LatLng(40.784,-73.9857))
            .zoom(21)
            .bearing(0)
            .tilt(45)
            .build();

    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204,-122.3491))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();

    static final CameraPosition DUBLIN = CameraPosition.builder()
            .target(new LatLng(53.3478,-6.2597))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();


    static final CameraPosition TOKYO = CameraPosition.builder()
            .target(new LatLng(35.6895,139.6917))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_maps_2 );
        btn1 = findViewById ( R.id.btnDublin );
        btn2 = findViewById ( R.id.btnTokyo );
        btn3 = findViewById ( R.id.btnSeattle );

        btn1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    flyTo ( DUBLIN );
            }
        } );

        btn2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    flyTo ( TOKYO );
            }
        } );

        btn3.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    flyTo ( SEATTLE );
            }
        } );

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        googleMap = map;
        map.setMapType ( GoogleMap.MAP_TYPE_SATELLITE );
        flyTo ( NEWYORK );


    }

    private void flyTo(CameraPosition target){
        googleMap.animateCamera ( CameraUpdateFactory.newCameraPosition ( target ), 10000, null );

    }
}

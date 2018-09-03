package com.poojab26.googlemaps;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity_L4 extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady=false;

    MarkerOptions rentonMarker;
    LatLng renton = new LatLng(47.489805, -122.120502);

    MarkerOptions kirklandMarker;
    LatLng kirkland = new LatLng(47.7301986, -122.1768858);

    MarkerOptions everettMarker;
    LatLng everett = new LatLng(47.978748,-122.202001);



    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204,-122.2491))
            .zoom(10)
            .bearing(0)
            .tilt(45)
            .build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_3);

        rentonMarker = new MarkerOptions()
                .position(renton)
                .title("Renton")
                .icon ( bitmapDescriptorFromVector ( this, R.drawable.ic_marker ));

        kirklandMarker = new MarkerOptions()
                .position(kirkland)
                .title("Kirkland")
                .icon ( bitmapDescriptorFromVector ( this, R.drawable.ic_marker ));

        everettMarker = new MarkerOptions()
                .position(everett)
                .title("Everett")
                .icon ( bitmapDescriptorFromVector ( this, R.drawable.ic_marker ));


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }


    @Override
    public void onMapReady(GoogleMap map){
        //MapsInitializer.initialize(getApplicationContext());
        mapReady=true;
        m_map = map;
        m_map.addMarker(rentonMarker);
        m_map.addMarker(kirklandMarker);
        m_map.addMarker(everettMarker);

        flyTo(SEATTLE);

        m_map.addPolyline (
                new PolylineOptions ().geodesic ( true )
        .add ( renton )
        .add ( kirkland )
        .add ( everett ));
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {

        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(10, 5, vectorDrawable.getIntrinsicWidth() + 10,
                vectorDrawable.getIntrinsicHeight() + 5);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getMinimumWidth (), vectorDrawable.getMinimumHeight (),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    private void flyTo(CameraPosition target)
    {
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}
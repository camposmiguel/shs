package com.miguelcr.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    Marker sidneyMarker;
    LatLng sydney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerDragListener(this);

        // Add a marker in Sydney and move the camera
        sydney = new LatLng(37.387764,-5.981219);
        sidneyMarker = mMap.addMarker(
                new MarkerOptions()
                        .position(sydney)
                        .title("Marker")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                );

        sidneyMarker.setSnippet("Estás en Sydney");

        CircleOptions circleOptions = new CircleOptions()
                .center(sydney)
                .fillColor(ContextCompat.getColor(this,R.color.circle))
                .strokeColor(ContextCompat.getColor(this,R.color.colorAccent))
                .radius(1000); // In meters

        // Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        sidneyMarker.setPosition(latLng);
        sidneyMarker.setSnippet(latLng.latitude+","+latLng.longitude);
        sidneyMarker.showInfoWindow();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Log.i("*** LOC ***",marker.getPosition().latitude+","+marker.getPosition().longitude);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        marker.showInfoWindow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mapa_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ir_a_location:
                goToLocation();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void goToLocation() {
        sidneyMarker.setPosition(sydney);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));

    }
}

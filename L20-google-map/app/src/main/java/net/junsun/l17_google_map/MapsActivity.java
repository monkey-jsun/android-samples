package net.junsun.l17_google_map;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LatLng latLngOperatHouse, latLngGovHouse;
    Polyline connectLine=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_maps);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // set interesting locations
        latLngOperatHouse = new LatLng(-33.8570, 151.2148);
        latLngGovHouse = new LatLng(-33.8599,151.2150);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectLine != null) return;
                connectLine = mMap.addPolyline(new PolylineOptions()
                                .add(latLngOperatHouse, latLngGovHouse)
                                .width(2)
                                .color(Color.BLUE)
                );
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectLine == null) return;
                connectLine.remove();
                connectLine = null;
            }
        });
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

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(latLngOperatHouse).title("Sydney Opera House").snippet("Sydney, AU"));
        mMap.addMarker(new MarkerOptions().position(latLngGovHouse).title("Government House").snippet("Sydney, AU"));

        LatLng midway = new LatLng((latLngOperatHouse.latitude + latLngGovHouse.latitude)/2,
                (latLngOperatHouse.longitude+latLngGovHouse.longitude)/2);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midway, 17.0f));

        // set up UI control
        UiSettings ui = mMap.getUiSettings();
        ui.setAllGesturesEnabled(true);
        ui.setCompassEnabled(true);
        ui.setZoomControlsEnabled(true);
    }
}

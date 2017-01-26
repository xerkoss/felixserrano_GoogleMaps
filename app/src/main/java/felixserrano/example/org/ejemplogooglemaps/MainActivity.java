package felixserrano.example.org.ejemplogooglemaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements
        GoogleMap.OnMapClickListener, OnMapReadyCallback {

    private final LatLng BENIDORM = new LatLng(38.543685, -0.132227);
    private final LatLng PEREMARIA = new LatLng(38.553489, -0.121579);
    private GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment =(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapa = mapFragment.getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(BENIDORM, 13));
        mapa.setMyLocationEnabled(true);
        mapFragment.getMapAsync(this);
        mapa.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mapa.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.
                        defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }

    @Override

    public void onMapReady(GoogleMap googleMap) {
        mapa.addMarker(new MarkerOptions().position(PEREMARIA)
                .title("IES Pere Mª")
                .snippet("IES Pere Maria Orts i Bosch"));
    }
    void moverCamara(View v) {

        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(BENIDORM, 13));
    }

    void animarCamara(View v) {
        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(PEREMARIA,
                18));
    }

    void aMiPosicion(View v){
        if (mapa.getMyLocation() != null) {
            mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mapa.getMyLocation().getLatitude(),
                            mapa.getMyLocation().getLongitude()),20));
        }
    }
}

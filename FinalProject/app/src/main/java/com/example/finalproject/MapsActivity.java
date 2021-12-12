package com.example.finalproject;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ice = new LatLng(37.558304407779744, 126.99818561156079);

        //마커에 대한 옵션 설정
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ice);
        markerOptions.title("ICE.com 고객센터");
        markerOptions.snippet("ICE.com 고객센터 위치");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
        mMap.addMarker(markerOptions);

        mMap.getUiSettings().setZoomGesturesEnabled(true); //줌 기능 활성화
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ice,16));

    }
}
package com.example.mycanister;

import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    /* 지도 구현 */
    private GoogleMap mMap;
    private Animation fabOpen, fabClose;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab_maps, fab_gps, fab_findWay, fab_next;
    /* 지도 구현 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //protected였음
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /* 지도 구현 */
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab_maps = (FloatingActionButton) findViewById(R.id.fab_maps);
        fab_gps = (FloatingActionButton) findViewById(R.id.fab_gps);
        fab_findWay = (FloatingActionButton) findViewById(R.id.fab_findWay);
        fab_next = (FloatingActionButton) findViewById(R.id.fab_next);

        fab_maps.setOnClickListener(this);
        fab_gps.setOnClickListener(this);
        fab_findWay.setOnClickListener(this);
        fab_next.setOnClickListener(this);
        /* 지도 구현 */
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
        LatLng hansung = new LatLng(37.582528, 127.010846);
        mMap.addMarker(new MarkerOptions().position(hansung).title("Marker in Hansung Univ"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hansung, 15F));  //초기 카메라 배율

        //마커추가하기
        LatLng dental_yega = new LatLng(37.586339, 127.009859);
        mMap.addMarker(new MarkerOptions().position(dental_yega).title("Marker in 예가치과"));

        LatLng familyMedicine = new LatLng(37.586539, 127.009840);
        mMap.addMarker(new MarkerOptions().position(familyMedicine).title("Marker in 우리가정의학"));

        LatLng pharmacy_joil = new LatLng(37.586336, 127.009632);
        mMap.addMarker(new MarkerOptions().position(pharmacy_joil).title("Marker in 조일약국"));

        LatLng pharmacy_hyesung = new LatLng(37.585674, 127.008724);
        mMap.addMarker(new MarkerOptions().position(pharmacy_hyesung).title("Marker in 혜성약국"));

        LatLng pharmacy_boram = new LatLng(37.589337, 127.007717);
        mMap.addMarker(new MarkerOptions().position(pharmacy_boram).title("Marker in 보람약국"));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;

        switch (id) {
            case R.id.fab_maps:
                anim();
                Toast.makeText(this, "더보기", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_gps:
                anim();
                Toast.makeText(this, "자신의 현재위치 찾기", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_findWay:
                anim();
                Toast.makeText(this, "길찾기", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_next:
                intent = new Intent(this, FinalActivity.class);
                startActivity(intent);
                break;
        }
    }

    /*플로우버튼 숨기기 찾기 옵션함수*/
    public void anim() {

        if (isFabOpen) {
            fab_gps.startAnimation(fabClose);
            fab_findWay.startAnimation(fabClose);
            fab_next.startAnimation(fabClose);
            fab_gps.setClickable(false);
            fab_findWay.setClickable(false);
            fab_next.setClickable(false);
            isFabOpen = false;
        } else {
            fab_gps.startAnimation(fabOpen);
            fab_findWay.startAnimation(fabOpen);
            fab_next.startAnimation(fabOpen);
            fab_gps.setClickable(true);
            fab_findWay.setClickable(true);
            fab_next.setClickable(true);
            isFabOpen = true;
        }
    }

    /*액티비티가 정지될 때 삭제하기 -> 스크린에서 액티비티가 사라지면 삭제하기*/
    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

}

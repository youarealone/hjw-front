package com.example.hjw_front.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.hjw_front.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class LocationFragment extends Fragment implements
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        PlacesListener {

    private MapView gmap;

    public LocationFragment() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    private GoogleMap mMap;
    List<Marker> previous_marker = null;

    private Activity activity;
    private Button btnCurrent;
    private TextView tv_addr;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity)
            this.activity = (Activity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        tv_addr = view.findViewById(R.id.tv_addr_doro);
        btnCurrent = view.findViewById(R.id.btn_current);

        gmap = (MapView) view.findViewById(R.id.map);
        gmap.onCreate(savedInstanceState);

        gmap.getMapAsync(this);


        return view;
    }

    @Override
    public void onResume() {
        gmap.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gmap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        gmap.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(35.141233, 126.925594), 14);

        googleMap.animateCamera(cameraUpdate);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(35.141233, 126.925594))
                .title("루프리코리아"));

    }


    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                for (noman.googleplaces.Place place : places) {
//
//                    LatLng latLng
//                            = new LatLng(place.getLatitude()
//                            , place.getLongitude());
//
//                    String markerSnippet = getCurrentAddress(latLng);
//
//                    MarkerOptions markerOptions = new MarkerOptions();
//                    markerOptions.position(latLng);
//                    markerOptions.title(place.getName());
//                    markerOptions.snippet(markerSnippet);
//                    Marker item = mMap.addMarker(markerOptions);
//                    previous_marker.add(item);
//
//                }
//
//                //중복 마커 제거
//                HashSet<Marker> hashSet = new HashSet<Marker>();
//                hashSet.addAll(previous_marker);
//                previous_marker.clear();
//                previous_marker.addAll(hashSet);
//
//            }
//        });
    }

    @Override
    public void onPlacesFinished() {

    }

    public void showPlaceInformationHospital(LatLng location) {
        mMap.clear();//지도 클리어

        if (previous_marker != null)
            previous_marker.clear();//지역정보 마커 클리어

        new NRPlaces.Builder()
                .listener(LocationFragment.this)
                .key("AIzaSyBnKCVMvSkb2fk98klhkwbOTSQl_n2lYlk")
                .latlng(location.latitude, location.longitude)//현재 위치
                .radius(3000) // 3000 미터 내에서 검색
                .type(PlaceType.HOSPITAL) //병원
                .build()
                .execute();
    }

    public void showPlaceInformationPharmacy(LatLng location) {
        mMap.clear();//지도 클리어

        if (previous_marker != null)
            previous_marker.clear();//지역정보 마커 클리어

        new NRPlaces.Builder()
                .listener(LocationFragment.this)
                .key("AIzaSyBnKCVMvSkb2fk98klhkwbOTSQl_n2lYlk")
                .latlng(location.latitude, location.longitude)//현재 위치
                .radius(3000) // 3000미터 내에서 검색
                .type(PlaceType.PHARMACY) //약국
                .build()
                .execute();
    }
}

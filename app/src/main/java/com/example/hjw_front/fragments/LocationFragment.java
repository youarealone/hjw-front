package com.example.hjw_front.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.hjw_front.R;
import com.google.android.gms.maps.GoogleMap;
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

        return view;
    }


    // 구글 맵 사용
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);
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

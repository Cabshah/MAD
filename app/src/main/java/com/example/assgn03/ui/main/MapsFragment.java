package com.example.assgn03.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assgn03.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
         //   LatLng sydney = new LatLng(-34, 151);
          //  googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
         //   googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            getDtaFrmFireBase(googleMap);
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);
          /*  LatLng address = null;
            for (int i=0;i<placesList.size();i++){
                try{
                    String addree =placesList.get(i).getCity()+","+
                            placesList.get(i).getState()+","+
                            placesList.get(i).getCountry()+",";
                    address=getLatlangFromAdress(getActivity() , placesList.get(i)
                            .getCity());
                    googleMap.addMarker(new MarkerOptions().position(address).title(placesList.get(i).getCity()));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));
                }catch (Exception e){

                }
            }*/





        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        return view;
    }
    DatabaseReference databaseRefrence;
    FirebaseDatabase firebaseDatabase;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    List<Places> placesList = new ArrayList<>();
    Places places;
    void getDtaFrmFireBase(GoogleMap googleMap){
        firebaseDatabase  = FirebaseDatabase.getInstance("https://assgn03-default-rtdb.firebaseio.com/");
        databaseRefrence=firebaseDatabase.getReference("places-info");
        databaseRefrence.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Map<String,String> map = (Map<String, String>) snapshot.getValue();
                places = new Places();
                places.setCity(map.get("city"));
                places.setState(map.get("state"));
                places.setCountry(map.get("country"));
                placesList.add(places);

                LatLng address = null;
                for (int i=0;i<placesList.size();i++){
                    try{
                        String addree =placesList.get(i).getCity()+","+
                                placesList.get(i).getState()+","+
                                placesList.get(i).getCountry()+",";
                        address=getLatlangFromAdress(getActivity() , placesList.get(i)
                                .getCity());
                        googleMap.addMarker(new MarkerOptions().position(address).title(placesList.get(i).getCity()));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));
                    }catch (Exception e){

                    }
                }

            }

            //      city:
            //        "Pakistan "


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
    LatLng getLatlangFromAdress(Context context , String strAddress){
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;
        LatLng latLng = null;
        try{
            addresses=geocoder.getFromLocationName(strAddress,2);
            if(addresses==null){
                return  null;
            }
            Address loc = addresses.get(0);
            latLng= new  LatLng(loc.getLatitude(),loc.getLongitude());

        }catch (Exception e){
          //  return  latLng;
        }
        return latLng;
    }
}
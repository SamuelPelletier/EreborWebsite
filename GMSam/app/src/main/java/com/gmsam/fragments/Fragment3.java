package com.gmsam.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gmsam.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Fragment3 extends Fragment{
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    MapView mMapView;
    private GoogleMap googleMap;
    public Fragment3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3, container, false);
        //obtenir l'info du pseudo
        String info_pseudo;
        info_pseudo = "Pseudo du test";//à remplir...
        final TextView pseudoView = (TextView) view.findViewById(R.id.pseudo);
        pseudoView.setText(info_pseudo);

        //obtenir l'info du numero
        final String info_numero;
        info_numero = "0659460215";//à remplir...
        final TextView numeroView = (TextView) view.findViewById(R.id.numero);
        numeroView.setText(info_numero);
        numeroView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                appel(info_numero);
            }
        });

        final Button proposer = (Button) view.findViewById(R.id.proposer);
        final Button trouver = (Button) view.findViewById(R.id.trouver);


        proposer.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                trouver.setBackgroundColor(Color.rgb(36,167,185));
                proposer.setBackgroundColor(Color.rgb(38,56,74));
                DialogFragment newFragment = CreateSam.newInstance();
                newFragment.show(getFragmentManager(), "dialog");
            }
        });

        trouver.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                proposer.setBackgroundColor(Color.rgb(36,167,185));
                trouver.setBackgroundColor(Color.rgb(38,56,74));
            }
        });
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(45.65, 5.8667);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    public void faitAppel(String tel)
    {
        //Toast.makeText(SOSActivity.this,"17", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try
        {
            startActivity(intent);
        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(),"erreur", Toast.LENGTH_SHORT).show();
        }
    }

    public void appel(String tel) {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
                Toast.makeText(getActivity(), "Permission invalide.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            faitAppel(tel);
        }
    }
}
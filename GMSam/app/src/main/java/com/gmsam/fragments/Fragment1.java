package com.gmsam.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmsam.R;

public class Fragment1 extends Fragment {

    public Fragment1() {
    }
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment1, container, false);

        final ImageView appel17 = (ImageView)view.findViewById(R.id.image17);
        final ImageView appel18 = (ImageView)view.findViewById(R.id.image18);
        final ImageView appel112 = (ImageView)view.findViewById(R.id.image112);
        appel17.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                appel("0659460215");
            }
        });
        appel18.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                appel("0659460215");
            }
        });
        appel112.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                appel("0659460215");
            }
        });

        String info_adresse;

        //obtenir l'info d'adresse
        info_adresse = "test\nadresse";//à remplir

        final TextView adresseView = (TextView)view.findViewById(R.id.adresse);
        adresseView.setText(info_adresse);
        return view;
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


    public void appel(String tel)
    {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE))
            {
                Toast.makeText(getActivity(), "Permission invalide.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
            else
            {
                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }
        else
        {
            faitAppel(tel);

        }

    }

}
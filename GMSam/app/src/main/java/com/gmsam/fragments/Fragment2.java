package com.gmsam.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gmsam.R;

public class Fragment2 extends Fragment {

    public Fragment2() {
    }
    private String type_accident_choisi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment2, container, false);
        Button signaler = (Button)view.findViewById(R.id.signler);
        final EditText motcle = (EditText)view.findViewById(R.id.editText) ;

        final ImageView accident = (ImageView)view.findViewById(R.id.accident);
        final ImageView naturel = (ImageView)view.findViewById(R.id.naturel);
        final ImageView travaux = (ImageView)view.findViewById(R.id.travaux);

        accident.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                type_accident_choisi = "accident";

                //tester
                Toast.makeText(getActivity(),type_accident_choisi, Toast.LENGTH_SHORT).show();
                //à remplir...
            }
        });

        naturel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                type_accident_choisi = "naturel";

                //tester
                Toast.makeText(getActivity(),type_accident_choisi, Toast.LENGTH_SHORT).show();
                //à remplir...
            }
        });

        travaux.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                type_accident_choisi = "travaux";

                //tester
                Toast.makeText(getActivity(),type_accident_choisi, Toast.LENGTH_SHORT).show();
                //à remplir...
            }
        });

        signaler.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String data = motcle.getText().toString();
                Toast.makeText(getActivity(),data, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
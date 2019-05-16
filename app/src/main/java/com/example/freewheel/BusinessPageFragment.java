package com.example.freewheel;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BusinessPageFragment extends Fragment {

    public static Business businessToDisplay = null;

    private ImageView logo;
    private TextView name, address;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.business_page, container, false);

        getAttributesIds(view);

        if(businessToDisplay != null)
            handleAttribute();

        return view;
    }


    private void getAttributesIds(View view) {
        //name = (TextView) view.findViewById(R.id.name);


    }

    private void handleAttribute() {
        //name.setText(businessToDisplay.getTitle());

    }




}

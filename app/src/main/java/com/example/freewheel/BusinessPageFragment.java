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

import java.util.HashMap;

public class BusinessPageFragment extends Fragment {

    public static GoogleInfo businessToDisplay = null;

    private ImageView logo;
    private TextView name, address, stairs, doors;
    private String id;
    private View view;
    ImageView standard, wheel, parking, elav;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.business_page, container, false);

        getAttributesIds(view);
        name = view.findViewById(R.id.place_name);
        address = view.findViewById(R.id.place_address);
        logo = view.findViewById(R.id.place_photo);
        standard = view.findViewById(R.id.standard);
        wheel = view.findViewById(R.id.wheelchair);
        parking = view.findViewById(R.id.elevator);
        elav  = view.findViewById(R.id.elevator);
        stairs = view.findViewById(R.id.stairs);
        doors = view.findViewById(R.id.doors);
        if(businessToDisplay != null) {
            handleAttribute();
            name.setText(businessToDisplay.getName());
            address.setText(businessToDisplay.getAddress());
            logo.setImageBitmap(businessToDisplay.getImage());
            id = businessToDisplay.getId();

            if (ServerApi.getInstance().exists(id)) {

                HashMap<String, Object> atts = ServerApi.getInstance().getAccessibilities(id);

                try {
                    wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setAccessAtts(atts);
            }

        }

        return view;
    }


    private void setAccessAtts(HashMap<String, Object> atts){
        if (atts != null) {
            if ((Boolean) atts.get("Standard")) {
                standard.setImageResource(R.drawable.v);
            } else {
                standard.setImageResource(R.drawable.x);
            }
            if ((Boolean) atts.get("WheelChair accessibility")) {
                wheel.setImageResource(R.drawable.v);
            } else {
                wheel.setImageResource(R.drawable.x);
            }

            if ((Boolean) atts.get("Close parking")) {
                parking.setImageResource(R.drawable.v);
            } else {
                parking.setImageResource(R.drawable.x);
            }

            if ((Boolean) atts.get("Elevator")) {
                elav.setImageResource(R.drawable.v);
            } else {
                elav.setImageResource(R.drawable.x);
            }
            doors.setText((String) atts.get("Doors width") + "cm");
            stairs.setText((String) atts.get("Number of Stairs"));

        }


    }

    private void getAttributesIds(View view) {
        //name = (TextView) view.findViewById(R.id.name);


    }

    private void handleAttribute() {
        //name.setText(businessToDisplay.getTitle());

    }




}

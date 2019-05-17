package com.example.freewheel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        loadFragment(new BusinessList());
        init();
    }

    public void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment, fragment)
                    .commit();
        }
    }

    private void init(){
        HashMap<String, Object> toSend = new HashMap<>();
        toSend.put("Standard", true);
        toSend.put("WheelChair accessibility", true);
        toSend.put("Close parking", false);
        toSend.put("Elevator", false);
        toSend.put("Doors width", 80);
        toSend.put("Number of Stairs", 30);

        String id = "ChIJRXeieDsnAxURf3ycN2-QRDA";

        /* givat ram hebrew university id*/
        ServerApi.getInstance().createLocation(new Business(id));
        ServerApi.getInstance().setAccess(id, toSend);


        HashMap<String, Object> toSend2 = new HashMap<>();
        toSend2.put("Standard", false);
        toSend2.put("WheelChair accessibility", true);
        toSend2.put("Close parking", false);
        toSend2.put("Elevator", false);
        toSend2.put("Doors width", 100);
        toSend2.put("Number of Stairs", 3);

        String id2 = "ChIJeUOgtI_VHBURwnW3y_Rau8Q";

        /* givat ram hebrew university id*/
        ServerApi.getInstance().createLocation(new Business(id2));
        ServerApi.getInstance().setAccess(id2, toSend2);


    }

}

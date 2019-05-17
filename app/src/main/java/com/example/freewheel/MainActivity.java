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


        /*for (String arg : toSend.keySet()){
            ServerApi.getInstance().setAccess(id,
                    arg, toSend.get(arg));
        }*/


    }

}

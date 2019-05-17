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
        toSend.put("Close parking", true);
        toSend.put("Elevator", true);
        toSend.put("Doors width", 80);
        toSend.put("Number of Stairs", 30);

        /* givat ram hebrew university id*/
        ServerApi.getInstance().createLocation(new Business("ChIJS_zBNNbXAhUR_A-4iwuWBP0"));
        ServerApi.getInstance().setMulAccess("ChIJS_zBNNbXAhUR_A-4iwuWBP0", toSend);


    }

}

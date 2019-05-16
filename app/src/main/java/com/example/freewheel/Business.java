package com.example.freewheel;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

//import com.google.android.gms.maps.model.LatLng;
import com.google.type.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Business {

    private String id;

    private int numOfRaters, sumOfRates;
    private float rate;

    private ArrayList<Comment> comments;
    private HashMap<String, Object> access;


    Business(String id){
        this.numOfRaters = 0;
        this.sumOfRates = 0;
        this.rate = 0;
        this.id = id;
        access = new HashMap<>();
        //todo - add values to hashmap
    }


    void addRate(int rate){
        if (0 > rate || rate > 5){
            return;
        }
        numOfRaters++;
        sumOfRates += rate;
        rate = sumOfRates / numOfRaters;
    }

    public HashMap<String, Object> getAccess() {
        return access;
    }

    public int getNumOfRaters() {
        return numOfRaters;
    }

    public int getSumOfRates() {
        return sumOfRates;
    }

    public float getRate() {
        return rate;
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<Comment> getComments(){
        return this.comments;
    }
}
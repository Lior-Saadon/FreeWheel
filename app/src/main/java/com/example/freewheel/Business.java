package com.example.freewheel;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Scanner;

public class Business {

    private String name, id, address;
    private Boolean accessibilityStandard;       //TavTeken Negishut
    private LatLng location;
    private int logo;
    private ArrayList<Comment> comments;
    private float crossingWidth;
    private int height;
    private float incline;              //shipua
    private int numOfRaters, sumOfRates;
    private float rate;


    Business(String name, String adrress, LatLng location, int logo){
        this.name = name;
        this.address = adrress;
        this.location = location;
        this.logo = logo;
        this.numOfRaters = 0;
        this.sumOfRates = 0;
        this.rate = 0;
    }

    void addRate(int rate){
        if (0 > rate || rate > 5){
            return;
        }
        numOfRaters++;
        sumOfRates += rate;
        rate = sumOfRates / numOfRaters;
    }

    public void setAccessibilityStandard(boolean accessibilityStandard) {
        Scanner input = new Scanner(System.in);
        System.out.println("Is this business has a standard mark?");
        System.out.println("0 = Yes , img1 = No, img2 = I don't know");

        int answer = input.nextInt();
        this.accessibilityStandard = accessibilityStandard;
    }

    public void setCrossingWidth(float crossingWidth) {
        this.crossingWidth = crossingWidth;
    }

    public void setHight(int hight) {
        this.height = hight;
    }

    public String getName() {
        return name;
    }

    public String getAdrress() {
        return address;
    }

    public Boolean getAccessibilityStandard() {
        return accessibilityStandard;
    }

    public LatLng getLocation() {
        return location;
    }

    public int getLogo() {
        return logo;
    }

    public float getCrossingWidth() {
        return crossingWidth;
    }

    public int getHight() {
        return height;
    }

    public float getIncline() {
        return incline;
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
}

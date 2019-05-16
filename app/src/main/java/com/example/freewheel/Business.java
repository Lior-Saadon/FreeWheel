package com.example.freewheel;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Scanner;

public class Business {

    private String name, adrress;
    private Boolean standardMark;
    private LatLng location;
    private ImageView logo;
    //private Comment[] commengts;
    private float crossingWidth;
    private int hight;
    private float decline;
    private int numOfRaters, sumOfRates;
    private float rate;



    Business(String name, String adrress, LatLng location, ImageView logo){
        this.name = name;
        this.adrress = adrress;
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

    public void setStandardMark(boolean standardMark) {
        Scanner input = new Scanner(System.in);
        System.out.println("Is this business has a standard mark?");
        System.out.println("0 = Yes , 1 = No, 2 = I don't know");

        int answer = input.nextInt();
        this.standardMark = standardMark;
    }

    public void setCrossingWidth(float crossingWidth) {
        this.crossingWidth = crossingWidth;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public String getName() {
        return name;
    }

    public String getAdrress() {
        return adrress;
    }

    public Boolean getStandardMark() {
        return standardMark;
    }

    public LatLng getLocation() {
        return location;
    }

    public ImageView getLogo() {
        return logo;
    }

    public float getCrossingWidth() {
        return crossingWidth;
    }

    public int getHight() {
        return hight;
    }

    public float getDecline() {
        return decline;
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

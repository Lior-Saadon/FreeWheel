package com.example.freewheel;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import static android.support.constraint.Constraints.TAG;

/**
 * easy API for server
 */
public class ServerApi {

    private FirebaseFirestore db; // ref. to firebase db
    private static ServerApi instance = new ServerApi();


    /**
     * constructor
     */
    private ServerApi(){
        db = FirebaseFirestore.getInstance();
    }

    /**
     * get singleton instance of this class
     * @return instance of this server
     */
    public static ServerApi getInstance(){
        return instance;
    }

    public void createLocation(Business bus){
        HashMap<String, Object> vals = new HashMap<>();

        vals.put("id", bus.getId());
        vals.put("num_of_rates", bus.getNumOfRaters());
        vals.put("sum_of_rates", bus.getSumOfRates());
        vals.put("comments", bus.getComments());
        vals.put("accessibilities", bus.getAccess());

        db.collection("businesses").add(vals).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
        });



    }


}

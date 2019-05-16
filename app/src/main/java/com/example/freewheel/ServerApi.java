package com.example.freewheel;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;

import static android.support.constraint.Constraints.TAG;

/**
 * easy API for server
 */
public class ServerApi {
    private final static String LOC_LIB_NAME = "locations";
    private FirebaseFirestore db; // ref. to firebase db
    private final static ServerApi instance = new ServerApi();


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

        db.collection(LOC_LIB_NAME).document(bus.getId()).set(bus);
    }


    public void rateBus(String busid, final int rating){

        DocumentReference busRef = db.collection(LOC_LIB_NAME).document(busid);

        busRef.update("numOfRaters", FieldValue.increment(1)); // raise by one
        busRef.update("sumOfRates", FieldValue.increment(rating)); // raise by one

    }


    public void addComment(String busid, Comment comment){

        DocumentReference busRef = db.collection(LOC_LIB_NAME).document(busid);
        ArrayList<Comment> com = new ArrayList<>();
        Comment[] arr = {comment};
        com.add(comment);
        busRef.update("comments", FieldValue.arrayUnion(comment));

    }


//    public void setAccess(String busid, String key, Object val){
//
//        DocumentReference busRef = db.collection(LOC_LIB_NAME).document(busid);
//        busRef.update("access", Map)
//        busRef.update("comments", FieldValue.arrayUnion(comment));
//
//    }
//
//    public void getBus(String busid){
//
//        DocumentReference docRef = db.collection(LOC_LIB_NAME).document(busid);
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
//
//
//
//        return bus;
//    }





}

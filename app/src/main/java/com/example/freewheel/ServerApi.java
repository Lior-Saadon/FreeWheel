package com.example.freewheel;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

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
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;

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


    public void setAccess(String busid, final String key, final Object val){

        final DocumentReference busRef = db.collection(LOC_LIB_NAME).document(busid);

        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(busRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                HashMap<String, Object> newMap = (HashMap<String, Object>)snapshot.get("access");
                newMap.put(key, val);
                transaction.update(busRef, "access", newMap);

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Transaction success!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Transaction failure.", e);
                    }
                });

    }


    public void setMulAccess(String busid, final HashMap<String, Object> map){

        final DocumentReference busRef = db.collection(LOC_LIB_NAME).document(busid);

        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(busRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                HashMap<String, Object> newMap = (HashMap<String, Object>)snapshot.get("access");
                newMap.putAll(map);
                transaction.update(busRef, "access", newMap);

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Transaction success!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Transaction failure.", e);
                    }
                });

    }

    public HashMap<String, Object> getAccessibilities(String busid){
        final DocumentReference busRef = db.collection(LOC_LIB_NAME).document(busid);

        final HashMap<String, Object> newMap = new HashMap<>();
        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(busRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                newMap.putAll((HashMap<String, Object>)snapshot.get("access"));


                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Transaction success!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Transaction failure.", e);
                    }
                });

        return newMap;
    }

    public ArrayList<Comment> getComments(String busid){
        final DocumentReference busRef = db.collection(LOC_LIB_NAME).document(busid);

        final ArrayList<Comment> arr = new ArrayList<>();
        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(busRef);

                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                arr.addAll(( ArrayList<Comment>)snapshot.get("comments"));


                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Transaction success!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Transaction failure.", e);
                    }
                });

        return arr;
    }

    public boolean exists(String bid){
        final Boolean[] res = new Boolean[1];
        db.collection(LOC_LIB_NAME)
                .document(bid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                res[0] = true;
                            } else {
                                res[0] = false;
                            }
                        } else {
                            res[0] = false;
                        }
                    }
                });


        return res[0];
    }

}

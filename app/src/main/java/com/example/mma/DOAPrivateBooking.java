package com.example.mma;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DOAPrivateBooking {

    private DatabaseReference databaseReference;
    public DOAPrivateBooking()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(PrivateBooking.class.getSimpleName());
    }

    public Task<Void> add(PrivateBooking pb){
        return databaseReference.push().setValue(pb);
    }

    public Task<Void> update(String key, HashMap<String,Object>hashMap){
       return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }






///
}

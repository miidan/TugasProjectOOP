package com.pro.pcmappnew.dao;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pro.pcmappnew.utils.User;

import java.util.HashMap;

public class DAOupdate {
    private DatabaseReference databaseReference;

    public DAOupdate(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
}

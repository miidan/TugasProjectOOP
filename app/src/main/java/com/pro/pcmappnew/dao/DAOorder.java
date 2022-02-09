package com.pro.pcmappnew.dao;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pro.pcmappnew.utils.Order;

public class DAOorder {
    private DatabaseReference databaseReference;
    public DAOorder(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Order.class.getSimpleName());
    }
    public Task<Void> add(Order order){

        return databaseReference.push().setValue(order);
    }
}

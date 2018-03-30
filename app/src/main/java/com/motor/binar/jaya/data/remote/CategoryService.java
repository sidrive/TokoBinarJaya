package com.motor.binar.jaya.data.remote;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.motor.binar.jaya.data.model.Motor;

/**
 * Created by ikun on 12/01/18.
 */

public class CategoryService {
    DatabaseReference databaseRef;
    public CategoryService(){
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getMerk(){
        return databaseRef.child("merk");
    }

    public DatabaseReference getType(String id){
        return databaseRef.child("type").child(id);
    }

    public DatabaseReference getSeri(String id){
        return databaseRef.child("seri").child(id);
    }

    public Task<Void> saveMotor(Motor motor){
        return databaseRef.child("motors").child(motor.getUserid()).child(motor.getIdmotor()).setValue(motor);
    }

    public DatabaseReference getMotor(String id){
        return databaseRef.child("motors").child(id);
    }
}
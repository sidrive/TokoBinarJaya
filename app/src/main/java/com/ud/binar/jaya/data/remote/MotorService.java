package com.ud.binar.jaya.data.remote;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ud.binar.jaya.data.model.Barang;

/**
 * Created by ikun on 17/01/18.
 */

public class MotorService {
    DatabaseReference databaseRef;

    public MotorService(){
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getMotors(){
        return databaseRef.child("motors");
    }

    public DatabaseReference getDetailsMotor (String id){
        return databaseRef.child("motors").child(id);
    }

    public Task<Void> saveMotor(Barang barang){
        return databaseRef.child("motors").child(barang.getIdbarang()).setValue(barang);
    }
}

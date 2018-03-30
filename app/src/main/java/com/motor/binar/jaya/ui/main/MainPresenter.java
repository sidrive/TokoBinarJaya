package com.motor.binar.jaya.ui.main;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.motor.binar.jaya.base.BasePresenter;
import com.motor.binar.jaya.data.model.Motor;
import com.motor.binar.jaya.data.remote.CategoryService;
import com.motor.binar.jaya.data.remote.UserService;
import com.motor.binar.jaya.data.remote.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikun on 02/01/18.
 */

public class MainPresenter implements BasePresenter {
    MainAct activity;
    UserService userService;
    User user;
    CategoryService categoryService;

    public MainPresenter(MainAct activity, UserService userService, User user, CategoryService categoryService){
        this.activity = activity;
        this.userService = userService;
        this.user = user;
        this.categoryService = categoryService;
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {

    }

    public void updateFCMToken(String uid, String token){
        userService.updateUserToken(uid, token);
    }

    public void getMotor(User user){
        categoryService.getMotor(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Motor> listMotor = new ArrayList<Motor>();
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Motor motor = postSnapshot.getValue(Motor.class);
                    Log.e("MainPresenter", "onDataChange: " + dataSnapshot.getChildren());
                    listMotor.add(motor);
//                    Log.e("MainPresenter", "onDataChange: " + listMotor);
                }

                activity.initListMotor(listMotor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateMotor(Motor motor){
        categoryService.saveMotor(motor).addOnCompleteListener(task -> activity.succesSaveMotor()).addOnFailureListener(e -> {
            activity.showLoading(false);
            Toast.makeText(activity, "Gagal menyimpan motor", Toast.LENGTH_SHORT).show();
        });

    }
}

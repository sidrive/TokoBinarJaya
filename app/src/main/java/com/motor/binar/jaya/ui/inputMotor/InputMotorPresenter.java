package com.motor.binar.jaya.ui.inputMotor;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.motor.binar.jaya.base.BasePresenter;
import com.motor.binar.jaya.data.model.Category;
import com.motor.binar.jaya.data.model.Motor;
import com.motor.binar.jaya.data.remote.CategoryService;
import com.motor.binar.jaya.data.remote.UserService;
import com.motor.binar.jaya.data.remote.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikun on 11/01/18.
 */

public class InputMotorPresenter implements BasePresenter {
    InputMotorActivity activity;
    UserService userService;
    User user;
    CategoryService categoryService;
    Motor motor;

    public InputMotorPresenter(InputMotorActivity activity, UserService userService, User user, CategoryService categoryService, Motor motor){
        this.activity = activity;
        this.userService = userService;
        this.user = user;
        this.categoryService = categoryService;
        this.motor = motor;
    }
    @Override
    public void subscribe() {
        if (user != null){
            getCategories();
        }

    }

    @Override
    public void unsubscribe() {

    }

    public void getCategories(){
        categoryService.getMerk().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Category> listCategories = new ArrayList<Category>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    listCategories.add(category);
                }

                activity.initMerk(listCategories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getSubCategories(String id){
        categoryService.getType(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Category> listCategories = new ArrayList<Category>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    listCategories.add(category);
                }

                activity.initType(listCategories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getLevels(String id){
        categoryService.getSeri(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Category> listCategories = new ArrayList<Category>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    listCategories.add(category);
                }

                activity.initSeri(listCategories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void savemotor(Motor motor){
        Log.e("InputMotor","idmotor "+motor.getIdmotor());
        categoryService.saveMotor(motor).addOnCompleteListener(task -> activity.succesSaveMotor()).addOnFailureListener(e -> {
            activity.showLoading(false);
            Toast.makeText(activity, "Gagal menyimpan motor", Toast.LENGTH_SHORT).show();
        });
    }
}

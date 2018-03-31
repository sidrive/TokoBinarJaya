package com.ud.binar.jaya.ui.main;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ud.binar.jaya.base.BasePresenter;
import com.ud.binar.jaya.data.model.Barang;
import com.ud.binar.jaya.data.remote.CategoryService;
import com.ud.binar.jaya.data.remote.UserService;
import com.ud.binar.jaya.data.remote.model.User;

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
                List<Barang> listBarang = new ArrayList<Barang>();
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.e("MainPresenter", "onDataChange: " + dataSnapshot.getChildren());
                    Barang barang = postSnapshot.getValue(Barang.class);

                    listBarang.add(barang);
//                    Log.e("MainPresenter", "onDataChange: " + listBarang);
                }

                activity.initListMotor(listBarang);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateMotor(Barang barang){
        categoryService.saveBarang(barang).addOnCompleteListener(task -> activity.succesSaveMotor()).addOnFailureListener(e -> {
            activity.showLoading(false);
            Toast.makeText(activity, "Gagal menyimpan ud", Toast.LENGTH_SHORT).show();
        });

    }
}

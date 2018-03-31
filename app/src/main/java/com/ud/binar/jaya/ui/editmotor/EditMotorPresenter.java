package com.ud.binar.jaya.ui.editmotor;

import android.util.Log;
import android.widget.Toast;

import com.ud.binar.jaya.base.BasePresenter;
import com.ud.binar.jaya.data.model.Barang;
import com.ud.binar.jaya.data.remote.CategoryService;
import com.ud.binar.jaya.data.remote.UserService;
import com.ud.binar.jaya.data.remote.model.User;

public class EditMotorPresenter implements BasePresenter {
    EditMotorActivity activity;
    UserService userService;
    User user;
    CategoryService categoryService;
    Barang barang;

    public EditMotorPresenter(EditMotorActivity activity,UserService userService, Barang barang){
        this.activity = activity;
        this.userService = userService;
        this.user = user;
        this.categoryService = categoryService;
        this.barang = barang;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void saveBarangs(Barang barang){
        Log.e("InputMotor","idmotor "+ barang.getIdbarang());
        categoryService.saveBarang(barang).addOnCompleteListener(task -> activity.succesSaveMotor()).addOnFailureListener(e -> {
            activity.showLoading(false);
            Toast.makeText(activity, "Gagal menyimpan ud", Toast.LENGTH_SHORT).show();
        });
    }


}

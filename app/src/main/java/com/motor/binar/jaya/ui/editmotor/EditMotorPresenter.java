package com.motor.binar.jaya.ui.editmotor;

import com.motor.binar.jaya.base.BasePresenter;
import com.motor.binar.jaya.data.model.Motor;
import com.motor.binar.jaya.data.remote.CategoryService;
import com.motor.binar.jaya.data.remote.UserService;
import com.motor.binar.jaya.data.remote.model.User;

public class EditMotorPresenter implements BasePresenter {
    EditMotorActivity activity;
    UserService userService;
    User user;
    CategoryService categoryService;
    Motor motor;

    public EditMotorPresenter(EditMotorActivity activity,UserService userService, Motor motor){
        this.activity = activity;
        this.userService = userService;
        this.user = user;
        this.categoryService = categoryService;
        this.motor = motor;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}

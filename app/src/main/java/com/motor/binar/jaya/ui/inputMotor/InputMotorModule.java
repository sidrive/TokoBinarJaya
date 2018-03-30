package com.motor.binar.jaya.ui.inputMotor;

import com.motor.binar.jaya.base.annotation.ActivityScope;
import com.motor.binar.jaya.data.model.Motor;
import com.motor.binar.jaya.data.remote.CategoryService;
import com.motor.binar.jaya.data.remote.UserService;
import com.motor.binar.jaya.data.remote.model.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ikun on 11/01/18.
 */
@Module
public class InputMotorModule {
    InputMotorActivity activity;

    public InputMotorModule(InputMotorActivity activity){
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    InputMotorActivity provideInputMotorActiviy(){return activity;}

    @ActivityScope
    @Provides
    InputMotorPresenter provideInputMotorPresenter(UserService userService, User user, CategoryService categoryService, Motor motor){
        return new InputMotorPresenter(activity,userService,user,categoryService,motor);
    }
}

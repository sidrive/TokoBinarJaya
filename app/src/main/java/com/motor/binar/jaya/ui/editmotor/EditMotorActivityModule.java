package com.motor.binar.jaya.ui.editmotor;

import com.motor.binar.jaya.base.annotation.ActivityScope;
import com.motor.binar.jaya.data.model.Motor;
import com.motor.binar.jaya.data.remote.UserService;

import dagger.Module;
import dagger.Provides;

@Module
public class EditMotorActivityModule {
    EditMotorActivity activity;

    public EditMotorActivityModule(EditMotorActivity activity){
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    EditMotorActivity provideEditMotorActivity(){return activity;}

    @ActivityScope
    @Provides
    EditMotorPresenter provideEditMotorPresenter(UserService userService,Motor motor){
        return new EditMotorPresenter(activity,userService,motor);
    }
}

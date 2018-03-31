package com.ud.binar.jaya.data.editMotor;

import com.ud.binar.jaya.base.annotation.MainScope;
import com.ud.binar.jaya.ui.editmotor.EditMotorActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class EditMotorModule {
    EditMotorActivity activity;

    public EditMotorModule(EditMotorActivity activity){
        this.activity = activity;
    }

    @Provides
    @MainScope
    EditMotorActivity provideEditMotor(){return activity;}
}

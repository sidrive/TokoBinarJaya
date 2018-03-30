package com.motor.binar.jaya.data.inputMotor;

import com.motor.binar.jaya.base.annotation.MainScope;
import com.motor.binar.jaya.ui.inputMotor.InputMotorActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ikun on 11/01/18.
 */

@Module
public class InputmotorModule {
    InputMotorActivity activity;

    public InputmotorModule(InputMotorActivity activity){
        this.activity = activity;
    }

    @Provides
    @MainScope
    InputMotorActivity provideInputMotor(){
        return activity;
    }
}

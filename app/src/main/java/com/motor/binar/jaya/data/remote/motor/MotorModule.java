package com.motor.binar.jaya.data.remote.motor;

import com.motor.binar.jaya.base.annotation.UserScope;
import com.motor.binar.jaya.data.model.Motor;

import dagger.Module;
import dagger.Provides;

@Module
public class MotorModule {
    Motor motor;

    public MotorModule(Motor motor) {
        this.motor = motor;
    }

    @Provides
    @UserScope
    Motor provideMotor() {
        return motor;
    }

//
//
//    @Provides
//    @UserScope
//    Motor provideMotor(){
//        return new Motor();
//    }
}

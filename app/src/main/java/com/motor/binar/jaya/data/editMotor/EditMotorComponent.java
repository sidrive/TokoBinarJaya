package com.motor.binar.jaya.data.editMotor;

import com.motor.binar.jaya.base.annotation.MainScope;

import dagger.Subcomponent;

@MainScope
@Subcomponent(
        modules = {
                EditMotorModule.class
        }
)
public interface EditMotorComponent {
}

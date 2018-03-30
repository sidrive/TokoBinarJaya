package com.motor.binar.jaya.data.remote.motor;

import com.motor.binar.jaya.base.annotation.UserScope;
import com.motor.binar.jaya.ui.editmotor.EditMotorActivityComponent;
import com.motor.binar.jaya.ui.editmotor.EditMotorActivityModule;

import dagger.Subcomponent;

@UserScope
@Subcomponent(
        modules = {
                MotorModule.class
        }
)
public interface MotorComponent {
    EditMotorActivityComponent plus(EditMotorActivityModule editMotorActivityModule);
}

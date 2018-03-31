package com.ud.binar.jaya.data.remote.motor;

import com.ud.binar.jaya.base.annotation.UserScope;
import com.ud.binar.jaya.ui.editmotor.EditMotorActivityComponent;
import com.ud.binar.jaya.ui.editmotor.EditMotorActivityModule;

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

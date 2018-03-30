package com.motor.binar.jaya.ui.editmotor;

import com.motor.binar.jaya.base.annotation.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {
                EditMotorActivityModule.class
        }
)
public interface EditMotorActivityComponent {
    EditMotorActivity inject(EditMotorActivity activity);
}

package com.ud.binar.jaya.ui.editmotor;

import com.ud.binar.jaya.base.annotation.ActivityScope;

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

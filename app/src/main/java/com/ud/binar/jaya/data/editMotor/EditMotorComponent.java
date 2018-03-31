package com.ud.binar.jaya.data.editMotor;

import com.ud.binar.jaya.base.annotation.MainScope;

import dagger.Subcomponent;

@MainScope
@Subcomponent(
        modules = {
                EditMotorModule.class
        }
)
public interface EditMotorComponent {
}

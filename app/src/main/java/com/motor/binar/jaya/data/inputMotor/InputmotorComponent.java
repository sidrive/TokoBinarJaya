package com.motor.binar.jaya.data.inputMotor;

import com.motor.binar.jaya.base.annotation.MainScope;

import dagger.Subcomponent;

/**
 * Created by ikun on 11/01/18.
 */

@MainScope
@Subcomponent(
        modules = {
                InputmotorModule.class
        }
)
public interface InputmotorComponent {
}

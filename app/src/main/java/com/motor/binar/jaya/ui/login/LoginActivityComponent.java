package com.motor.binar.jaya.ui.login;

import com.motor.binar.jaya.base.annotation.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by ikun on 28/12/17.
 */
@ActivityScope
@Subcomponent(
        modules = {
                LoginActivityModule.class
        }
)
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity activity);
}

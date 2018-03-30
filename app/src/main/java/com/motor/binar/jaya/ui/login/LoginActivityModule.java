package com.motor.binar.jaya.ui.login;

/**
 * Created by ikun on 22/12/17.
 */


import com.motor.binar.jaya.base.annotation.ActivityScope;
import com.motor.binar.jaya.data.remote.FirebaseUserService;
import com.motor.binar.jaya.data.remote.UserService;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {
    LoginActivity activity;

    public LoginActivityModule(LoginActivity activity){
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    LoginActivity provideLoginActivity(){return activity;}

    @ActivityScope
    @Provides
    LoginPresenter provideLoginPresenter(UserService userService, FirebaseUserService firebaseUserService){
        return new LoginPresenter(activity, userService,firebaseUserService);
    }
}

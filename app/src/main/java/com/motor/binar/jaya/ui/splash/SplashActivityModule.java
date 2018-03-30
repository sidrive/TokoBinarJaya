package com.motor.binar.jaya.ui.splash;

import com.motor.binar.jaya.base.annotation.ActivityScope;
import com.motor.binar.jaya.data.remote.UserService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ikun on 28/12/17.
 */
@Module
public class SplashActivityModule {

    private SplashActivity activity;

    public SplashActivityModule(SplashActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    SplashActivity provideSplashActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    SplashPresenter provideSplashActivityPresenter(UserService userService) {
        return new SplashPresenter(activity, userService);
    }
}

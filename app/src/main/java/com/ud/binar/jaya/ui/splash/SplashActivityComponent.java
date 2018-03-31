package com.ud.binar.jaya.ui.splash;

import com.ud.binar.jaya.base.annotation.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by ikun on 28/12/17.
 */


@ActivityScope
@Subcomponent(
        modules = {
                SplashActivityModule.class
        }
)
public interface SplashActivityComponent {
    SplashActivity inject(SplashActivity activity);
}
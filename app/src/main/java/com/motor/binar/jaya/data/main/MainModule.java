package com.motor.binar.jaya.data.main;

import com.motor.binar.jaya.base.annotation.MainScope;
import com.motor.binar.jaya.ui.main.MainAct;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ikun on 05/01/18.
 */

@Module
public class MainModule {
    MainAct activity;

    public MainModule(MainAct activity){
        this.activity = activity;
    }

    @Provides
    @MainScope
    MainAct provideMainAct(){
        return activity;
    }
}

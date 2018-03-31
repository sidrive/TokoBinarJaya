package com.ud.binar.jaya.data.main;

import com.ud.binar.jaya.base.annotation.MainScope;
import com.ud.binar.jaya.ui.main.MainAct;

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

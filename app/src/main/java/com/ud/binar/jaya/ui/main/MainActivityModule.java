package com.ud.binar.jaya.ui.main;

import com.ud.binar.jaya.base.annotation.ActivityScope;
import com.ud.binar.jaya.data.remote.CategoryService;
import com.ud.binar.jaya.data.remote.UserService;
import com.ud.binar.jaya.data.remote.model.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ikun on 02/01/18.
 */

@Module
public class MainActivityModule {
    MainAct activity;

    public MainActivityModule(MainAct activity){this.activity = activity;}

    @ActivityScope
    @Provides
    MainAct provideMainAct(){return activity;}

    @ActivityScope
    @Provides
    MainPresenter provideMainPresenter(UserService userService, User user, CategoryService categoryService){
    return new MainPresenter(activity, userService, user, categoryService);
    }
}

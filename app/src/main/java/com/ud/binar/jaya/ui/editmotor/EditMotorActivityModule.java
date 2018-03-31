package com.ud.binar.jaya.ui.editmotor;

import com.ud.binar.jaya.base.annotation.ActivityScope;
import com.ud.binar.jaya.data.model.Barang;
import com.ud.binar.jaya.data.remote.UserService;

import dagger.Module;
import dagger.Provides;

@Module
public class EditMotorActivityModule {
    EditMotorActivity activity;

    public EditMotorActivityModule(EditMotorActivity activity){
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    EditMotorActivity provideEditMotorActivity(){return activity;}

    @ActivityScope
    @Provides
    EditMotorPresenter provideEditMotorPresenter(UserService userService,Barang barang){
        return new EditMotorPresenter(activity,userService, barang);
    }
}
